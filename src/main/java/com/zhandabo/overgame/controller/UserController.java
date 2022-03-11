package com.zhandabo.overgame.controller;

import com.zhandabo.overgame.model.dto.KeycloakAuthRequestDto;
import com.zhandabo.overgame.model.dto.KeycloakAuthResponseDto;
import com.zhandabo.overgame.model.dto.UserInfoDto;
import com.zhandabo.overgame.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
@Api(value = "Методы для работы с пользователями")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    @ApiOperation("Регистрация")
    public void register(@RequestBody UserInfoDto userInfoDto) {
        userService.create(userInfoDto);
    }

    @PostMapping("/login")
    @ApiOperation("Получить токен доступа")
    public KeycloakAuthResponseDto login(@Valid @RequestBody KeycloakAuthRequestDto keycloakAuthRequestDto) {
        return userService.login(keycloakAuthRequestDto);
    }
}
