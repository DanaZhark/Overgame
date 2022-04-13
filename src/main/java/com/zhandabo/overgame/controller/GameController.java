package com.zhandabo.overgame.controller;

import com.zhandabo.overgame.model.dto.game.GameCreateDto;
import com.zhandabo.overgame.model.dto.game.GameViewDto;
import com.zhandabo.overgame.service.GameService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/games")
@Api(value = "Методы для работы с играми")
public class GameController {

    private final GameService gameService;

    @PostMapping
    @ApiOperation("Создание")
    public void create(@RequestBody GameCreateDto gameCreateDto) {
        gameService.create(gameCreateDto);
    }

    @GetMapping
    @ApiOperation("Получение всех игр")
    public List<GameViewDto> getAllGames() {
        return gameService.getAll();
    }

    @GetMapping("/genres/{genreId}")
    @ApiOperation("Получение игр по жанру")
    public List<GameViewDto> getGamesByGenre(@PathVariable Long genreId) {
        return gameService.getByGenreId(genreId);
    }
}
