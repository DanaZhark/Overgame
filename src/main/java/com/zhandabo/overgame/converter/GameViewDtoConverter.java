package com.zhandabo.overgame.converter;

import com.zhandabo.overgame.model.dto.game.GameViewDto;
import com.zhandabo.overgame.model.entity.Game;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GameViewDtoConverter implements Converter<Game, GameViewDto> {
    @Override
    public GameViewDto convert(Game source) {
        GameViewDto target = new GameViewDto();
        target.setId(source.getId());
        target.setName(source.getName().get("en"));
        target.setDescription(source.getDescription().get("en"));
        target.setImgLink(source.getImgLink());
        target.setPrice(source.getPrice());
        return target;
    }
}
