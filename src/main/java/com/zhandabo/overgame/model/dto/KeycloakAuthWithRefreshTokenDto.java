package com.zhandabo.overgame.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ApiModel("Объект передачи данных для получения refresh token'а")
public class KeycloakAuthWithRefreshTokenDto {
    @NotBlank
    @ApiModelProperty("refresh token для обновления keycloak access token'а")
    private String refreshToken;
}
