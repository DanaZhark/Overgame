package com.zhandabo.overgame.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Объект передачи данных для получения информации для авторизации c наличием даты истечения срока действия токена")
public class KeycloakAuthResponseDto {
    private String accessToken;

    private Integer expiresIn;

    private Integer refreshExpiresIn;

    private String refreshToken;

    private String refreshTokenExpDate;

    private String tokenType;

    private String sessionState;

    private String scope;
}
