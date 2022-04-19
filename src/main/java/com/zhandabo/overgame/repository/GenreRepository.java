package com.zhandabo.overgame.repository;

import com.zhandabo.overgame.model.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    @Query(value = "from Genre g " +
            "inner join GameGenre gg on g.id = gg.genre.id " +
            "where gg.game.id = : gameId")
    List<Genre> getAllByGameId(Long gameId);
}
