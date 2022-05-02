package com.zhandabo.overgame.repository;

import com.zhandabo.overgame.model.entity.FavouriteDevelopers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FavouriteDevelopersRepository extends JpaRepository<FavouriteDevelopers, Long> {

    @Query(value = "from FavouriteDevelopers fd where fd.developer.id = :developerId and fd.user.id = :userId")
    FavouriteDevelopers getByDeveloperIdAndUserId(Long developerId, Long userId);
}
