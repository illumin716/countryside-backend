package com.countryside.backend.controller;

import com.countryside.backend.domain.Place;
import com.countryside.backend.repository.PlaceRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/places")
public class PlaceController {

    private final PlaceRepository placeRepository;

    public PlaceController(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    // 기존 메서드: 검색어로 이름/설명/카테고리/태그 모두 검색
    @GetMapping("/search")
    public List<Place> searchPlaces(@RequestParam String keyword) {
        return placeRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrCategoryContainingIgnoreCaseOrTagsContainingIgnoreCase(
                keyword, keyword, keyword, keyword);
    }

    // 기존 메서드: 장소 추가 API 예시
    @PostMapping
    public Place addPlace(@RequestBody Place place) {
        return placeRepository.save(place);
    }

    // [새롭게 추가] 사용자가 선택한 지역 정보를 세션에 저장
    @PostMapping("/set-location")
    public ResponseEntity<String> setLocation(@RequestBody Map<String, String> request, HttpSession session) {
        String location = request.get("location");
        if (location != null) {
            session.setAttribute("userLocation", location);
            return ResponseEntity.ok("Location set successfully to: " + location);
        } else {
            return ResponseEntity.badRequest().body("Location data is missing.");
        }
    }

    // 기존 코드 (세션 사용)
    @GetMapping("/by-location")
    public ResponseEntity<List<Place>> getPlacesByLocation(HttpSession session) {
        String location = (String) session.getAttribute("userLocation");
        if (location != null) {
            List<Place> places = placeRepository.findByAddressContainingIgnoreCase(location);
            return ResponseEntity.ok(places);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // [새롭게 추가] 특정 ID의 장소를 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlace(@PathVariable Long id) {
        if (placeRepository.existsById(id)) {
            placeRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}