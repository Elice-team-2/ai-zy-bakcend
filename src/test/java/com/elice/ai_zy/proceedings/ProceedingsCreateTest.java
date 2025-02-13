package com.elice.ai_zy.proceedings;

import com.elice.ai_zy.proceedings.dto.CreateProceedingsDTO;
import com.elice.ai_zy.proceedings.entity.Proceedings;
import com.elice.ai_zy.proceedings.repository.ProceedingsRepository;
import com.elice.ai_zy.proceedings.service.ProceedingsService;
import com.elice.ai_zy.projects.entity.Project;
import com.elice.ai_zy.user.entity.User;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProceedingsCreateTest {

    @InjectMocks
    private ProceedingsService proceedingsService;

    @Mock
    private ProceedingsRepository proceedingsRepository;

    private User testUser;
    private Project testProject;

    @BeforeEach
    void setUp() {
        // 가짜 User 및 Project 생성
        testUser = new User();
        testProject = new Project();
    }

    @Test
    @DisplayName("✅ 회의록 생성 성공")
    void testCreateProceedings_Success() {
        // given: 정상적인 DTO 설정
        CreateProceedingsDTO dto = new CreateProceedingsDTO("회의 제목", "회의 내용", null, List.of("참석자1", "참석자2"),testUser, testProject, LocalDateTime.now());
        Proceedings mockProceeding = Proceedings.builder()
                .proceedingsId(UUID.randomUUID().toString())
                .title(dto.getTitle())
                .contents(dto.getContents())
                .owner(testUser)
                .project(testProject)
                .attendeeNames(dto.getAttendees())
                .createdAt(LocalDateTime.now())
                .build();

        when(proceedingsRepository.save(any(Proceedings.class))).thenReturn(mockProceeding);

        // when
        Proceedings createdProceeding = proceedingsService.createProceedings(dto);

        // then
        assertThat(createdProceeding).isNotNull();
        assertThat(createdProceeding.getTitle()).isEqualTo(dto.getTitle());
        verify(proceedingsRepository, times(1)).save(any(Proceedings.class));
    }

    @Test
    @DisplayName("❌ 회의록 생성 실패 (owner 없음)")
    void testCreateProceedings_Fail_NoOwner() {
        // given: owner가 없는 DTO
        CreateProceedingsDTO dto = new CreateProceedingsDTO("회의 제목", "회의 내용", null, List.of("참석자1", "참석자2"),null, testProject, LocalDateTime.now());

        // when & then: owner가 없으면 예외 발생해야 함
        Exception exception = assertThrows(IllegalArgumentException.class, () -> proceedingsService.createProceedings(dto));
        assertThat(exception.getMessage()).contains("Owner is required");
    }

    @Test
    @DisplayName("❌ 회의록 생성 실패 (project 없음)")
    void testCreateProceedings_Fail_NoProject() {
        // given: project가 없는 DTO
        CreateProceedingsDTO dto = new CreateProceedingsDTO("회의 제목", "회의 내용", null, List.of("참석자1", "참석자2"),testUser, null, LocalDateTime.now());

        // when & then: project가 없으면 예외 발생해야 함
        Exception exception = assertThrows(IllegalArgumentException.class, () -> proceedingsService.createProceedings(dto));
        assertThat(exception.getMessage()).contains("Project is required");
    }

    @Test
    @DisplayName("❌ 존재하지 않는 ID로 회의록 조회 시 예외 발생")
    void testReadProceedingById_NotFound() {
        // given: 존재하지 않는 ID
        String nonExistentId = UUID.randomUUID().toString();
        when(proceedingsRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        // when & then: 조회 시 예외 발생
        Exception exception = assertThrows(EntityNotFoundException.class, () -> proceedingsService.readProceedingById(nonExistentId));
        assertThat(exception.getMessage()).contains("Proceeding not found");
    }


    @Test
    @DisplayName("❌ 회의록 삭제 후 조회 시 예외 발생")
    void testDeleteProceeding_ThenReadFails() {
        // given: 삭제할 회의록
        String proceedingId = UUID.randomUUID().toString();
        Proceedings mockProceeding = Proceedings.builder()
                .proceedingsId(proceedingId)
                .title("삭제할 회의록")
                .contents("내용")
                .owner(testUser)
                .project(testProject)
                .createdAt(LocalDateTime.now())
                .build();

        when(proceedingsRepository.findById(proceedingId)).thenReturn(Optional.of(mockProceeding));

        // when: 삭제 실행
        proceedingsService.deleteProceeding(proceedingId);

        // then: 조회 시 예외 발생
        when(proceedingsRepository.findById(proceedingId)).thenReturn(Optional.empty());
        Exception exception = assertThrows(EntityNotFoundException.class, () -> proceedingsService.readProceedingById(proceedingId));
        assertThat(exception.getMessage()).contains("Proceeding not found");
    }


}
