package com.zhandabo.overgame.config;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component("keycloak")
public class KeycloakComponent {

    public Boolean hasAnyRole(String... keycloakRoles) {
        return getRoles().stream().anyMatch(Arrays.asList(keycloakRoles)::contains);
    }

    public Boolean hasRole(String role) {
        return getRoles().contains(role);
    }

    private List<String> getRoles() {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Map<String, List<String>> roles = jwt.getClaim("realm_access");
        return roles.get("roles");
    }
}
