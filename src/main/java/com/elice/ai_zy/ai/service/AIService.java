package com.elice.ai_zy.ai.service;


import com.elice.ai_zy.ai.dto.AIChatRequest;
import com.elice.ai_zy.ai.dto.AIChatResponse;
import com.elice.ai_zy.ai.dto.AIRequest;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Service;

@Service
public class AIService {

    private final OpenAiChatModel openAiChatModel;

    public AIService(OpenAiChatModel openAiChatModel) {
        this.openAiChatModel = openAiChatModel;
    }


    public AIChatResponse getAIChat(AIRequest aiChatRequest) {
        String promptText = "다음 회의록 내용을 요약해줘. \n\n" + aiChatRequest.getProceedingsId();
        Prompt prompt = new Prompt(promptText);

        String result = openAiChatModel.call(prompt).getResult().getOutput().getContent();

        return AIChatResponse.builder()
                .response(result)
                .build();
    }
}
