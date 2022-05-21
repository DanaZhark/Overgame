package com.zhandabo.overgame.converter;

import com.zhandabo.overgame.model.dto.game.GameViewDto;
import com.zhandabo.overgame.model.dto.genre.GenreShortViewDto;
import com.zhandabo.overgame.model.entity.Game;
import com.zhandabo.overgame.model.entity.GameGenre;
import com.zhandabo.overgame.model.entity.Genre;
import com.zhandabo.overgame.model.entity.User;
import com.zhandabo.overgame.model.enums.GameStatus;
import com.zhandabo.overgame.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GameViewDtoConverter implements Converter<Game, GameViewDto> {

    private final GenreShortViewDtoConverter genreShortViewDtoConverter;
    private final UserRepository userRepository;
    private final UserShortViewDtoConverter userShortViewDtoConverter;

    @Override
    public GameViewDto convert(Game source) {
        GameViewDto target = new GameViewDto();
        target.setId(source.getId());
        target.setName(source.getName().get("en"));
        target.setDescription(source.getDescription().get("en"));
        target.setGameLink(source.getGameLink());
        target.setImgLink(source.getImgLink());
        target.setPrice(source.getPrice());
        target.setRating(source.getRating());
        target.setStatus(source.getStatus());
        List<Genre> genres = source.getGenres().stream().map(GameGenre::getGenre).collect(Collectors.toList());
        List<GenreShortViewDto> genreShortViewDtoList = new ArrayList<>();
        for (Genre genre : genres) {
            genreShortViewDtoList.add(genreShortViewDtoConverter.convert(genre));
        }
        target.setGenres(genreShortViewDtoList);
        Optional<User> creator = userRepository.findById(source.getCreatorId());
        if (creator.isPresent()) {
            target.setCreator(userShortViewDtoConverter.convert(creator.get()));
        }

        if (Objects.nonNull(source.getModeratorId()) && GameStatus.ACCEPTED.equals(source.getStatus())) {
            Optional<User> moderator = userRepository.findById(source.getModeratorId());
            target.setModerator(userShortViewDtoConverter.convert(moderator.get()));
        }

        return target;
    }
}
