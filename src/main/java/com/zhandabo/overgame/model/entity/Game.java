package com.zhandabo.overgame.model.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@Table(name = "game")
@ApiModel("Игра")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty("Название игры")
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", name = "name")
    private Map<String, String> name;

    @ApiModelProperty("Описание игры")
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", name = "description")
    private Map<String, String> description;

    @ApiModelProperty("Рейтинг игры")
    @Column(name = "rating")
    private BigDecimal rating = BigDecimal.valueOf(0);

    @ApiModelProperty("Ссылка на игру")
    @Column(name = "game_link")
    private String gameLink;

    @ApiModelProperty("Ссылка на картинку игры")
    @Column(name = "img_link")
    private String imgLink;

    @ApiModelProperty("Цена игры")
    @Column(name = "price")
    private BigDecimal price;

    @ApiModelProperty("Дата создания")
    @Column(name = "date_created")
    private Date dateCreated;

    @ApiModelProperty("ID создателя")
    @Column(name = "creator_id")
    private String creatorId;

    @ApiModelProperty("ID модератора")
    @Column(name = "moderator_id")
    private String moderatorId;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "game")
    Set<GameGenre> genres;
}
