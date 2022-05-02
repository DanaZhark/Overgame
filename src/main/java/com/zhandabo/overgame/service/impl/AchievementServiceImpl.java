package com.zhandabo.overgame.service.impl;

import com.zhandabo.overgame.converter.AchievementTypeViewDtoConverter;
import com.zhandabo.overgame.model.dto.achievement.AchievementCreateDto;
import com.zhandabo.overgame.model.dto.achievement.AchievementTypeCreateDto;
import com.zhandabo.overgame.model.dto.achievement.AchievementTypeViewDto;
import com.zhandabo.overgame.model.dto.achievement.AchievementViewDto;
import com.zhandabo.overgame.model.entity.Achievement;
import com.zhandabo.overgame.model.entity.AchievementType;
import com.zhandabo.overgame.repository.AchievementRepository;
import com.zhandabo.overgame.repository.AchievementTypeRepository;
import com.zhandabo.overgame.service.AchievementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AchievementServiceImpl implements AchievementService {

    private final AchievementTypeRepository achievementTypeRepository;
    private final AchievementRepository achievementRepository;
    private final AchievementTypeViewDtoConverter achievementTypeViewDtoConverter;

    @Override
    public void createAchievementType(AchievementTypeCreateDto dto) {

        AchievementType achievementType = new AchievementType();

        achievementType.setCode(dto.getCode());
        achievementType.setName(dto.getName());
        achievementType.setDescription(dto.getDescription());
        achievementType.setImgLink(dto.getImgLink());

        achievementTypeRepository.save(achievementType);
    }

    @Override
    public List<AchievementTypeViewDto> getAllAchievementTypes() {
        List<AchievementTypeViewDto> genreViewDtoList = new ArrayList<>();
        List<AchievementType> achievementTypes = achievementTypeRepository.findAll();

        for (AchievementType achievementType : achievementTypes) {
            genreViewDtoList.add(achievementTypeViewDtoConverter.convert(achievementType));
        }
        return genreViewDtoList;
    }

    @Override
    public void createAchievement(AchievementCreateDto dto) {
        Achievement achievement = new Achievement();

        achievement.setCode(dto.getCode());
        achievement.setName(dto.getName());
        achievement.setDescription(dto.getDescription());
        achievement.setImgLink(dto.getImgLink());
        achievement.setAchievementTypeId(dto.getAchievementTypeId());

        achievementRepository.save(achievement);
    }

    @Override
    public List<AchievementViewDto> getAllAchievements() {
        return null;
    }
}
