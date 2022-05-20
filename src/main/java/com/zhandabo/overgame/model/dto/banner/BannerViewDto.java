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

    @ApiModelProperty("Название банера")
    private String name;

    @ApiModelProperty("Описание банера")
    private String description;

    @ApiModelProperty("Ссылка на картинку банера")
    private String imgUrl;

    @ApiModelProperty("Код банера")
    private BannerCode code;
}
