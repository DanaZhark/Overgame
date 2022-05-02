package com.zhandabo.overgame.service.impl;

import com.zhandabo.overgame.converter.GameViewDtoConverter;
import com.zhandabo.overgame.converter.UserViewDtoConverter;
import com.zhandabo.overgame.model.dto.UserViewDto;
import com.zhandabo.overgame.model.dto.game.GameViewDto;
import com.zhandabo.overgame.model.entity.FavouriteDevelopers;
import com.zhandabo.overgame.model.entity.FavouriteGames;
import com.zhandabo.overgame.model.entity.Game;
import com.zhandabo.overgame.model.entity.User;
import com.zhandabo.overgame.repository.FavouriteDevelopersRepository;
import com.zhandabo.overgame.repository.FavouriteGamesRepository;
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
    private final FavouriteGamesRepository favouriteGamesRepository;
    private final FavouriteDevelopersRepository favouriteDevelopersRepository;
    private final GameViewDtoConverter gameViewDtoConverter;
    private final UserRepository userRepository;
    private final UserViewDtoConverter userViewDtoConverter;

    @Override
    public void addGameToFavourite(Long gameId) {
        FavouriteGames favouriteGames = new FavouriteGames();
        String userId = JwtUtils.getKeycloakId();
        User user = userRepository.getByKeycloakId(userId);
        favouriteGames.setGame(gameRepository.getById(gameId));
        favouriteGames.setUser(user);

        favouriteGamesRepository.save(favouriteGames);
    }

    @Override
    public void removeGameFromFavorite(Long gameId) {
        String userId = JwtUtils.getKeycloakId();
        FavouriteGames favouriteGames = favouriteGamesRepository.getByGameIdAndUserId(gameId, userId);

        favouriteGamesRepository.delete(favouriteGames);
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

    @Override
    public void addDeveloperToFavourite(Long developerId) {

        FavouriteDevelopers favouriteDevelopers = new FavouriteDevelopers();
        String userId = JwtUtils.getKeycloakId();
        User user = userRepository.getByKeycloakId(userId);
        User developer = userRepository.getById(developerId);

        favouriteDevelopers.setDeveloper(developer);
        favouriteDevelopers.setUser(user);

        favouriteDevelopersRepository.save(favouriteDevelopers);
    }

    @Override
    public void removeDeveloperFromFavorite(Long developerId) {
        String userId = JwtUtils.getKeycloakId();
        FavouriteDevelopers favouriteDevelopers = favouriteDevelopersRepository.getByDeveloperIdAndUserId(developerId, userRepository.getIdByKeycloakId(userId));
        favouriteDevelopersRepository.delete(favouriteDevelopers);
    }

    @Override
    public List<UserViewDto> getUserFavouriteDevelopers() {
        String userId = JwtUtils.getKeycloakId();
        List<UserViewDto> userViewDtoList = new ArrayList<>();
        List<User> users = userRepository.getFavouriteDevelopersByUserId(userRepository.getIdByKeycloakId(userId));
        for (User user : users) {
            userViewDtoList.add(userViewDtoConverter.convert(user));
        }
        return userViewDtoList;
    }
}
