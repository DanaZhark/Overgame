package com.zhandabo.overgame.model.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.zhandabo.overgame.model.enums.BannerCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "banner")
@ApiModel("Банер")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty("Название")
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", name = "name")
    private Map<String, String> name;

    @ApiModelProperty("Описание")
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", name = "description")
    private Map<String, String> description;

    @ApiModelProperty("Путь картинки")
    @Column(name = "img")
    private String imgLink;

    @Enumerated(EnumType.STRING)
    private BannerCode code;

    @ApiModelProperty("Тип банера")
    @Column(name = "is_active")
    private boolean isActive;
}
