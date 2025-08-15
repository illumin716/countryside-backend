package com.countryside.backend.repository;

import com.countryside.backend.domain.User; // User 엔티티 임포트
import org.springframework.data.jpa.repository.JpaRepository; // Spring Data JPA Repository 임포트
import org.springframework.stereotype.Repository;

@Repository // 해당 인터페이스가 Spring Bean으로 등록
public interface UserRepository extends JpaRepository<User, Long> {
    // JpaRepository를 상속함으로써 User 엔티티(첫 번째 제네릭)와 그 ID 타입(Long, 두 번째 제네릭)에 대한
    // findAll(), findById(), save(), delete() 등 기본 CRUD 메서드가 자동으로 제공됩니다.

    // TODO: 만약 특정 필드(예: 닉네임)로 사용자를 조회하는 기능이 필요하다면 여기에 추가할 수 있습니다.
    // 예시: Optional<User> findByNickname(String nickname);
}