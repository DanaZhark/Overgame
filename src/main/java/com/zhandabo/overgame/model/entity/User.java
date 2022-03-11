package com.zhandabo.overgame.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

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

    @ApiModelProperty("Кем создан")
    @Column(name = "created_by")
    private String createdBy;

    @ApiModelProperty("Кем последним был редактирован")
    @Column(name = "updated_by")
    private String updatedBy;

    @ApiModelProperty("Дата создания")
    @Column(name = "created_date")
    private Date createdDate;

    @ApiModelProperty("Дата последнего редактирования")
    @Column(name = "updated_date")
    private Date updatedDate;
}
