package com.elice.ai_zy.proceedings;

import com.elice.ai_zy.proceedings.dto.UpdateProceedingsDTO;
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
import org.mockito.Mockito;
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
class ProceedingsUpdateTest {

    @InjectMocks
    private ProceedingsService proceedingsService;

    @Mock
    private ProceedingsRepository proceedingsRepository;

    private User testUser;
    private Project testProject;
    private Proceedings testProceeding;

    @BeforeEach
    void setUp() {
        // ✅ User 및 Project 객체 초기화
        testUser = new User();
        testProject = new Project();

        // ✅ 회의록 객체 초기화
        testProceeding = Proceedings.builder()
                .proceedingsId(UUID.randomUUID().toString()) // ✅ ID 생성
                .title("기존 회의록 제목")
                .contents("기존 회의록 내용")
                .owner(testUser) // ✅ Owner 설정
                .project(testProject) // ✅ Project 설정
                .createdAt(LocalDateTime.now())
                .build();
    }

    @Test
    @DisplayName("✅ 회의록 수정 성공")
    void testUpdateProceeding_Success() {
        // given
        UpdateProceedingsDTO dto = new UpdateProceedingsDTO(
                "새로운 제목", "새로운 내용", null,
                testUser, testProject, List.of("참석자1", "참석자2"), LocalDateTime.now()
        );

        // ✅ Optional.ofNullable()로 NullPointerException 방지
        when(proceedingsRepository.findById(any(String.class))).thenReturn(Optional.ofNullable(testProceeding));

        // save() 메소드가 업데이트된 값을 반환하도록 설정
        Proceedings updatedProceeding = Proceedings.builder()
                .proceedingsId(testProceeding.getProceedingsId())
                .title(dto.getTitle()) // title을 새로운 값으로 설정
                .contents(dto.getContents()) // contents도 업데이트
                .tags(dto.getTags()) // tags도 업데이트
                .attendeeNames(dto.getAttendees())
                .owner(dto.getOwner())
                .project(dto.getProject())
                .createdAt(testProceeding.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .build();

        when(proceedingsRepository.save(any(Proceedings.class))).thenReturn(updatedProceeding);

        // when
        Proceedings result = proceedingsService.updateProceeding(testProceeding.getProceedingsId(), dto);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo(dto.getTitle()); // title이 업데이트되었는지 확인
        assertThat(result.getContents()).isEqualTo(dto.getContents()); // contents도 업데이트되었는지 확인
        verify(proceedingsRepository, times(1)).save(any(Proceedings.class)); // save가 한 번 호출되었는지 확인
    }


    @Test
    @DisplayName("❌ 회의록 수정 실패 (Owner 없음)")
    void testUpdateProceeding_Fail_NoOwner() {
        // given: Owner가 없는 DTO
        UpdateProceedingsDTO dto = new UpdateProceedingsDTO(
                "새로운 제목", "새로운 내용", null,
                null, testProject, List.of("참석자1", "참석자2"), LocalDateTime.now()
        );
        when(proceedingsRepository.findById(any(String.class))).thenReturn(Optional.ofNullable(testProceeding));

        // when & then: Owner가 없으면 예외 발생
        Exception exception = assertThrows(IllegalArgumentException.class, () -> proceedingsService.updateProceeding(testProceeding.getProceedingsId(), dto));
        assertThat(exception.getMessage()).contains("Owner is required");
    }

    @Test
    @DisplayName("❌ 회의록 수정 실패 (Project 없음)")
    void testUpdateProceeding_Fail_NoProject() {
        // given: Project가 없는 DTO
        UpdateProceedingsDTO dto = new UpdateProceedingsDTO(
                "새로운 제목", "새로운 내용", null,
                testUser, null, List.of("참석자1", "참석자2"), LocalDateTime.now()
        );
        when(proceedingsRepository.findById(any(String.class))).thenReturn(Optional.ofNullable(testProceeding));

        // when & then: Project가 없으면 예외 발생
        Exception exception = assertThrows(IllegalArgumentException.class, () -> proceedingsService.updateProceeding(testProceeding.getProceedingsId(), dto));
        assertThat(exception.getMessage()).contains("Project is required");
    }
}
