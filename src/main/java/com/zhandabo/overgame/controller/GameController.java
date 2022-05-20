package com.zhandabo.overgame.controller;

import com.zhandabo.overgame.model.dto.PageDTO;
import com.zhandabo.overgame.model.dto.game.GameCreateDto;
import com.zhandabo.overgame.model.dto.game.GameViewDto;
import com.zhandabo.overgame.model.enums.GameStatus;
import com.zhandabo.overgame.service.GameService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

    @PutMapping("/edit/{gameId}")
    @ApiOperation("Редактирование игры")
    public void create(@RequestBody GameCreateDto game, @PathVariable Long gameId) {
        gameService.edit(game, gameId);
    }

    @GetMapping("/by-status")
    @ApiOperation("Получение пагинированного списка всех игр по статусу")
    public PageDTO<GameViewDto> getAllGamesByStatus(@RequestParam GameStatus status,
                                                    @PageableDefault(sort = "dateCreated", direction = Sort.Direction.DESC) Pageable pageable) {
        return gameService.getGamesByStatus(status, pageable);
    }

    @GetMapping("/search")
    @ApiOperation("Поиск игр с фильтрами")
    public PageDTO<GameViewDto> getAllGamesByStatus(
            @RequestParam String name,
            @RequestParam List<Long> genreIds,
            @PageableDefault(sort = "dateCreated", direction = Sort.Direction.DESC) Pageable pageable) {
        return gameService.getAllAcceptedGames(name, genreIds, pageable);
    }

    @GetMapping("/{gameId}")
    @ApiOperation("Получение игры по айди")
    public GameViewDto getGameById(@PathVariable("gameId") Long gameId) {
        return gameService.getGameById(gameId);
    }

    @PutMapping("/moderator/{gameId}")
    @ApiOperation("Изменение статуса игры от лица модератора")
    public void changeGameStatus(@PathVariable("gameId") Long gameId,
                                 @RequestParam GameStatus status) {
        gameService.changeGameStatus(gameId, status);
    }

    @GetMapping("/genres/{genreId}")
    @ApiOperation("Получение списка игр по жанру")
    public List<GameViewDto> getGamesByGenre(@PathVariable Long genreId) {
        return gameService.getGamesByGenreId(genreId);
    }

}
