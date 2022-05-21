package com.zhandabo.overgame.converter;

import com.zhandabo.overgame.model.dto.user.UserViewDto;
import com.zhandabo.overgame.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserViewDtoConverter implements Converter<User, UserViewDto> {

    @Override
    public UserViewDto convert(User source) {
        UserViewDto target = new UserViewDto();
        target.setId(source.getId());
        target.setUsername(source.getUsername());
        target.setEmail(source.getEmail());
        target.setDateOfBirth(source.getDateOfBirth());
        target.setRoleCode(source.getRole());
        target.setImgUrl(source.getAvatarUrl());
        return target;
    }
}
