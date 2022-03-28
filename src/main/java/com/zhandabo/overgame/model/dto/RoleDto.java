package com.zhandabo.overgame.model.dto;

import com.zhandabo.overgame.model.enums.RoleCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDto {

    @ApiModelProperty("ID этой записи в БД")
    private Long id;

    @ApiModelProperty("Отображаемое название кода")
    private String name;

    @ApiModelProperty("Описание кода")
    private String description;

    @ApiModelProperty("Код уникальный код (на него можно зашиваться)")
    private RoleCode code;
}
