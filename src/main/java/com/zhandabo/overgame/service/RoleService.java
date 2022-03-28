package com.zhandabo.overgame.service;

import com.zhandabo.overgame.model.entity.Role;
import com.zhandabo.overgame.model.enums.RoleCode;

public interface RoleService {
    Role getByRoleCode(RoleCode roleCode);
}
