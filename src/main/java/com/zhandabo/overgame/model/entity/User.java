package com.zhandabo.overgame.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zhandabo.overgame.model.enums.RoleCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
@ApiModel("Пользователь")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "keycloak_id")
    private String keycloakId;
    @ApiModelProperty("Логин")
    @Column(name = "username")
    private String username;
    @ApiModelProperty("Электронная почта")
    @Column(name = "email")
    private String email;
    @ApiModelProperty("Дата создания")
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "birthday_date")
    private LocalDate dateOfBirth;
    @ApiModelProperty("URL аватарки")
    @Column(name = "avatar_url")
    private String avatarUrl;
    @Column(name = "role_code")
    private RoleCode role;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    @JsonIgnore
    private Set<FavouriteGames> favouriteGames;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    @JsonIgnore
    private Set<FavouriteDevelopers> favouriteDevelopers;
}
