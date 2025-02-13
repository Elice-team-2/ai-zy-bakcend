package com.elice.ai_zy.ai.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AIChatRequest {
    private String model;
    private List<Message> messages;

    public AIChatRequest(String model, String prompt) {
        this.model = model;
        this.messages =  new ArrayList<>();
        this.messages.add(new Message("user", prompt));
    }
}