package com.zhandabo.overgame.converter;

import com.zhandabo.overgame.model.dto.genre.GenreViewDto;
import com.zhandabo.overgame.model.entity.Genre;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GenreViewDtoConverter implements Converter<Genre, GenreViewDto> {
    @Override
    public GenreViewDto convert(Genre source) {
        GenreViewDto target = new GenreViewDto();
        target.setId(source.getId());
        target.setCode(source.getCode());
        target.setName(source.getName().get("en"));
        target.setDescription(source.getDescription().get("en"));
        target.setImgLink(source.getImgLink());
        return target;
    }
}
