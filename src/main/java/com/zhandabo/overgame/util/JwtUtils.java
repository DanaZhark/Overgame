package com.zhandabo.overgame.util;

import com.nimbusds.jose.shaded.json.JSONArray;
import com.nimbusds.jose.shaded.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Set;
import java.util.stream.Collectors;

public final class JwtUtils {
    private JwtUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static String getKeycloakId() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null) {
            return null;
        } else {
            Authentication authentication = context.getAuthentication();
            return authentication == null ? null : authentication.getName();
        }
    }

    public static String getUserName() {
        try {
            Object mid = ((Jwt) SecurityContextHolder.getContext().getAuthentication().getCredentials()).getClaims().get("preferred_username");
            return (String) mid;
        } catch (Exception var1) {
            return getKeycloakId();
        }
    }

    public static Set<String> getCredentials() {
        Object object = ((Jwt) SecurityContextHolder.getContext().getAuthentication().getCredentials()).getClaims().get("realm_access");
        JSONObject jsonObject = (JSONObject) object;
        JSONArray jsonArray = (JSONArray) jsonObject.get("roles");
        return jsonArray.stream().map((o) -> {
            return (String) o;
        }).collect(Collectors.toSet());
    }
}

