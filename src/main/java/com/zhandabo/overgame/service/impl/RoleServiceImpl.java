package com.zhandabo.overgame.service.impl;

import com.zhandabo.overgame.model.entity.Role;
import com.zhandabo.overgame.model.enums.RoleCode;
import com.zhandabo.overgame.repository.RoleRepository;
import com.zhandabo.overgame.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role getByRoleCode(RoleCode roleCode) {
        return roleRepository.getByCode(roleCode);
    }
}
