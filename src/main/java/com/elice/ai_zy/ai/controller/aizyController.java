package com.elice.ai_zy.ai.controller;

import com.elice.ai_zy.ai.dto.ChatGPTRequest;
import com.elice.ai_zy.ai.dto.ChatGPTResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/bot")
@RestController
public class aizyController {
    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiURL;

    private RestTemplate template;

    public aizyController(RestTemplate template) {
        this.template = template;
    }

    @GetMapping("/chat")
    public String chat(@RequestParam(name = "prompt")String prompt){
        ChatGPTRequest request = new ChatGPTRequest(model, prompt);
        ChatGPTResponse chatGPTResponse =  template.postForObject(apiURL, request, ChatGPTResponse.class);
        return chatGPTResponse.getChoices().get(0).getMessage().getContent();
    }
}
