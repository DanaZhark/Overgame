package com.zhandabo.overgame.controller;

import com.zhandabo.overgame.model.dto.*;
import com.zhandabo.overgame.model.entity.User;
import com.zhandabo.overgame.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
@Api(value = "Методы для работы с пользователями")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    @ApiOperation("Регистрация")
    public void register(@RequestPart UserInfoDto userInfoDto,
                         @RequestPart(value = "imgFile") MultipartFile imgFile) {
        userInfoDto.setImgFile(imgFile);
        userService.create(userInfoDto);
    }

    @PostMapping("/login")
    @ApiOperation("Получить токен доступа")
    public KeycloakAuthResponseDto login(@Valid @RequestBody KeycloakAuthRequestDto keycloakAuthRequestDto) {
        return userService.login(keycloakAuthRequestDto);
    }

    @GetMapping("/me")
    @ApiOperation("Получить информацию о текущем пользователе")
    public User getMe() {
        return userService.getCurrentUser();
    }

    @PostMapping("/refresh")
    @ApiOperation("Обновить токен доступа")
    public KeycloakAuthResponseDto refresh(@Valid @RequestBody KeycloakAuthWithRefreshTokenDto keycloakAuthWithRefreshTokenDto) {
        return userService.refresh(keycloakAuthWithRefreshTokenDto);
    }

    @PutMapping("/edit")
    @ApiOperation("Редактирование данных пользователя")
    public void editUser(@Valid @RequestBody UserEditDto userEditDto) {
        userService.editUser(userEditDto);
    }

    @GetMapping("/developers")
    @ApiOperation("Получить информацию о текущем пользователе")
    public List<UserViewDto> getDevelopers() {
        return userService.getDevelopers();
    }

}
