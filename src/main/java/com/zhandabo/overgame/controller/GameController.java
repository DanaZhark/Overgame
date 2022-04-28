package com.zhandabo.overgame.controller;

import com.zhandabo.overgame.model.dto.game.GameCreateDto;
import com.zhandabo.overgame.model.dto.game.GameViewDto;
import com.zhandabo.overgame.service.GameService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/games")
@Api(value = "Методы для работы с играми")
public class GameController {

    private final GameService gameService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation("Создание игры")
    public void create(@RequestPart GameCreateDto game,
                       @RequestPart(value = "imgFile") MultipartFile imgFile) {
        game.setImgFile(imgFile);
        gameService.create(game);
    }

    @PutMapping("/{gameId}")
    @ApiOperation("Редактирование")
    public void create(@RequestBody GameCreateDto gameCreateDto, @PathVariable Long gameId) {
        gameService.edit(gameCreateDto, gameId);
    }

    @GetMapping
    @ApiOperation("Получение списка всех игр")
    public List<GameViewDto> getAllGames() {
        return gameService.getAllGames();
    }

    @GetMapping("/{gameId}")
    @ApiOperation("Получение игры по айди")
    public GameViewDto getGameById(@PathVariable("gameId") Long gameId) {
        return gameService.getGameById(gameId);
    }

    @GetMapping("/genres/{genreId}")
    @ApiOperation("Получение списка игр по жанру")
    public List<GameViewDto> getGamesByGenre(@PathVariable Long genreId) {
        return gameService.getGamesByGenreId(genreId);
    }

}
