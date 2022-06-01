package com.zhandabo.overgame.service;

import com.zhandabo.overgame.model.dto.PageDTO;
import com.zhandabo.overgame.model.dto.game.GameCreateDto;
import com.zhandabo.overgame.model.dto.game.GameViewDto;
import com.zhandabo.overgame.model.enums.GameStatus;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GameService {

    void create(GameCreateDto gameCreateDto);

    void edit(GameCreateDto gameCreateDto, Long gameId);

    void createGameGenre(List<Long> genreIds, Long gameId);

    PageDTO<GameViewDto> getGamesByStatus(GameStatus status, Pageable pageable);

    PageDTO<GameViewDto> getAllGamesByDeveloperId(Pageable pageable, Long userId);

    PageDTO<GameViewDto> getAllAcceptedGames(String name, List<Long> genreIds, Pageable pageable);

    void changeGameStatus(Long gameId, GameStatus status);

    GameViewDto getGameById(Long gameId);

    List<GameViewDto> getGamesByGenreId(Long genreId);

}
