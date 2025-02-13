package com.elice.ai_zy.proceedings.controller;

import com.elice.ai_zy.proceedings.dto.CreateProceedingsDTO;
import com.elice.ai_zy.proceedings.dto.UpdateProceedingsDTO;
import com.elice.ai_zy.proceedings.entity.Proceedings;
import com.elice.ai_zy.proceedings.service.ProceedingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProceedingsController {

    private final ProceedingsService proceedingsService;

    @PostMapping("/proceedings")
    public ResponseEntity<Proceedings> createProceeding(@RequestBody CreateProceedingsDTO dto, String userNames) {
        return ResponseEntity.ok(proceedingsService.createProceedings(dto, userNames));
    }

    @GetMapping("/proceedings/{proceedingsId}")
    public ResponseEntity<Proceedings> getProceedingById(@PathVariable UUID proceedingsId) {
        return ResponseEntity.ok(proceedingsService.readProceedingById(proceedingsId));
    }

    @GetMapping("/proceedings")
    public ResponseEntity<List<Proceedings>> getAllProceedings() {
        return ResponseEntity.ok(proceedingsService.readAllProceedings());
    }

    @PutMapping("/proceedings/{proceedingsId}")
    public ResponseEntity<Proceedings> updateProceeding(@PathVariable UUID proceedingsId, @RequestBody UpdateProceedingsDTO dto,String attendeeNames) {
        return ResponseEntity.ok(proceedingsService.updateProceeding(proceedingsId, dto, attendeeNames));
    }

    @DeleteMapping("/proceedings/{proceedingsId}")
    public ResponseEntity<UUID> deleteProceeding(@PathVariable UUID proceedingsId) {
        return ResponseEntity.ok(proceedingsService.deleteProceeding(proceedingsId));
    }
}
