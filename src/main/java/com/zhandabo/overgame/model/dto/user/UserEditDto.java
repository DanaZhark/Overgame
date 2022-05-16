package com.zhandabo.overgame.model.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ApiModel("Новый пароль для пользователя")
public class UserEditDto {

    @NotBlank
    @ApiModelProperty("Логин")
    private String username;

    @NotBlank
    @ApiModelProperty("Почта")
    private String email;

    @NotBlank
    @ApiModelProperty("Новый пароль")
    private String newPassword;

    @NotBlank
    @ApiModelProperty("Подтверждение нового пароля")
    private String validateNewPassword;
}
