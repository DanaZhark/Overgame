package com.zhandabo.overgame.model.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import com.zhandabo.overgame.model.enums.RoleCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@ApiModel("Данные юзера")
public class UserViewDto {

    private Long id;
    @ApiModelProperty("Дата рождения")
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate dateOfBirth;
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
    @ApiModelProperty("Ссылка на аватарку пользователя")
    private String imgUrl;
}
