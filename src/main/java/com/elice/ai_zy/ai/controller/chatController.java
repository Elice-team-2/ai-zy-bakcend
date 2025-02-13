package com.elice.ai_zy.ai.controller;

import com.elice.ai_zy.ai.dto.AIChatRequest;
import com.elice.ai_zy.ai.dto.AIRequest;
import com.elice.ai_zy.ai.dto.ChatGPTResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/api")
@RestController
public class chatController {

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiURL;

    private RestTemplate template;

    public chatController(RestTemplate template) {
        this.template = template;
    }

    @GetMapping("/ai/test")
    public String chat(@RequestParam(name = "prompt")String prompt){

        AIChatRequest request = new AIChatRequest(model, prompt);
        ChatGPTResponse chatGPTResponse =  template.postForObject(apiURL, request, ChatGPTResponse.class);

        return chatGPTResponse.getChoices().get(0).getMessage().getContent();
    }

//    @GetMapping("/ai")
//    public String getAIChat(@RequestBody AIRequest aiRequest){
//
//        ChatGPTResponse a =  template.postForObject(apiURL, request, ChatGPTResponse.class);
//        ChatGPTResponse chatGPTResponse =  aiService.getChat(aiRequest);
//
//        return chatGPTResponse.getChoices().get(0).getMessage().getContent();
//    }
}