package com.zhandabo.overgame.model.dto;

import com.sun.istack.NotNull;
import com.zhandabo.overgame.model.enums.RoleCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

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

    @NotNull
    @ApiModelProperty("Роль пользователя")
    private RoleCode roleCode;

    @ApiModelProperty("Ссылка на картинку игры")
    private MultipartFile imgFile;
}
