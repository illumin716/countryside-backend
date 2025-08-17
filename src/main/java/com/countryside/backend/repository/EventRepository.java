package com.countryside.backend.repository;

import com.countryside.backend.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    // JpaRepository가 기본 CRUD 및 데이터 조회 기능을 제공합니다.
}