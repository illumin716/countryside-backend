package com.countryside.backend.repository;

import com.countryside.backend.domain.Place; // Place 엔티티를 사용
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    // JpaRepository에서 save, findById, findAll 같은 기본 기능들을 제공!

    // 필요 시 추가
    List<Place> findByNameContainingIgnoreCase(String name); // 이름에 특정 단어가 들어간 명소 찾기
    List<Place> findByCategoryContainingIgnoreCase(String category); // 카테고리로 명소 찾기
    List<Place> findByDescriptionContainingIgnoreCase(String keyword); // 설명에 특정 키워드가 들어간 명소 찾기
    List<Place> findByTagsContainingIgnoreCase(String tag); // 태그에 특정 키워드가 들어간 명소 찾기

    // 여러 검색 조건 조합도 가능(예: 이름 또는 설명으로 찾기)
    List<Place> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrCategoryContainingIgnoreCaseOrTagsContainingIgnoreCase(String nameKeyword, String descKeyword, String categoryKeyword, String tagKeyword);

    // Place 엔티티의 기본 CRUD 메서드가 자동으로 제공됩니다.

    // TODO: 명소 이름으로 검색, 카테고리별 검색 등 필요에 따라 추가 메서드 정의
    // 예시: List<Place> findByNameContainingIgnoreCase(String name);
    // 예시: List<Place> findByCategory(String category);
}