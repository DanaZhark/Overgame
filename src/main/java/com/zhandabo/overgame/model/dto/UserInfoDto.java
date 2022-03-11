package com.zhandabo.overgame.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ApiModel("Данные юзера")
public class UserInfoDto {

    @NotBlank
    @ApiModelProperty("Логин пользователя")
    private String username;

    @NotBlank
    @Email
    @ApiModelProperty("Почта пользователя")
    private String email;
}
