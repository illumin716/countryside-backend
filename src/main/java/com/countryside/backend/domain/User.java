// User 엔티티는 사용자를 식별할 최소한의 id만 가지고 있다.
// 챗봇 기록이나 명소 추천 기록을 특정 id를 가진 사용자에게 연결하는 용도로 사용

package com.countryside.backend.domain;

import jakarta.persistence.*; // JPA 어노테이션 임포트
import lombok.*; // Lombok 어노테이션 임포트

@Entity // 이 클래스는 JPA 엔티티를 나타냄. 데이터베이스 테이블과 매핑
@Table(name = "users") //이 엔티티가 매핑될 데이터베이스 테이블 이름을 저장
@Getter // Lombok: 모든 필드에 대한 getter 메서드를 자동으로 생성
@Setter // Lombok: 모든 필드에 대한 setter 메서드를 자동으로 생성
@NoArgsConstructor // Lombok: 인자가 없는 기본 생성자를 자동으로 생성, JPA 사용 시 필수
@AllArgsConstructor // Lombok: 모든 필드를 인자로 받는 생성자를 자동으로 생성
@Builder // Lombok: 빌더 패턴을 사용하여 객체를 생성할 수 있도록 함

public class User {
    @Id // 이 필드가 테이블의 PK임을 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK값이 데이터베이스에서 자동으로 생성
    private Long id; // 사용자를 식별할 고유 ID

    // 로그인/회원가입 기능이 없으므로, 사용자에게 더 이상의 상세 정보는 저장하지 않음
    // 필요시 추후에 닉네임, 생성일 등 필드를 추가할 수 있다.
    // @Column(nullable = false, unique = true)
    // private String nickname;

}
