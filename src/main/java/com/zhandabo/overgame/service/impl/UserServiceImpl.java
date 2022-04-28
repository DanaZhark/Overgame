package com.zhandabo.overgame.service.impl;

import com.zhandabo.overgame.client.KeycloakClient;
import com.zhandabo.overgame.converter.RoleConverter;
import com.zhandabo.overgame.converter.UserConverter;
import com.zhandabo.overgame.exception.OvergameException;
import com.zhandabo.overgame.model.constant.ErrorCodeConstant;
import com.zhandabo.overgame.model.dto.*;
import com.zhandabo.overgame.model.entity.User;
import com.zhandabo.overgame.model.enums.RoleCode;
import com.zhandabo.overgame.repository.UserRepository;
import com.zhandabo.overgame.service.*;
import com.zhandabo.overgame.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String KEYCLOAK_GRANT_TYPE_PASSWORD = "password";
    private final KeycloakService keycloakService;
    private final CredentialService credentialService;
    private final MailService mailService;
    private final RoleService roleService;
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final RoleConverter roleConverter;
    private final KeycloakClient keycloakClient;

    @Value("${keycloak.reset-password-url}")
    private String keycloakResetPasswordUrl;
    @Value("${keycloak.realm}")
    private String realm;

    @Override
    public void create(UserInfoDto userInfoDto) {
        if (Boolean.FALSE.equals(keycloakService.isFreeKeycloakUsername(userInfoDto.getEmail())) ||
                Boolean.TRUE.equals(userRepository.existsByEmail(userInfoDto.getEmail()))) {
            throw new OvergameException(HttpStatus.BAD_REQUEST, ErrorCodeConstant.USER_EMAIL_ALREADY_EXIST,
                    "messages.exception.user-email-already-exist"
            );
        }

        if (!isRoleAvailable(userInfoDto.getRoleCode())) {
            throw new OvergameException(HttpStatus.BAD_REQUEST, ErrorCodeConstant.INSUFFICIENT_PERMISSION,
                    "messages.exception.insufficient-permission"
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
            log.error("registerResponse: ", e);
            if (Objects.nonNull(keycloakId)) {
                keycloakService.deleteUserByKeycloakIdIfExists(keycloakId);
                userRepository.delete(Objects.requireNonNull(newUser));
            }
            throw new OvergameException(HttpStatus.BAD_REQUEST, ErrorCodeConstant.CREATE_KEYCLOAK_USER_ERROR,
                    "messages.exception.create-keycloak-user-error"
            );
        }
    }

    @Override
    @Transactional
    public User getCurrentUser() {
        return userRepository.getByKeycloakId(JwtUtils.getKeycloakId());
    }

    public List<RoleDto> getAvailableRoles() {
        List<RoleDto> availableRoles = new ArrayList<>();
        availableRoles.add(roleConverter.convert(roleService.getByRoleCode(RoleCode.OVERGAME_ADMIN_ROLE)));
        availableRoles.add(roleConverter.convert(roleService.getByRoleCode(RoleCode.OVERGAME_USER_ROLE)));
        return availableRoles;
    }

    @Override
    @Transactional
    public KeycloakAuthResponseDto login(KeycloakAuthRequestDto keycloakAuthRequestDto) {
        return keycloakService.login(keycloakAuthRequestDto);
    }

    @Override
    @Transactional
    public KeycloakAuthResponseDto refresh(KeycloakAuthWithRefreshTokenDto keycloakAuthWithRefreshTokenDto) {
        return keycloakService.refresh(keycloakAuthWithRefreshTokenDto);
    }

    @Override
    @Transactional
    public void editUser(UserEditDto userEditDto) {

        String keycloakId = JwtUtils.getKeycloakId();

        User user = userRepository.getByKeycloakId(keycloakId);


        if (!userEditDto.getNewPassword().equals(userEditDto.getValidateNewPassword())) {
            throw new OvergameException(HttpStatus.BAD_REQUEST, ErrorCodeConstant.PASSWORD_MISMATCH,
                    "messages.exception.password-mismatch"
            );
        }

        URI uri = URI.create(String.format(keycloakResetPasswordUrl, realm, keycloakId));
        KeycloakResetPasswordRequestDto keycloakResetPasswordRequestDto = new KeycloakResetPasswordRequestDto();
        keycloakResetPasswordRequestDto.setTemporary(false);
        keycloakResetPasswordRequestDto.setType(KEYCLOAK_GRANT_TYPE_PASSWORD);
        keycloakResetPasswordRequestDto.setValue(userEditDto.getNewPassword());
        keycloakClient.resetPassword(uri, keycloakResetPasswordRequestDto);
    }

    private boolean isRoleAvailable(RoleCode roleCode) {
        return getAvailableRoles().stream().anyMatch(e -> roleCode.equals(e.getCode()));
    }
}
