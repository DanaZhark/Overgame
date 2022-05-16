package com.zhandabo.overgame.model.dto.banner;

import com.zhandabo.overgame.model.enums.BannerCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Getter
@Setter
@ApiModel("Данные игры для банера")
public class BannerCreateDto {

    @NotBlank
    @ApiModelProperty("Название игры")
    private Map<String, String> name;

    @NotNull
    @ApiModelProperty("Описание игры")
    private Map<String, String> description;

    @ApiModelProperty("Ссылка на картинку игры")
    private MultipartFile imgFile;

    @NotNull
    @ApiModelProperty("Список id жанров")
    private BannerCode code;
}
