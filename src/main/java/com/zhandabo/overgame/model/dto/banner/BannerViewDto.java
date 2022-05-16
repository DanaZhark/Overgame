package com.zhandabo.overgame.model.dto.banner;

import com.zhandabo.overgame.model.enums.BannerCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Данные игры для банера")
public class BannerViewDto {

    private Long id;

    @ApiModelProperty("Название игры")
    private String name;

    @ApiModelProperty("Описание игры")
    private String description;

    @ApiModelProperty("Ссылка на картинку игры")
    private String imgUrl;

    @ApiModelProperty("Список id жанров")
    private BannerCode code;
}
