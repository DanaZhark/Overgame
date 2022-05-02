package com.zhandabo.overgame.model.dto.achievement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Данные игры для создания")
public class AchievementViewDto {

    @ApiModelProperty("ID игры")
    private Long id;

    private String code;

    @ApiModelProperty("Название игры")
    private String name;

    @ApiModelProperty("Описание игры")
    private String description;

    private String imgLink;

    private Long achievementTypeId;
}
