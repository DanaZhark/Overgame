package com.zhandabo.overgame.model.dto.genre;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Map;

@Getter
@Setter
@ApiModel("Данные жанра для создания")
public class GenreCreateDto {

    @NotBlank
    @Pattern(regexp = "[A-Z]+", message = "Код должен быть на латинице")
    @ApiModelProperty("Код")
    private String code;

    @NotBlank
    @ApiModelProperty("Название жанра на англ и рус")
    private Map<String, String> name;

    @NotNull
    @ApiModelProperty("Описание жанра на англ и рус")
    private Map<String, String> description;

    @ApiModelProperty("Ссылка на картинку жанра")
    private String imgLink;
}
