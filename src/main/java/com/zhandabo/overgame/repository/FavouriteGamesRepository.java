package com.zhandabo.overgame.repository;

import com.zhandabo.overgame.model.entity.FavouriteGames;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FavouriteGamesRepository extends JpaRepository<FavouriteGames, Long> {

    @Query(value = "from FavouriteGames f where f.game.id = :gameId and f.user.keycloakId = :userId")
    FavouriteGames getByGameIdAndUserId(Long gameId, String userId);
}
