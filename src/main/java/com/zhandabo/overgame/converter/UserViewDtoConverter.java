package com.zhandabo.overgame.converter;

import com.zhandabo.overgame.model.dto.user.UserViewDto;
import com.zhandabo.overgame.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class UserViewDtoConverter implements Converter<User, UserViewDto> {

    @Override
    public UserViewDto convert(User source) {
        UserViewDto target = new UserViewDto();
        target.setId(source.getId());
        target.setUsername(source.getUsername());
        target.setEmail(source.getEmail());
        target.setDateOfBirth(source.getDateOfBirth().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        target.setRoleCode(source.getRole());
        target.setImgUrl(source.getAvatarUrl());
        return target;
    }
}
