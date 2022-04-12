package com.zhandabo.overgame.client;

import com.zhandabo.overgame.config.OvergameFeignConfig;
import com.zhandabo.overgame.model.dto.KeycloakResetPasswordRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.net.URI;

@FeignClient(value = "${keycloak.value}", url = "${keycloak.reset-password-url}", configuration = OvergameFeignConfig.class)
public interface KeycloakClient {
    @PutMapping
    void resetPassword(URI baserUrl, @Valid @RequestBody KeycloakResetPasswordRequestDto keycloakResetPasswordRequestDto);
}