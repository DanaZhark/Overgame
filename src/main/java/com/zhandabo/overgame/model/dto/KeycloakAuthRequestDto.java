package com.zhandabo.overgame.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ApiModel("Объект передачи данных для авторизации пользователя")
public class KeycloakAuthRequestDto {

    @NotBlank
    @ApiModelProperty("Имя пользователя для keycloak")
    private String username;

    @NotBlank
    @ApiModelProperty("Пароль для keycloak пользователя")
    private String password;
}
