package com.zhandabo.overgame.util;

import com.zhandabo.overgame.model.entity.Role;
import com.zhandabo.overgame.model.entity.User;
import com.zhandabo.overgame.model.entity.UserRole;
import com.zhandabo.overgame.model.enums.RoleCode;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class RoleUtils {

    public static boolean hasAnyRole(User user, RoleCode... roleCodes) {
        if (roleCodes == null || roleCodes.length < 1) {
            throw new IllegalArgumentException();
        }
        Set<RoleCode> userRoles = user.getRoles().stream()
                .map(UserRole::getRole)
                .map(Role::getCode)
                .collect(Collectors.toSet());
        return !Collections.disjoint(userRoles, Arrays.asList(roleCodes));
    }
}
