package com.zhandabo.overgame.model.dto.genre;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Данные жанра для отображения")
public class GenreViewDto {

    @ApiModelProperty("ID жанра")
    private Long id;

    @ApiModelProperty("Код жанра")
    private String code;

    @ApiModelProperty("Название жанра")
    private String name;

    @ApiModelProperty("Описание жанра")
    private String description;

    @ApiModelProperty("Изображение жанра")
    private String imgLink;
}
