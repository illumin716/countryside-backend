package com.countryside.backend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "내 프로젝트 API 문서",
                version = "1.0",
                description = "개발 중인 API에 대한 명세입니다."
        )
)
@Configuration
public class SwaggerConfig {
}
