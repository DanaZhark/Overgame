package com.zhandabo.overgame.converter;

import com.zhandabo.overgame.model.dto.user.UserInfoDto;
import com.zhandabo.overgame.model.entity.User;
import com.zhandabo.overgame.util.ImgFileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class UserConverter implements Converter<UserInfoDto, User> {

    private final String uploadPath = "/home/danazharkimbayeva/Documents/IITU/overgame/src/main/resources/static/images/ava/";

    @Override
    public User convert(UserInfoDto source) {
        User target = new User();
        target.setUsername(source.getUsername());
        target.setEmail(source.getEmail());
        target.setCreatedDate(new Date());
        target.setDateOfBirth(source.getDateOfBirth());

        try {
            ImgFileUtils.saveFile(source.getImgFile(), uploadPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        target.setAvatarUrl("/static/images/games/" + source.getImgFile().getOriginalFilename());
        return target;
    }
}
