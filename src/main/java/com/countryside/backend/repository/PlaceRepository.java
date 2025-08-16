package com.countryside.backend.repository;

import com.countryside.backend.domain.Place; // Place 엔티티를 사용
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    // 기존 메서드 (PlaceController에서 사용)
    List<Place> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrCategoryContainingIgnoreCaseOrTagsContainingIgnoreCase(
            String nameKeyword, String descriptionKeyword, String categoryKeyword, String tagsKeyword
    );

    // 기존 메서드 (ChatbotService에서 '카페' 검색에 사용)
    List<Place> findByCategoryContainingIgnoreCase(String category);

    // 새롭게 추가할 메서드: tags 필드에서 키워드를 포함하는 장소 검색
    List<Place> findByTagsContainingIgnoreCase(String tagsKeyword); // <-- 이 줄을 추가합니다.
}