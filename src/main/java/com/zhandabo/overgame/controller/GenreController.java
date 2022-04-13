package com.zhandabo.overgame.controller;


import com.zhandabo.overgame.model.dto.genre.GenreViewDto;
import com.zhandabo.overgame.service.GenreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/genres")
@Api(value = "Методы для работы с жанрами")
public class GenreController {

    private final GenreService genreService;

    @GetMapping
    @ApiOperation("Получение всех жанров")
    public List<GenreViewDto> register() {
        return genreService.getAll();
    }
}
