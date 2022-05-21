package com.zhandabo.overgame.converter;

import com.zhandabo.overgame.model.dto.user.UserCreateDto;
import com.zhandabo.overgame.model.entity.User;
import com.zhandabo.overgame.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class UserConverter implements Converter<UserCreateDto, User> {

    private final StorageService storageService;

    private final String uploadPath = "https://overgame.s3.us-west-2.amazonaws.com/profile-ava/";

    @Override
    public User convert(UserCreateDto source) {
        User target = new User();
        target.setUsername(source.getUsername());
        target.setEmail(source.getEmail());
        target.setCreatedDate(new Date());
        target.setRole(source.getRoleCode());
        target.setDateOfBirth(source.getDateOfBirth());

        storageService.uploadFile(source.getImgFile(), "profile-ava");

        target.setAvatarUrl(uploadPath + source.getImgFile().getOriginalFilename());
        return target;
    }
}
