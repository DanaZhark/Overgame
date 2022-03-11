package com.zhandabo.overgame.repository;

import com.zhandabo.overgame.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    User getByKeycloakId(String keycloakId);

    Boolean existsByEmail(String email);

    User getByEmail(String email);
}
