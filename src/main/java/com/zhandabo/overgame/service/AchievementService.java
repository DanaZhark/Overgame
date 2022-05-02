package com.zhandabo.overgame.service;

import com.zhandabo.overgame.model.dto.achievement.AchievementCreateDto;
import com.zhandabo.overgame.model.dto.achievement.AchievementTypeCreateDto;
import com.zhandabo.overgame.model.dto.achievement.AchievementTypeViewDto;
import com.zhandabo.overgame.model.dto.achievement.AchievementViewDto;

import java.util.List;

public interface AchievementService {

    void createAchievementType(AchievementTypeCreateDto achievementType);

    List<AchievementTypeViewDto> getAllAchievementTypes();

    void createAchievement(AchievementCreateDto createDto);

    List<AchievementViewDto> getAllAchievements();
}
