package com.elice.ai_zy.ai.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class AIRequest {

    @NotNull
    private String mode;

    @NotNull
    private String proceedingsId;

    @NotNull
    private String rawCommand;

}