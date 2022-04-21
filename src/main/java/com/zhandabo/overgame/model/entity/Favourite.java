package com.zhandabo.overgame.model.entity;

import io.swagger.annotations.ApiModel;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "favorite")
@ApiModel("Связь игры и жанра")
public class Favourite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "game_id")
    Game game;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
