package com.zhandabo.overgame.repository;

import com.zhandabo.overgame.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    User getByKeycloakId(String keycloakId);

    Boolean existsByEmail(String email);

    @Query(value = "from User u inner join Game g where u.id = g.creatorId")
    List<User> getDevelopers();

    @Query(value = "from User u " +
            "inner join FavouriteDevelopers fd on u.id = fd.user.id " +
            "where fd.user.id = :userId")
    List<User> getFavouriteDevelopersByUserId(Long userId);
}
