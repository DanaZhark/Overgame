package com.zhandabo.overgame.converter;

import com.zhandabo.overgame.model.dto.KeycloakAuthResponse;
import com.zhandabo.overgame.model.dto.KeycloakAuthResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class KeycloakAuthResponseConverter implements Converter<KeycloakAuthResponse, KeycloakAuthResponseDto> {
    @Override
    public KeycloakAuthResponseDto convert(KeycloakAuthResponse source) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, source.getRefreshExpiresIn());
        String refreshTokenExpDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.getDefault(Locale.Category.FORMAT)).format(calendar.getTime());
        KeycloakAuthResponseDto target = new KeycloakAuthResponseDto();
        target.setAccessToken(source.getAccessToken());
        target.setExpiresIn(source.getExpiresIn());
        target.setRefreshExpiresIn(source.getRefreshExpiresIn());
        target.setRefreshToken(source.getRefreshToken());
        target.setRefreshTokenExpDate(refreshTokenExpDate);
        target.setTokenType(source.getTokenType());
        target.setSessionState(source.getSessionState());
        target.setScope(source.getScope());
        return target;
    }
}
