package com.zhandabo.overgame.controller;

import com.zhandabo.overgame.model.dto.game.GameViewDto;
import com.zhandabo.overgame.service.FavouriteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/favourite")
@Api(value = "Методы для работы с играми")
public class FavouriteController {

    private final FavouriteService favouriteService;

    @PostMapping("/games/{gameId}")
    @ApiOperation("Добавление игры в список любимых игр")
    public void addGameToFavorite(@PathVariable("gameId") Long gameId) {
        favouriteService.addGameToFavourite(gameId);
    }

    @GetMapping
    @ApiOperation("Получение списка любимых игр пользователя")
    public List<GameViewDto> getUserFavouriteGames() {
        return favouriteService.getUserFavouriteGames();
    }

    @DeleteMapping("/games/{gameId}")
    @ApiOperation("Удаление игры из списка любимых игр")
    public void removeGameFromFavorite(@PathVariable("gameId") Long gameId) {
        favouriteService.removeGameFromFavorite(gameId);
    }
}
