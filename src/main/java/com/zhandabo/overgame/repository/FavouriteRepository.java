package com.zhandabo.overgame.repository;

import com.zhandabo.overgame.model.entity.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FavouriteRepository extends JpaRepository<Favourite, Long> {

    @Query(value = "from Favourite f where f.game.id = :gameId and f.user.keycloakId = :userId")
    Favourite getByGameId(Long gameId, String userId);
}
