package com.countryside.backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@Configuration
public class OpenAIConfig {
    @Value("${openai.api.key}") // application.properties에서 API 키 가져오기
    private String openaiApiKey;

    @Bean // Spring Boot가 RestTemplate를 만들어서 필요할 때 마다 쓸 수 있게 구현
    public RestTemplate restTempalate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(((request, body, execution) -> {
           // GPT에게 보낼 때 필요한 비밀 헤더(머리말)를 추가
           request.getHeaders().add(HttpHeaders.AUTHORIZATION, "Bearer " + openaiApiKey);
           request.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
           return execution.execute(request, body);
        }));
        return restTemplate;
    }
}
