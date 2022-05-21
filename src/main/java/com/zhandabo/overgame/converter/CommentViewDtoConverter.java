package com.zhandabo.overgame.converter;

import com.zhandabo.overgame.model.dto.comment.CommentViewDto;
import com.zhandabo.overgame.model.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentViewDtoConverter implements Converter<Comment, CommentViewDto> {

    private final UserShortViewDtoConverter userShortViewDtoConverter;

    @Override
    public CommentViewDto convert(Comment source) {
        CommentViewDto target = new CommentViewDto();
        target.setId(source.getId());
        target.setText(source.getText());
        target.setDateCreated(source.getDateCreated());
        target.setGameId(source.getGame().getId());
        target.setUserInfo(userShortViewDtoConverter.convert(source.getUser()));
        return target;
    }
}
