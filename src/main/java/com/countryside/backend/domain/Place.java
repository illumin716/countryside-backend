package com.countryside.backend.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity // 데이터베이스 테이블과 연결될 클래스를 알려줌
@Table(name = "places") // 해당 클래스의 이름은 'places'
@Getter // 모든 필드의 값을 가져오는(get) 메서드를 자동으로 만듬
@Setter // 모든 필드의 값을 설정하는(set) 메서드를 자동으로 만듬
@NoArgsConstructor // 파라미터 없는 긱본 생성자를 만듬
@AllArgsConstructor // 모든 필드를 파라미터로 받는 생성자를 만듬
@Builder // 객체를 멋지게 만들 수 있는 빌터 패턴을 만듬

public class Place {
    @Id // 필드의 고유 번호
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID는 데이터베이스가 알아서 자동으로 만듬
    private Long id;

    @Column(nullable = false, length = 255) // 명소 이름 (VARCHAR 255)
    private String name;

    @Column(columnDefinition = "TEXT") // 설명(TEXT 타입)
    private String description;

    @Column(length = 255)
    private String category; // 명소 카테고리

    @Column(nullable = false, length = 500)
    private String address; // 주소

    @Column
    private Double latitude; // 위도

    @Column
    private Double longitude; // 경도

    @Column(name = "image_url")
    private String imageUrl; // 이미지 URL

    @Column(name = "average_rating")
    private Double averageRating; // 평균 평점

    @Column(name = "total_reviews")
    private Integer totalReviews; // 총 리뷰 수

    @Column
    private String tags; // 태그들 (JSON에서는 배열이지만 일단 String으로 받아서 나중에 파싱)

    @Column
    private String operatingHours; // 운영 시간

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // TODO: 다른 엔티티와의 연관 관계가 있다면 여기에 정의합니다.
    // 예시: 명소에 대한 리뷰가 있다면
    // @OneToMany(mappedBy = "place", cascade = CascadeType.ALL, orphanRemoval = true)
    // private List<Review> reviews = new ArrayList<>();

    // 참고: tags 필드는 JSON에서 배열 형태([“빵집", "맛집"])로 왔기 때문에
    // Spring JPA에서 바로 List<String>으로 매핑하려면 @ElementCollection 등 추가 설정이 필요해요.
    // 지금은 가장 간단하게 String으로 받아서 콤마(,) 등으로 구분된 문자열로 저장한다고 가정할게요.
    // 나중에 챗봇이 활용할 때는 이 String을 다시 파싱해서 쓸 거예요.

}
