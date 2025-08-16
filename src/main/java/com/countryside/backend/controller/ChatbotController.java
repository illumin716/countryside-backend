package com.countryside.backend.controller;

import com.countryside.backend.service.ChatbotService;
import org.springframework.web.bind.annotation.GetMapping; // PostMapping 대신 GetMapping 사용
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam; // RequestBody 대신 RequestParam 사용
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chatbot")
public class ChatbotController {

    private final ChatbotService chatbotService;

    public ChatbotController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    // @PostMapping("/message") // 이 부분을 수정합니다.
    @GetMapping // GET 요청으로 "/api/chatbot" 주소로 질문이 오면
    // @RequestBody String userMessage 대신 @RequestParam("message") String userMessage 사용
    public String getChatbotResponse(@RequestParam("message") String userMessage) {
        // 챗봇(chatbotService)에게 사용자 질문을 전달하고 답장 받기
        String response = chatbotService.getChatbotResponse(userMessage);
        return response; // 받은 답장을 사용자에게 돌려주기
    }
}