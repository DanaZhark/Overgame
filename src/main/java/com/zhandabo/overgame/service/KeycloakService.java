package com.zhandabo.overgame.service;

import com.zhandabo.overgame.model.dto.KeycloakAuthRequestDto;
import com.zhandabo.overgame.model.dto.KeycloakAuthResponseDto;
import com.zhandabo.overgame.model.dto.KeycloakAuthWithRefreshTokenDto;
import com.zhandabo.overgame.model.dto.user.UserInfoDto;

public interface KeycloakService {
    String createUserAndGetKeycloakId(UserInfoDto adminRequestDto, String password);

    KeycloakAuthResponseDto login(KeycloakAuthRequestDto keycloakAuthRequestDto);

    KeycloakAuthResponseDto refresh(KeycloakAuthWithRefreshTokenDto keycloakAuthWithRefreshTokenDto);

    String getKeycloakId(String username);

    void deleteUserByKeycloakIdIfExists(String keycloakId);

    Boolean isFreeKeycloakUsername(String username);

    void setUserDisabled(String keycloakId);
}
