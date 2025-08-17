package com.countryside.backend.controller;

import com.countryside.backend.service.ChatbotService;
import org.springframework.web.bind.annotation.*; // 필요한 어노테이션을 모두 import

@RestController
@RequestMapping("/api/chatbot")
public class ChatbotController {

    private final ChatbotService chatbotService;

    public ChatbotController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    // PostMapping으로 변경
    @PostMapping
    public String getChatbotResponse(@RequestBody String userMessage) {
        String response = chatbotService.getChatbotResponse(userMessage);
        return response;
    }
}