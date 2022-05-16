package com.zhandabo.overgame.service;

import com.zhandabo.overgame.model.dto.comment.CommentViewDto;

import java.util.List;

public interface CommentService {

    CommentViewDto create(String text, Long gameId);

    CommentViewDto edit(String text, Long commentId);

    void delete(Long commentId);

    List<CommentViewDto> getAllCommentsByGameId(Long gameId);
}
