package com.zhandabo.overgame.repository;

import com.zhandabo.overgame.model.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    @Query(value = "from Game g " +
            "inner join GameGenre gg on g.id = gg.game.id " +
            "where gg.genre.id = :genreId")
    List<Game> getAllByGenreId(Long genreId);
}
