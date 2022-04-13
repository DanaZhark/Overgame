package com.zhandabo.overgame.service;

import com.zhandabo.overgame.model.dto.game.GameCreateDto;
import com.zhandabo.overgame.model.dto.game.GameViewDto;

import java.util.List;

public interface GameService {

    void create(GameCreateDto gameCreateDto);

    List<GameViewDto> getAll();

    List<GameViewDto> getByGenreId(Long genreId);
}
