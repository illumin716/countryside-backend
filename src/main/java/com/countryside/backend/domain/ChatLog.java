package com.countryside.backend.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime; // 날짜와 시간 정보를 다루기 위환 임포트

@Entity
@Table(name = "chat_logs")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder

public class ChatLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 채팅 기록 고유 ID

    @ManyToOne(fetch = FetchType.LAZY) // N:1 관계 (여러 ChatLog가 하나의 User에 속함)
    @JoinColumn(name = "user_id", nullable = false) // FK 컬럼 이름 지정. User 엔티티의 ID와 연결
    private User user; // 이 채팅 기록이 속한 사용자 엔티티

    @Column(nullable = false, length = 50) // 메시지를 보낸 주체(예: USER, ChatBOT)
    private String sender;

    @Column(nullable = false, columnDefinition = "TEXT") // 채팅 메시지 (TEXT 타입)
    private String message;

    @Column(nullable = false)
    private LocalDateTime timestamp; // 메시지가 생성된 시간(자동 기록으로 설정)

    // TODO: Notion ERD에 따라 추가 필드를 여기에 정의하세요.
    // 예시:
    // @Column(length = 255)
    // private String responseType; // 챗봇 응답 유형 (예: "RECOMMENDATION", "GENERAL_INFO")

    // @Column(length = 255)
    // private String recommendedPlaceId; // 챗봇이 추천한 명소 ID (옵션)
    // (만약 Place 엔티티와 직접적인 연관 관계를 맺고 싶다면 @ManyToOne Place place; 로 변경 가능)
}
