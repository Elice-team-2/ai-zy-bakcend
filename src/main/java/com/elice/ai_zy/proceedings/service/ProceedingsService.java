package com.elice.ai_zy.proceedings.service;

import com.elice.ai_zy.proceedings.dto.CreateProceedingsDTO;
import com.elice.ai_zy.proceedings.dto.UpdateProceedingsDTO;
import com.elice.ai_zy.proceedings.entity.Proceedings;
import com.elice.ai_zy.proceedings.repository.ProceedingsRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProceedingsService {

    private final ProceedingsRepository proceedingsRepository;

    @Transactional
    public Proceedings createProceedings(CreateProceedingsDTO dto) {
        if (dto.getOwner() == null) {
            throw new IllegalArgumentException("Owner is required");
        }
        if (dto.getProject() == null) {
            throw new IllegalArgumentException("Project is required");
        }

        Proceedings proceedings = Proceedings.builder()
                .title(dto.getTitle())
                .contents(dto.getContents())
                .tags(dto.getTags())
                .attendeeNames(dto.getAttendees())
                .owner(dto.getOwner())
                .project(dto.getProject())
                .createdAt(LocalDateTime.now())
                .build();
        return proceedingsRepository.save(proceedings);
    }

    @Transactional
    public Proceedings readProceedingById(String proceedingId) {
        return proceedingsRepository.findById(proceedingId)
                .orElseThrow(() -> new EntityNotFoundException("Proceeding not found with id: " + proceedingId));
    }

    @Transactional
    public List<Proceedings> readAllProceedings() {
        List<Proceedings> proceedingsList = proceedingsRepository.findAll();
        return proceedingsList.isEmpty() ? List.of() : proceedingsList;
    }

    @Transactional
    public Proceedings updateProceeding(String proceedingId, UpdateProceedingsDTO dto) {
        Proceedings proceedings = proceedingsRepository.findById(proceedingId)
                .orElseThrow(() -> new EntityNotFoundException("Proceeding not found with id: " + proceedingId));
        if (dto.getOwner() == null) {
            throw new IllegalArgumentException("Owner is required");
        }
        if (dto.getProject() == null) {
            throw new IllegalArgumentException("Project is required");
        }
        proceedings = Proceedings.builder()
                .proceedingsId(proceedings.getProceedingsId())
                .title(dto.getTitle() != null ? dto.getTitle() : proceedings.getTitle())
                .contents(dto.getContents() != null ? dto.getContents() : proceedings.getContents())
                .tags(dto.getTags() != null ? dto.getTags() : proceedings.getTags())
                .attendeeNames(dto.getAttendees())
                .owner(dto.getOwner())
                .project(dto.getProject())
                .createdAt(proceedings.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .build();

        return proceedingsRepository.save(proceedings);
    }

    @Transactional
    public String deleteProceeding(String proceedingId) {
        Proceedings proceedings = proceedingsRepository.findById(proceedingId)
                .orElseThrow(() -> new EntityNotFoundException("Proceeding not found with id: " + proceedingId));

        proceedingsRepository.delete(proceedings);
        return proceedingId;
    }
}
