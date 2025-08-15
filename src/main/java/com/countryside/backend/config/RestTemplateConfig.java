package com.countryside.backend.config; // 이 패키지 선언이 정확해야 합니다.

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        // RestTemplate에 기본 JSON 메시지 컨버터가 등록되어 있지만,
        // 명시적으로 추가하여 혹시 모를 누락이나 순서 문제를 방지합니다.
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        return restTemplate;
    }
}