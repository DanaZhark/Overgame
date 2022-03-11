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
@Table(name = "game")
@ApiModel("Игра")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty("Логин")
    @Column(name = "name")
    private String name;

    @ApiModelProperty("Кем создан")
    @Column(name = "description")
    private String description;

    @ApiModelProperty("Кем последним был редактирован")
    @Column(name = "rating")
    private String rating;

    @ApiModelProperty("Дата создания")
    @Column(name = "game_link")
    private String gameLink;

    @ApiModelProperty("Дата последнего редактирования")
    @Column(name = "img_link")
    private String imgLink;

    @ApiModelProperty("Дата последнего редактирования")
    @Column(name = "price")
    private String price;

    @ApiModelProperty("Дата последнего редактирования")
    @Column(name = "date_created")
    private String dateCreated;

    @ApiModelProperty("Дата последнего редактирования")
    @Column(name = "genre_id")
    private String genreId;

    @ApiModelProperty("Дата последнего редактирования")
    @Column(name = "creator_id")
    private String creatorId;

    @ApiModelProperty("Дата последнего редактирования")
    @Column(name = "moderator_id")
    private String moderatorId;
}
