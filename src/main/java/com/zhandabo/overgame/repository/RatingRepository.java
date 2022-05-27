package com.zhandabo.overgame.repository;

import com.zhandabo.overgame.model.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    @Query(value = "select avg(grade) from rating where game_id = :gameId", nativeQuery = true)
    BigDecimal getRatingGameByGameId(Long gameId);
}
