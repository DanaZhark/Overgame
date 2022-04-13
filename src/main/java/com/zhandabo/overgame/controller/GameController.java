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
    public void register(@RequestBody GameCreateDto gameCreateDto) {
        gameService.create(gameCreateDto);
    }

    @GetMapping
    @ApiOperation("Получение всех игр")
    public List<GameViewDto> register() {
        return gameService.getAll();
    }

    @GetMapping
    @ApiOperation("Получение игр по жанру")
    public List<GameViewDto> register(@RequestParam Long genreId) {
        return gameService.getByGenreId(genreId);
    }
}
