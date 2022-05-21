package com.zhandabo.overgame.converter;

import com.zhandabo.overgame.model.dto.user.UserCreateDto;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserRepresentationConverter implements Converter<UserCreateDto, UserRepresentation> {

    @Override
    public UserRepresentation convert(UserCreateDto source) {
        UserRepresentation target = new UserRepresentation();
        target.setUsername(source.getUsername());
        target.setEmail(source.getEmail());
        return target;
    }
}
