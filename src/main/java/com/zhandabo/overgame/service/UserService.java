package com.zhandabo.overgame.service;

import com.zhandabo.overgame.model.dto.KeycloakAuthRequestDto;
import com.zhandabo.overgame.model.dto.KeycloakAuthResponseDto;
import com.zhandabo.overgame.model.dto.KeycloakAuthWithRefreshTokenDto;
import com.zhandabo.overgame.model.dto.user.UserEditDto;
import com.zhandabo.overgame.model.dto.user.UserInfoDto;
import com.zhandabo.overgame.model.dto.user.UserViewDto;
import com.zhandabo.overgame.model.entity.User;

import java.util.List;

public interface UserService {

    void create(UserInfoDto userInfoDto);

    KeycloakAuthResponseDto login(KeycloakAuthRequestDto keycloakAuthRequestDto);

    User getCurrentUser();

    List<UserViewDto> getDevelopers();

    KeycloakAuthResponseDto refresh(KeycloakAuthWithRefreshTokenDto keycloakAuthWithRefreshTokenDto);

    void editUser(UserEditDto userEditDto);


}
