package com.countryside.backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("CountrySide API Documentation")
                        .description("AI를 통한 지역 명소 안내 서비스의 백엔드 API 문서입니다.")
                        .version("v0.0.1"));
    }
}
