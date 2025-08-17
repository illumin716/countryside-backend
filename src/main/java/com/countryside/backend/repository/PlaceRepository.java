package com.countryside.backend.repository;

import com.countryside.backend.domain.Place;
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

    // 기존 메서드 (챗봇 답변 로직 개선에 사용)
    List<Place> findByTagsContainingIgnoreCase(String tagsKeyword);

    // [새롭게 추가] 주소에 특정 키워드가 포함된 장소 목록을 조회합니다.
    List<Place> findByAddressContainingIgnoreCase(String addressKeyword);
}