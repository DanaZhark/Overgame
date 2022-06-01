package com.zhandabo.overgame.service;

import com.zhandabo.overgame.model.dto.KeycloakAuthRequestDto;
import com.zhandabo.overgame.model.dto.KeycloakAuthResponseDto;
import com.zhandabo.overgame.model.dto.KeycloakAuthWithRefreshTokenDto;
import com.zhandabo.overgame.model.dto.user.UserCreateDto;
import com.zhandabo.overgame.model.dto.user.UserEditDto;
import com.zhandabo.overgame.model.dto.user.UserShortViewDto;
import com.zhandabo.overgame.model.dto.user.UserViewDto;

import java.util.List;

public interface UserService {

    void create(UserCreateDto userCreateDto);

    KeycloakAuthResponseDto login(KeycloakAuthRequestDto keycloakAuthRequestDto);

    UserViewDto getCurrentUser();

    UserViewDto getUserById(Long userId);

    List<UserShortViewDto> getDevelopers();

    KeycloakAuthResponseDto refresh(KeycloakAuthWithRefreshTokenDto keycloakAuthWithRefreshTokenDto);

    void editUser(UserEditDto userEditDto);
}
