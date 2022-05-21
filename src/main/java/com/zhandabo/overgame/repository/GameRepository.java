package com.zhandabo.overgame.repository;

import com.zhandabo.overgame.model.entity.Game;
import com.zhandabo.overgame.model.enums.GameStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long>, JpaSpecificationExecutor<Game> {

    @Query(value = "from Game g where g.id = :gameId")
    Game getGameById(Long gameId);

    @Query(value = "from Game g " +
            "inner join GameGenre gg on g.id = gg.game.id " +
            "where gg.genre.id = :genreId")
    List<Game> getAllByGenreId(Long genreId);

    @Query(value = "from Game g " +
            "inner join FavouriteGames f on g.id = f.game.id " +
            "where f.user.id = :userId")
    List<Game> getFavouriteGamesByUserId(Long userId);

    @Query(value = "from Game g where g.status = :status")
    Page<Game> getAllByStatus(GameStatus status, Pageable pageable);

    Page<Game> findAllByName(String name, Pageable pageable);
}
