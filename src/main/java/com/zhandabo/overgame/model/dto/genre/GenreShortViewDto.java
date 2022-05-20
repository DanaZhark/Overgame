package com.zhandabo.overgame.model.dto.genre;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Короткие данные игры для отображения")
public class GenreShortViewDto {

    @ApiModelProperty("ID жанра")
    private Long id;

    @ApiModelProperty("Название жанра")
    private String name;

    @ApiModelProperty("Код жанра")
    private String code;
}
