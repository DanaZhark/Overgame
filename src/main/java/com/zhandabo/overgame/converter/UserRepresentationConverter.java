package com.zhandabo.overgame.converter;

import com.zhandabo.overgame.model.dto.user.UserInfoDto;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserRepresentationConverter implements Converter<UserInfoDto, UserRepresentation> {

    @Override
    public UserRepresentation convert(UserInfoDto source) {
        UserRepresentation target = new UserRepresentation();
        target.setUsername(source.getEmail());
        target.setEmail(source.getEmail());
        return target;
    }
}
