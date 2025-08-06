package com.countryside.backend.repository;

import com.countryside.backend.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    // Place 엔티티의 기본 CRUD 메서드가 자동으로 제공됩니다.

    // TODO: 명소 이름으로 검색, 카테고리별 검색 등 필요에 따라 추가 메서드 정의
    // 예시: List<Place> findByNameContainingIgnoreCase(String name);
    // 예시: List<Place> findByCategory(String category);
}