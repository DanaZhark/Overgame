package com.zhandabo.overgame.service;

import com.zhandabo.overgame.model.dto.game.GameCreateDto;
import com.zhandabo.overgame.model.dto.game.GameViewDto;

import java.util.List;

public interface GameService {

    void create(GameCreateDto gameCreateDto);

    void edit(GameCreateDto gameCreateDto, Long gameId);

    void createGameGenre(List<Long> genreIds, Long gameId);

    List<GameViewDto> getAllGames();

    GameViewDto getGameById(Long gameId);

    List<GameViewDto> getGamesByGenreId(Long genreId);
}
