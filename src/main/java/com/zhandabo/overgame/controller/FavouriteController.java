package com.zhandabo.overgame.controller;

import com.zhandabo.overgame.model.dto.game.GameViewDto;
import com.zhandabo.overgame.model.dto.user.UserShortViewDto;
import com.zhandabo.overgame.service.FavouriteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/favourite")
@Api(value = "Методы для работы со списком избранное")
public class FavouriteController {

    private final FavouriteService favouriteService;

    @PostMapping("/games/{gameId}")
    @ApiOperation("Добавление игры в список любимых игр")
    public void addGameToFavorite(@PathVariable("gameId") Long gameId) {
        favouriteService.addGameToFavourite(gameId);
    }

    @GetMapping("/games")
    @ApiOperation("Получение списка любимых игр пользователя")
    public List<GameViewDto> getUserFavouriteGames() {
        return favouriteService.getUserFavouriteGames();
    }

    @DeleteMapping("/games/{gameId}")
    @ApiOperation("Удаление игры из списка любимых игр")
    public void removeGameFromFavorite(@PathVariable("gameId") Long gameId) {
        favouriteService.removeGameFromFavorite(gameId);
    }

    @PostMapping("/developer/{developerId}")
    @ApiOperation("Добавление разработчика в список любимых разработчиков")
    public void addDeveloperToFavorite(@PathVariable("developerId") Long developerId) {
        favouriteService.addDeveloperToFavourite(developerId);
    }

    @GetMapping("/developer")
    @ApiOperation("Получение списка любимых разработчиков пользователя")
    public List<UserShortViewDto> getUserFavouriteDevelopers() {
        return favouriteService.getUserFavouriteDevelopers();
    }

    @DeleteMapping("/developer/{developerId}")
    @ApiOperation("Удаление разработчика из списка любимых разработчиков")
    public void removeDeveloperFromFavorite(@PathVariable("developerId") Long developerId) {
        favouriteService.removeDeveloperFromFavorite(developerId);
    }
}
