package com.zhandabo.overgame.repository;

import com.zhandabo.overgame.model.entity.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Long> {

    @Query(value = "from Banner b where b.isActive = true")
    List<Banner> getByActiveTrue();
}
