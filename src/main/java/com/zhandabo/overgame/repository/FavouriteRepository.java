package com.zhandabo.overgame.repository;

import com.zhandabo.overgame.model.entity.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavouriteRepository extends JpaRepository<Favourite, Long> {
}
