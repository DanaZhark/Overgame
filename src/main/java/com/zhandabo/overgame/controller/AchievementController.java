package com.zhandabo.overgame.controller;

import com.zhandabo.overgame.model.dto.achievement.AchievementCreateDto;
import com.zhandabo.overgame.model.dto.achievement.AchievementViewDto;
import com.zhandabo.overgame.service.AchievementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/achievement")
@Api(value = "Методы для работы с достижениями")
public class AchievementController {

    private final AchievementService achievementService;

    @PostMapping
    @ApiOperation("Создание типа достижения")
    public void create(@RequestBody AchievementCreateDto createDto) {
        achievementService.createAchievement(createDto);
    }

    @GetMapping
    @ApiOperation("Получение всех типов достижений")
    public List<AchievementViewDto> getAllGenres() {
        return achievementService.getAllAchievements();
    }
}
