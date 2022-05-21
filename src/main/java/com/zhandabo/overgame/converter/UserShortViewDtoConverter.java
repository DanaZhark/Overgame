package com.zhandabo.overgame.converter;

import com.zhandabo.overgame.model.dto.user.UserShortViewDto;
import com.zhandabo.overgame.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserShortViewDtoConverter implements Converter<User, UserShortViewDto> {

    @Override
    public UserShortViewDto convert(User source) {
        UserShortViewDto target = new UserShortViewDto();
        target.setId(source.getId());
        target.setUsername(source.getUsername());
        target.setImg(source.getAvatarUrl());
        return target;
    }
}
