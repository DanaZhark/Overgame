package com.zhandabo.overgame.model.dto.genre;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Данные игры для создания")
public class GenreViewDto {

    @ApiModelProperty("ID игры")
    private Long id;

    private String code;

    @ApiModelProperty("Название игры")
    private String name;

    @ApiModelProperty("Описание игры")
    private String description;

    private String imgLink;
}
