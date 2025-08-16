package com.countryside.backend.controller;

import com.countryside.backend.domain.Place;
import com.countryside.backend.repository.PlaceRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/places")
public class PlaceController {

    private final PlaceRepository placeRepository;

    public PlaceController(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    // 검색어로 이름/설명/카테고리/태그 모두 검색
    @GetMapping("/search")
    public List<Place> searchPlaces(@RequestParam String keyword) {
        return placeRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrCategoryContainingIgnoreCaseOrTagsContainingIgnoreCase(
                keyword, keyword, keyword, keyword);
    }

    // 필요시 장소 추가 API 예시
    @PostMapping
    public Place addPlace(@RequestBody Place place) {
        return placeRepository.save(place);
    }
}
