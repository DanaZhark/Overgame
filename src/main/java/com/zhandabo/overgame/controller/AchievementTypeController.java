package com.zhandabo.overgame.controller;

import com.zhandabo.overgame.model.dto.achievement.AchievementTypeCreateDto;
import com.zhandabo.overgame.model.dto.achievement.AchievementTypeViewDto;
import com.zhandabo.overgame.service.AchievementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/achievement-type")
@Api(value = "Методы для работы с типами достижений")
public class AchievementTypeController {

    private final AchievementService achievementService;

    @PostMapping
    @ApiOperation("Создание типа достижения")
    public void create(@RequestBody AchievementTypeCreateDto achievementType) {
        achievementService.createAchievementType(achievementType);
    }

    @GetMapping
    @ApiOperation("Получение всех типов достижений")
    public List<AchievementTypeViewDto> getAllGenres() {
        return achievementService.getAllAchievementTypes();
    }
}
