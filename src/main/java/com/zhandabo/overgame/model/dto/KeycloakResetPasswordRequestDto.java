package com.zhandabo.overgame.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ApiModel("Параметры для изменения пароля keycloak user'a")
public class KeycloakResetPasswordRequestDto {

    @ApiModelProperty("Тип учетных данных")
    private String type;

    @ApiModelProperty("Временная защита")
    private Boolean temporary;

    @NotBlank
    @ApiModelProperty("Новое значение")
    private String value;
}
