package com.zhandabo.overgame.model.entity;

import com.zhandabo.overgame.model.enums.RoleCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("ID этой записи в БД")
    private Long id;

    @ApiModelProperty("Отображаемое название кода")
    private String name;

    @ApiModelProperty("Описание кода")
    private String description;

    @ApiModelProperty("Код уникальный код (на него можно зашиваться)")
    @Enumerated(EnumType.STRING)
    private RoleCode code;
}
