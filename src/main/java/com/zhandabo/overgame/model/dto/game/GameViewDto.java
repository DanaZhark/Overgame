package com.zhandabo.overgame.model.dto.game;

import com.zhandabo.overgame.model.dto.genre.GenreShortViewDto;
import com.zhandabo.overgame.model.dto.user.UserShortViewDto;
import com.zhandabo.overgame.model.enums.GameStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@ApiModel("Данные игры для отображения")
public class GameViewDto {

    @ApiModelProperty("ID игры")
    private Long id;

    @ApiModelProperty("Название игры")
    private String name;

    @ApiModelProperty("Описание игры")
    private String description;

    @ApiModelProperty("Ссылка на игру")
    private String gameLink;

    @ApiModelProperty("Ссылка на изображение игры")
    private String imgLink;

    @ApiModelProperty("Цена")
    private BigDecimal price;

    @ApiModelProperty("Рейтинг")
    private BigDecimal rating;

    @ApiModelProperty("Список id жанров")
    private List<GenreShortViewDto> genres;

    @ApiModelProperty("Создатель игры")
    private UserShortViewDto creator;

    @ApiModelProperty("Модератор, принявший игру")
    private UserShortViewDto moderator;

    @ApiModelProperty("Статус игры")
    private GameStatus status;
}
