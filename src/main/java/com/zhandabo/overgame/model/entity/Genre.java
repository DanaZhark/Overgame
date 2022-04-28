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
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "genre")
@ApiModel("Жанр игры")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Genre {

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "genre")
    Set<GameGenre> games;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ApiModelProperty("Логин")
    @Column(name = "code")
    private String code;
    @ApiModelProperty("Кем создан")
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", name = "name")
    private Map<String, String> name;
    @ApiModelProperty("Кем последним был редактирован")
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", name = "description")
    private Map<String, String> description;
    @ApiModelProperty("Дата создания")
    @Column(name = "img_link")
    private String imgLink;
}
