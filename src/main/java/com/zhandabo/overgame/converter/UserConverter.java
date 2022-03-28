package com.zhandabo.overgame.converter;

import com.zhandabo.overgame.model.dto.UserInfoDto;
import com.zhandabo.overgame.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class UserConverter implements Converter<UserInfoDto, User> {

    @Override
    public User convert(UserInfoDto source) {
        User target = new User();
        target.setUsername(source.getUsername());
        target.setEmail(source.getEmail());
        target.setCreatedDate(new Date());
        return target;
    }
}
