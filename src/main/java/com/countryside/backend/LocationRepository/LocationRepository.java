package com.countryside.backend;
/*
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query("SELECT DISTINCT l FROM Location l " +
            "LEFT JOIN FETCH l.landmarks " +
            "WHERE lower(l.englishName) LIKE lower(concat('%', :keyword, '%')) " +
            "OR l.koreanName LIKE concat('%', :keyword, '%')")
    List<Location> searchByEnglishOrKoreanName(@Param("keyword") String keyword);
}
 */
