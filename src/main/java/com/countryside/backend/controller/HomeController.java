
package com.countryside.backend.controller;

import com.countryside.backend.dto.HomeFestivalsResponse;
import com.countryside.backend.service.FestivalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/home")
@Tag(name = "Home", description = "홈 화면 API")
public class HomeController {

    private final FestivalService festivalService;

    public HomeController(FestivalService festivalService) {
        this.festivalService = festivalService;
    }

    @GetMapping("/festivals")
    @Operation(summary = "현재 진행중인 축제 랜덤 목록", description = "문자열 형식의 축제명 3~4개를 무작위로 반환합니다.")
    public HomeFestivalsResponse getFestivals() {
        return new HomeFestivalsResponse(festivalService.getRandomCurrentFestivals());
    }
}
