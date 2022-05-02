package com.zhandabo.overgame.converter;

import com.zhandabo.overgame.model.dto.achievement.AchievementTypeViewDto;
import com.zhandabo.overgame.model.entity.AchievementType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AchievementTypeViewDtoConverter implements Converter<AchievementType, AchievementTypeViewDto> {
    @Override
    public AchievementTypeViewDto convert(AchievementType source) {
        AchievementTypeViewDto target = new AchievementTypeViewDto();
        target.setId(source.getId());
        target.setCode(source.getCode());
        target.setName(source.getName().get("en"));
        target.setDescription(source.getDescription().get("en"));
        target.setImgLink(source.getImgLink());
        return target;
    }
}
