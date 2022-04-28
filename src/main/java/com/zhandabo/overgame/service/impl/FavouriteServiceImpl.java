package com.zhandabo.overgame.service.impl;

import com.zhandabo.overgame.converter.GameViewDtoConverter;
import com.zhandabo.overgame.model.dto.game.GameViewDto;
import com.zhandabo.overgame.model.entity.Favourite;
import com.zhandabo.overgame.model.entity.Game;
import com.zhandabo.overgame.model.entity.User;
import com.zhandabo.overgame.repository.FavouriteRepository;
import com.zhandabo.overgame.repository.GameRepository;
import com.zhandabo.overgame.repository.UserRepository;
import com.zhandabo.overgame.service.FavouriteService;
import com.zhandabo.overgame.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FavouriteServiceImpl implements FavouriteService {

    private final GameRepository gameRepository;
    private final FavouriteRepository favouriteRepository;
    private final GameViewDtoConverter gameViewDtoConverter;
    private final UserRepository userRepository;

    @Override
    public void addGameToFavourite(Long gameId) {
        Favourite favourite = new Favourite();
        String userId = JwtUtils.getKeycloakId();
        User user = userRepository.getByKeycloakId(userId);
        favourite.setGame(gameRepository.getById(gameId));
        favourite.setUser(user);

        favouriteRepository.save(favourite);
    }

    @Override
    public void removeGameFromFavorite(Long gameId) {
        String userId = JwtUtils.getKeycloakId();
        Favourite favourite = favouriteRepository.getByGameId(gameId, userId);

        favouriteRepository.delete(favourite);
    }

    @Override
    public List<GameViewDto> getUserFavouriteGames() {
        String userId = JwtUtils.getKeycloakId();
        List<GameViewDto> gameViewDtoList = new ArrayList<>();
        List<Game> games = gameRepository.getFavouriteGamesByUserId(userId);

        for (Game game : games) {
            gameViewDtoList.add(gameViewDtoConverter.convert(game));
        }
        return gameViewDtoList;
    }
}
