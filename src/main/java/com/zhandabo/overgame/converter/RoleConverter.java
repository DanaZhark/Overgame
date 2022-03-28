package com.zhandabo.overgame.converter;

import com.zhandabo.overgame.model.dto.RoleDto;
import com.zhandabo.overgame.model.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter {

    public RoleDto convert(Role source) {
        RoleDto target = new RoleDto();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setDescription(source.getDescription());
        target.setCode(source.getCode());
        return target;
    }
}
