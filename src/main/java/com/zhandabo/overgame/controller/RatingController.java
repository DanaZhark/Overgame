package com.zhandabo.overgame.controller;

import com.zhandabo.overgame.service.RatingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/rating")
@Api(value = "Методы для работы с рейтингом")
public class RatingController {

    private final RatingService ratingService;

    @PostMapping("/games/{gameId}")
    @ApiOperation("Пользователь оценивает игру")
    public void create(@RequestParam BigDecimal grade,
                       @PathVariable("gameId") Long gameId) {
        ratingService.userRateGame(gameId, grade);
    }
}
