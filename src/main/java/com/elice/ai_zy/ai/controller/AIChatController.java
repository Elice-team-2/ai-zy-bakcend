package com.elice.ai_zy.ai.controller;

import com.elice.ai_zy.ai.dto.AIChatRequest;
import com.elice.ai_zy.ai.dto.AIChatResponse;
import com.elice.ai_zy.ai.dto.AIRequest;
import com.elice.ai_zy.ai.dto.ChatGPTResponse;
import com.elice.ai_zy.ai.service.AIService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/api/public")
@RestController
public class AIChatController {

//    @Value("${openai.model}")
//    private String model;
//
//    @Value("${openai.api.url}")
//    private String apiURL;

//    private RestTemplate template;
    private AIService aiService;

    public AIChatController(AIService aiService) {
        this.aiService = aiService;
    }

//    @GetMapping("/ai/test")
//    public String chat(@RequestParam(name = "prompt")String prompt){
//
//        AIChatRequest request = new AIChatRequest(model, prompt);
//        ChatGPTResponse chatGPTResponse =  template.postForObject(apiURL, request, ChatGPTResponse.class);
//
//        return chatGPTResponse.getChoices().get(0).getMessage().getContent();
//    }

    @GetMapping("/ai")
    public ResponseEntity<AIChatResponse> getAIChat(@RequestBody AIRequest aiRequest){
        AIChatResponse aiChatResponse = aiService.getAIChat(aiRequest);

        return ResponseEntity.ok(aiChatResponse);
    }
}