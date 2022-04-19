package com.zhandabo.overgame.controller;


import com.zhandabo.overgame.model.dto.genre.GenreCreateDto;
import com.zhandabo.overgame.model.dto.genre.GenreViewDto;
import com.zhandabo.overgame.service.GenreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/genres")
@Api(value = "Методы для работы с жанрами")
public class GenreController {

    private final GenreService genreService;

    @PostMapping
    @ApiOperation("Создание")
    public void create(@RequestBody GenreCreateDto genre) {
        genreService.create(genre);
    }

    @GetMapping
    @ApiOperation("Получение всех жанров")
    public List<GenreViewDto> getAllGenres() {
        return genreService.getAll();
    }

    @GetMapping("/games/{gameId}")
    @ApiOperation("Получение жанров игры по айди игры")
    public List<GenreViewDto> getAllGenres(@PathVariable Long gameId) {
        return genreService.getGenresByGameId(gameId);
    }
}
