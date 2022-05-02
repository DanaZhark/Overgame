package com.zhandabo.overgame.repository;

import com.zhandabo.overgame.model.entity.Game;
import com.zhandabo.overgame.model.enums.GameStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    @Query(value = "from Game g where g.id = :gameId")
    Game getGameById(Long gameId);

    @Query(value = "from Game g " +
            "inner join GameGenre gg on g.id = gg.game.id " +
            "where gg.genre.id = :genreId")
    List<Game> getAllByGenreId(Long genreId);

    @Query(value = "from Game g " +
            "inner join FavouriteGames f on g.id = f.game.id " +
            "where f.user.keycloakId = :userId")
    List<Game> getFavouriteGamesByUserId(String userId);

    @Query(value = "from Game g where g.status = :status")
    List<Game> getAllByStatus(GameStatus status);
}
