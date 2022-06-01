package com.zhandabo.overgame.controller;

import com.zhandabo.overgame.model.dto.KeycloakAuthRequestDto;
import com.zhandabo.overgame.model.dto.KeycloakAuthResponseDto;
import com.zhandabo.overgame.model.dto.KeycloakAuthWithRefreshTokenDto;
import com.zhandabo.overgame.model.dto.user.UserCreateDto;
import com.zhandabo.overgame.model.dto.user.UserEditDto;
import com.zhandabo.overgame.model.dto.user.UserShortViewDto;
import com.zhandabo.overgame.model.dto.user.UserViewDto;
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
    @ApiOperation("Регистрация пользователя")
    public void register(@RequestPart UserCreateDto user,
                         @RequestPart(value = "imgFile") MultipartFile imgFile) {
        user.setImgFile(imgFile);
        userService.create(user);
    }

    @PostMapping("/login")
    @ApiOperation("Получить токен доступа")
    public KeycloakAuthResponseDto login(@Valid @RequestBody KeycloakAuthRequestDto keycloakAuthRequestDto) {
        return userService.login(keycloakAuthRequestDto);
    }

    @PostMapping("/refresh")
    @ApiOperation("Обновить токен доступа")
    public KeycloakAuthResponseDto refresh(@Valid @RequestBody KeycloakAuthWithRefreshTokenDto keycloakAuthWithRefreshTokenDto) {
        return userService.refresh(keycloakAuthWithRefreshTokenDto);
    }

    @GetMapping("/me")
    @ApiOperation("Получить информацию о текущем пользователе")
    public UserViewDto getMe() {
        return userService.getCurrentUser();
    }

    @GetMapping("/{userId}")
    @ApiOperation("Получить информацию о пользователе по айди")
    public UserViewDto getMe(@PathVariable("userId") Long userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("/edit")
    @ApiOperation("Редактирование данных пользователя")
    public void editUser(@Valid @RequestBody UserEditDto userEditDto) {
        userService.editUser(userEditDto);
    }

    @GetMapping("/developers")
    @ApiOperation("Получить список всех разработчиков")
    public List<UserShortViewDto> getDevelopers() {
        return userService.getDevelopers();
    }

}
