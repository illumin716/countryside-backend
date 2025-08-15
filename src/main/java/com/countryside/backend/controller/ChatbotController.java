package com.countryside.backend.controller;

import com.countryside.backend.service.ChatbotService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chatbot")
public class ChatbotController {

    private final ChatbotService chatbotService; // 챗봇 서비스를 불러올 준비
    public ChatbotController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    @PostMapping("/message") // "/api/chatbot/message" 주소로 질문이 오면
    public String getChatbotResponse(@RequestBody String userMessage) {
        // 챗봇(chatbotService)에게 사용자 질문을 전달하고 답장 받기
        String response = chatbotService.getChatbotResponse(userMessage);
        return response; // 받은 답장을 사용자에게 돌려주기
    }

}
