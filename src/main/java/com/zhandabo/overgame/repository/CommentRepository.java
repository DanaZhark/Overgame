package com.zhandabo.overgame.repository;

import com.zhandabo.overgame.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "from Comment c where c.game.id = :gameId")
    List<Comment> getByGame(Long gameId);

}
