package com.zhandabo.overgame.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "genre")
@ApiModel("Жанр игры")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty("Логин")
    @Column(name = "code")
    private String code;

    @ApiModelProperty("Кем создан")
    @Column(name = "name")
    private String name;

    @ApiModelProperty("Кем последним был редактирован")
    @Column(name = "description")
    private String description;

    @ApiModelProperty("Дата создания")
    @Column(name = "img_link")
    private String imgLink;
}
