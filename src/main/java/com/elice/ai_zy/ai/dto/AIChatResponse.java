package com.elice.ai_zy.ai.dto;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AIChatResponse {

    private String response;

    private LocalDateTime createdAt;

    @Builder
    public AIChatResponse(String response, LocalDateTime createdAt) {
        this.response = response;
        this.createdAt = createdAt;
    }
}
