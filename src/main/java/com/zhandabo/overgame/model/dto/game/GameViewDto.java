package com.zhandabo.overgame.model.dto.game;

import com.zhandabo.overgame.model.dto.genre.GenreShortViewDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@ApiModel("Данные игры для создания")
public class GameViewDto {

    @ApiModelProperty("ID игры")
    private Long id;

    @ApiModelProperty("Название игры")
    private String name;

    @ApiModelProperty("Описание игры")
    private String description;

    @ApiModelProperty("Ссылка на игру")
    private String gameLink;

    @ApiModelProperty("Ссылка на игру")
    private String imgLink;

    @ApiModelProperty("Цена")
    private BigDecimal price;

    @ApiModelProperty("Список id жанров")
    private List<GenreShortViewDto> genres;

    @ApiModelProperty("Ссылка на игру")
    private String creatorName;
}
