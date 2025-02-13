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

    // Create
    public Proceedings createProceedings(CreateProceedingsDTO dto, String userNames) {
        Proceedings proceedings = Proceedings.builder()
                .title(dto.getTitle())
                .contents(dto.getContents())
                .tags(dto.getTags())
                .attendeeNames(dto.getAttendees(userNames))
                .createdAt(LocalDateTime.now())
                .build();
        return proceedingsRepository.save(proceedings);
    }

    // Read
    public Proceedings readProceedingById(UUID proceedingId) {
        return proceedingsRepository.findById(proceedingId)
                .orElseThrow(() -> new EntityNotFoundException("Proceeding not found with id: " + proceedingId));
    }

    // Read All
    public List<Proceedings> readAllProceedings() {
        return proceedingsRepository.findAll();
    }

    // Update
    @Transactional
    public Proceedings updateProceeding(UUID proceedingId, UpdateProceedingsDTO dto) {
        Proceedings proceedings = proceedingsRepository.findById(proceedingId)
                .orElseThrow(() -> new EntityNotFoundException("Proceeding not found with id: " + proceedingId));

        proceedings = Proceedings.builder()
                .proceedingsId(proceedings.getProceedingsId())
                .title(dto.getTitle() != null ? dto.getTitle() : proceedings.getTitle())
                .contents(dto.getContents() != null ? dto.getContents() : proceedings.getContents())
                .tags(dto.getTags() != null ? dto.getTags() : proceedings.getTags())
                .createdAt(proceedings.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .build();

        return proceedingsRepository.save(proceedings);
    }

    // Delete
    public UUID deleteProceeding(UUID proceedingId) {
        Proceedings proceedings = proceedingsRepository.findById(proceedingId)
                .orElseThrow(() -> new EntityNotFoundException("Proceeding not found with id: " + proceedingId));

        proceedingsRepository.delete(proceedings);
        return proceedingId;
    }
}
