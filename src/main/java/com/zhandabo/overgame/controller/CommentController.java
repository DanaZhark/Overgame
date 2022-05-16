package com.zhandabo.overgame.controller;

import com.zhandabo.overgame.model.dto.comment.CommentViewDto;
import com.zhandabo.overgame.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/comment")
@Api(value = "Методы для работы с комментариями")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/games/{gameId}")
    @ApiOperation("Создание комментария")
    public CommentViewDto create(@RequestParam String text,
                                 @PathVariable Long gameId) {
        return commentService.create(text, gameId);
    }

    @PutMapping("/{commentId}")
    @ApiOperation("Редактирование комментария")
    public CommentViewDto edit(@RequestParam String text,
                               @PathVariable Long commentId) {
        return commentService.edit(text, commentId);
    }

    @DeleteMapping("/{commentId}")
    @ApiOperation("Удаление комментария")
    public void delete(@PathVariable Long commentId) {
        commentService.delete(commentId);
    }

    @GetMapping("/games/{gameId}")
    @ApiOperation("Вывод всех комментариев под игрой")
    public List<CommentViewDto> getAllCommentsByGameId(@PathVariable Long gameId) {
        return commentService.getAllCommentsByGameId(gameId);
    }
}
