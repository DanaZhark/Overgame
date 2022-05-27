package com.zhandabo.overgame.service.impl;

import com.zhandabo.overgame.converter.CommentViewDtoConverter;
import com.zhandabo.overgame.model.dto.comment.CommentViewDto;
import com.zhandabo.overgame.model.entity.Comment;
import com.zhandabo.overgame.model.entity.Game;
import com.zhandabo.overgame.model.entity.User;
import com.zhandabo.overgame.repository.CommentRepository;
import com.zhandabo.overgame.repository.GameRepository;
import com.zhandabo.overgame.repository.UserRepository;
import com.zhandabo.overgame.service.CommentService;
import com.zhandabo.overgame.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    private final CommentViewDtoConverter commentViewDtoConverter;
    private final UserService userService;

    @Override
    public CommentViewDto create(String text, Long gameId) {
        Long userId = userService.getCurrentUser().getId();
        User user = userRepository.findById(userId).get();

        Game game = gameRepository.findById(gameId).get();

        Comment comment = new Comment();
        comment.setText(text);
        comment.setUser(user);
        comment.setGame(game);
        comment.setDateCreated(new Date());

        commentRepository.save(comment);
        return commentViewDtoConverter.convert(comment);
    }

    @Override
    public CommentViewDto edit(String text, Long commentId) {
        Comment comment = commentRepository.findById(commentId).get();
        comment.setText(text);
        commentRepository.save(comment);
        return commentViewDtoConverter.convert(comment);
    }

    @Override
    public void delete(Long commentId) {
        Comment comment = commentRepository.getById(commentId);
        commentRepository.delete(comment);
    }

    @Override
    public List<CommentViewDto> getAllCommentsByGameId(Long gameId) {
        List<Comment> comments = commentRepository.getByGame(gameId);
        List<CommentViewDto> commentViewDtoList = new ArrayList<>();
        for (Comment comment : comments) {
            commentViewDtoList.add(commentViewDtoConverter.convert(comment));
        }
        return commentViewDtoList;
    }
}
