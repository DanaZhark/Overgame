package com.zhandabo.overgame.service.impl;

import com.zhandabo.overgame.converter.KeycloakAuthResponseConverter;
import com.zhandabo.overgame.converter.UserRepresentationConverter;
import com.zhandabo.overgame.exception.OvergameException;
import com.zhandabo.overgame.model.constant.ErrorCodeConstant;
import com.zhandabo.overgame.model.dto.*;
import com.zhandabo.overgame.service.KeycloakService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class KeycloakServiceImpl implements KeycloakService {

    private static final String RESPONSE_STATUS = "Keycloak user creation response status: %s | status info: %s";
    private static final String KEYCLOAK_USER_DELETED_LOG_MESSAGE = "Пользователь c ID: %s успешно удален.";
    private static final String TOKEN_PATH = "/realms/%s/protocol/openid-connect/token";
    private static final String PARAM_USERNAME = "username";
    private static final String PARAM_PASSWORD = "password";
    private static final String PARAM_REFRESH_TOKEN = "refresh_token";
    private static final String PARAM_GRANT_TYPE = "grant_type";
    private static final String PARAM_CLIENT_ID = "client_id";
    private static final String PARAM_CLIENT_SECRET = "client_secret";
    private static final String PARAM_VAL_PASSWORD_GRANT_TYPE = "password";
    private static final String PARAM_VAL_REFRESH_TOKEN_GRANT_TYPE = "refresh_token";

    private final UserRepresentationConverter userRepresentationConverter;
    private final KeycloakAuthResponseConverter keycloakAuthResponseConverter;

    @Value("${keycloak.auth-server-url}")
    private String url;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.client-id}")
    private String clientId;

    @Value("${keycloak.client-secret}")
    private String clientSecret;

    @Value("${keycloak.username}")
    private String username;

    @Value("${keycloak.password}")
    private String password;

    private Keycloak keycloak;
    private RestTemplate restTemplate;

    @PostConstruct
    public void initializeKeycloak() {
        log.info("initializeKeycloak: url={}, myRealm={}, username={}, password={}, clientId={}, clientSecret={}", url, realm, username, password, clientId, clientSecret);
        keycloak = Keycloak.getInstance(url, realm, username, password, clientId, clientSecret);
        restTemplate = new RestTemplate();
    }

    @Override
    public String createUserAndGetKeycloakId(UserInfoDto dto, String password) {
        UserRepresentation user = userRepresentationConverter.convert(dto);
        setUserRepresentationCredentials(user, password);
        RealmResource realm = keycloak.realm(this.realm);
        UsersResource usersResource = realm.users();
        try (Response response = usersResource.create(user)) {
            log.info(String.format(RESPONSE_STATUS, response.getStatus(), response.getStatusInfo()));
            if (response.getStatus() > 201) {
                throw new OvergameException(HttpStatus.BAD_REQUEST, ErrorCodeConstant.CREATE_KEYCLOAK_USER_ERROR,
                        "messages.exception.create-keycloak-user-error");
            }
        }
        String keycloakId = getKeycloakId(user.getUsername());
        UserResource userResource = usersResource.get(keycloakId);
        RoleRepresentation adminRole = realm.roles().get(dto.getRoleCode().name().toLowerCase()).toRepresentation();
        List<RoleRepresentation> roles = new ArrayList<>(List.of(adminRole));
        RoleRepresentation keycloakRole = realm.roles().get(dto.getRoleCode().name().toLowerCase()).toRepresentation();
        roles.add(keycloakRole);
        userResource.roles().realmLevel().add(roles);
        return keycloakId;
    }

    @Override
    public KeycloakAuthResponseDto login(KeycloakAuthRequestDto keycloakAuthRequestDto) {
        return keycloakAuthResponseConverter.convert(loginResponse(keycloakAuthRequestDto));
    }

    public KeycloakAuthResponse loginResponse(KeycloakAuthRequestDto keycloakAuthRequestDto) {
        log.info("loginResponse: {}", keycloakAuthRequestDto);
        MultiValueMap<String, String> parameters = constructRequestBody(keycloakAuthRequestDto);
        HttpHeaders headers = constructRequestHeaders();
        String accessTokenUrl = url + String.format(TOKEN_PATH, realm);
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(parameters, headers);
        try {
            ResponseEntity<KeycloakAuthResponse> response = restTemplate.exchange(accessTokenUrl, HttpMethod.POST, entity, KeycloakAuthResponse.class);
            log.info(String.format(RESPONSE_STATUS, response.getStatusCode(), response.getStatusCodeValue()));
            return response.getBody();
        } catch (HttpClientErrorException e) {
            log.error("loginResponse: ", e);
            throw new OvergameException(HttpStatus.BAD_REQUEST, ErrorCodeConstant.KEYCLOAK_USER_TOKEN_RESPONSE_ERROR,
                    "messages.exception.keycloak-user-token-response-error"
            );
        }
    }

    @Override
    public KeycloakAuthResponseDto refresh(KeycloakAuthWithRefreshTokenDto keycloakAuthWithRefreshTokenDto) {
        return keycloakAuthResponseConverter.convert(refreshResponse(keycloakAuthWithRefreshTokenDto));
    }

    public KeycloakAuthResponse refreshResponse(KeycloakAuthWithRefreshTokenDto keycloakAuthWithRefreshTokenDto) {
        String refreshToken = keycloakAuthWithRefreshTokenDto.getRefreshToken();
        MultiValueMap<String, String> parameters = constructRequestBody(refreshToken);
        HttpHeaders headers = constructRequestHeaders();
        String accessTokenUrl = url + String.format(TOKEN_PATH, realm);
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(parameters, headers);
        ResponseEntity<KeycloakAuthResponse> response = restTemplate.exchange(accessTokenUrl, HttpMethod.POST, entity, KeycloakAuthResponse.class);
        log.info(String.format(RESPONSE_STATUS, response.getStatusCode(), response.getStatusCodeValue()));
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new OvergameException(HttpStatus.BAD_REQUEST, ErrorCodeConstant.KEYCLOAK_USER_TOKEN_RESPONSE_ERROR,
                    "messages.exception.keycloak-user-token-response-error"
            );
        }
        return response.getBody();
    }

    @Override
    public String getKeycloakId(String username) {
        RealmResource realm = keycloak.realm(this.realm);
        UsersResource usersResource = realm.users();
        List<UserRepresentation> userRepresentations = usersResource.search(username);
        if (userRepresentations.isEmpty()) {
            throw new OvergameException(HttpStatus.BAD_REQUEST, ErrorCodeConstant.KEYCLOAK_USER_NOT_EXIST,
                    "messages.exception.keycloak-user-not-exist"
            );
        }
        UserRepresentation userRepresentation = userRepresentations.get(0);
        return userRepresentation.getId();
    }

    @Override
    public void deleteUserByKeycloakIdIfExists(String keycloakId) {
        RealmResource realm = keycloak.realm(this.realm);
        UsersResource usersResource = realm.users();
        UserResource userResource = usersResource.get(keycloakId);
        try {
            userResource.remove();
            log.info(String.format(KEYCLOAK_USER_DELETED_LOG_MESSAGE, keycloakId));
        } catch (NotFoundException e) {
            log.error(String.format(ErrorCodeConstant.KEYCLOAK_USER_NOT_FOUND, keycloakId));
        }
    }

    @Override
    public Boolean isFreeKeycloakUsername(String username) {
        RealmResource realm = keycloak.realm(this.realm);
        UsersResource usersResource = realm.users();
        List<UserRepresentation> userRepresentations = usersResource.search(username);
        return userRepresentations.isEmpty();
    }

    @Override
    public void setUserDisabled(String keycloakId) {
        RealmResource realm = keycloak.realm(this.realm);
        UsersResource usersResource = realm.users();
        UserRepresentation userRepresentation = usersResource.get(keycloakId).toRepresentation();
        userRepresentation.setEnabled(false);
        usersResource.get(keycloakId).update(userRepresentation);
    }

    private void setUserRepresentationCredentials(UserRepresentation user, String password) {
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(password);
        user.setCredentials(Collections.singletonList(credentialRepresentation));
    }

    private MultiValueMap<String, String> constructRequestBody(KeycloakAuthRequestDto keycloakAuthRequestDto) {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add(PARAM_CLIENT_ID, clientId);
        parameters.add(PARAM_CLIENT_SECRET, clientSecret);
        parameters.add(PARAM_USERNAME, keycloakAuthRequestDto.getUsername());
        parameters.add(PARAM_PASSWORD, keycloakAuthRequestDto.getPassword());
        parameters.add(PARAM_GRANT_TYPE, PARAM_VAL_PASSWORD_GRANT_TYPE);
        return parameters;
    }

    private MultiValueMap<String, String> constructRequestBody(String refreshToken) {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add(PARAM_CLIENT_ID, clientId);
        parameters.add(PARAM_CLIENT_SECRET, clientSecret);
        parameters.add(PARAM_GRANT_TYPE, PARAM_VAL_REFRESH_TOKEN_GRANT_TYPE);
        parameters.add(PARAM_REFRESH_TOKEN, refreshToken);
        return parameters;
    }

    private HttpHeaders constructRequestHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return headers;
    }
}
