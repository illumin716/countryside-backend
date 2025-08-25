package com.countryside.backend.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "places")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder

public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255) // 명소 이름 (VARCHAR 255)
    private String name;

    @Column(nullable = false, length = 500) // 주소
    private String address;

    @Column
    private Double latitude;

    @Column
    private Double longitude;

    @Column(columnDefinition = "TEXT") // 설명(TEXT 타입)
    private String description;

    // TODO: Notion ERD에 따라 추가 필드를 여기에 정의하세요.
    // 예시:
    // @Column(length = 255)
    // private String category; // 명소 카테고리 (예: 음식점, 관광지)

    // @Column(length = 1024)
    // private String imageUrl; // 대표 이미지 URL

    // @Column
    // private Double rating; // 평균 평점 (예: 0.0 ~ 5.0)

    // TODO: 다른 엔티티와의 연관 관계가 있다면 여기에 정의합니다.
    // 예시: 명소에 대한 리뷰가 있다면
    // @OneToMany(mappedBy = "place", cascade = CascadeType.ALL, orphanRemoval = true)
    // private List<Review> reviews = new ArrayList<>();

}
