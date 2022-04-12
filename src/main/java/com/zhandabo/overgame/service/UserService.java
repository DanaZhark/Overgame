package com.zhandabo.overgame.service;

import com.zhandabo.overgame.model.dto.*;
import com.zhandabo.overgame.model.entity.User;

public interface UserService {

    void create(UserInfoDto userInfoDto);

    KeycloakAuthResponseDto login(KeycloakAuthRequestDto keycloakAuthRequestDto);

    User getCurrentUser();

    KeycloakAuthResponseDto refresh(KeycloakAuthWithRefreshTokenDto keycloakAuthWithRefreshTokenDto);

    void editUser(UserEditDto userEditDto);

}
