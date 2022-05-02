package com.zhandabo.overgame.model.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
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
@Table(name = "achievement")
@ApiModel("Достижения игрока")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty("Код")
    @Column(name = "code")
    private String code;

    @ApiModelProperty("Название")
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", name = "name")
    private Map<String, String> name;

    @ApiModelProperty("Описание")
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", name = "description")
    private Map<String, String> description;

    @ApiModelProperty("Путь картинки")
    @Column(name = "img_link")
    private String imgLink;

    @ApiModelProperty("Тип достижения")
    @Column(name = "achievement_type_id")
    private Long achievementTypeId;
}