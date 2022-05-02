package com.zhandabo.overgame.repository;

import com.zhandabo.overgame.model.entity.AchievementType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AchievementTypeRepository extends JpaRepository<AchievementType, Long> {

}
