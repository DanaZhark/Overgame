package com.zhandabo.overgame.converter;

import com.zhandabo.overgame.model.dto.genre.GenreShortViewDto;
import com.zhandabo.overgame.model.entity.Genre;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GenreShortViewDtoConverter implements Converter<Genre, GenreShortViewDto> {
    @Override
    public GenreShortViewDto convert(Genre source) {
        GenreShortViewDto target = new GenreShortViewDto();
        target.setId(source.getId());
        target.setName(source.getName().get("en"));
        target.setCode(source.getCode());
        return target;
    }
}
