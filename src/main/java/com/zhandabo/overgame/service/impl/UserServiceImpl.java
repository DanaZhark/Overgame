package com.zhandabo.overgame.service.impl;

import com.zhandabo.overgame.converter.UserConverter;
import com.zhandabo.overgame.exception.OvergameException;
import com.zhandabo.overgame.model.constant.ErrorCodeConstant;
import com.zhandabo.overgame.model.dto.KeycloakAuthRequestDto;
import com.zhandabo.overgame.model.dto.KeycloakAuthResponseDto;
import com.zhandabo.overgame.model.dto.UserInfoDto;
import com.zhandabo.overgame.model.entity.User;
import com.zhandabo.overgame.repository.UserRepository;
import com.zhandabo.overgame.service.CredentialService;
import com.zhandabo.overgame.service.KeycloakService;
import com.zhandabo.overgame.service.MailService;
import com.zhandabo.overgame.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final KeycloakService keycloakService;
    private final CredentialService credentialService;
    private final MailService mailService;
    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Override
    public void create(UserInfoDto userInfoDto) {
        if (Boolean.FALSE.equals(keycloakService.isFreeKeycloakUsername(userInfoDto.getEmail())) ||
                Boolean.TRUE.equals(userRepository.existsByEmail(userInfoDto.getEmail()))) {
            throw new OvergameException(HttpStatus.BAD_REQUEST, ErrorCodeConstant.USER_EMAIL_ALREADY_EXIST,
                    "messages.exception.user-email-already-exist"
            );
        }

        User newUser = userConverter.convert(userInfoDto);

        String keycloakId = null;

        try {
            String password = credentialService.generatePassword();
            keycloakId = keycloakService.createUserAndGetKeycloakId(userInfoDto, password);
            Objects.requireNonNull(newUser).setKeycloakId(keycloakId);
            userRepository.save(newUser);
            mailService.sendRegistrationMessage(userInfoDto, password);
        } catch (Exception e) {
            if (Objects.nonNull(keycloakId)) {
                keycloakService.deleteUserByKeycloakIdIfExists(keycloakId);
            }
            throw new OvergameException(HttpStatus.BAD_REQUEST, ErrorCodeConstant.CREATE_KEYCLOAK_USER_ERROR,
                    "messages.exception.create-keycloak-user-error"
            );
        }
    }

    @Override
    @Transactional
    public KeycloakAuthResponseDto login(KeycloakAuthRequestDto keycloakAuthRequestDto) {
        return keycloakService.login(keycloakAuthRequestDto);
    }
}
