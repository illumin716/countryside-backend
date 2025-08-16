package com.countryside.backend.repository;

import com.countryside.backend.domain.Faq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaqRepository extends JpaRepository<Faq, Long> {
    // JpaRepository를 상속받았기 때문에 findById, findAll, save, deleteById 등의 메서드는 자동으로 제공됩니다.
    // 필요하다면 여기에 추가적인 쿼리 메서드를 정의할 수 있습니다.
    // 예: Optional<Faq> findByQuestion(String question);
}