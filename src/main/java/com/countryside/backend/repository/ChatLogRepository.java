package com.countryside.backend.repository;

import com.countryside.backend.domain.ChatLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ChatLogRepository extends JpaRepository<ChatLog, Long> {
    // ChatLog 엔티티의 기본 CRUD 메서드가 자동으로 제공됩니다.

    // TODO: 특정 사용자의 채팅 기록 조회, 특정 시간에 발생한 채팅 조회 등 필요에 따라 추가 메서드 정의
    // 예시: List<ChatLog> findByUser_IdOrderByTimestampAsc(Long userId); // 특정 사용자의 채팅 기록을 시간 순으로 정렬
    // 예시: List<ChatLog> findBySender(String sender); // 'USER' 또는 'BOT'으로 조회
}
