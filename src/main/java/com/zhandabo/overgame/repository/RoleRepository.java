package com.zhandabo.overgame.repository;

import com.zhandabo.overgame.model.entity.Role;
import com.zhandabo.overgame.model.enums.RoleCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role getByCode(RoleCode roleCode);
}