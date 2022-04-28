package com.zhandabo.overgame.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<UserRole> roles;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    @JsonIgnore
    private Set<Favourite> favourites;
}
