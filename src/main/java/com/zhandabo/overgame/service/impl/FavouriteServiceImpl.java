package com.zhandabo.overgame.service.impl;

import com.zhandabo.overgame.converter.GameViewDtoConverter;
import com.zhandabo.overgame.converter.UserShortViewDtoConverter;
import com.zhandabo.overgame.model.dto.game.GameViewDto;
import com.zhandabo.overgame.model.dto.user.UserShortViewDto;
import com.zhandabo.overgame.model.entity.FavouriteDevelopers;
import com.zhandabo.overgame.model.entity.FavouriteGames;
import com.zhandabo.overgame.model.entity.Game;
import com.zhandabo.overgame.model.entity.User;
import com.zhandabo.overgame.repository.FavouriteDevelopersRepository;
import com.zhandabo.overgame.repository.FavouriteGamesRepository;
import com.zhandabo.overgame.repository.GameRepository;
import com.zhandabo.overgame.repository.UserRepository;
import com.zhandabo.overgame.service.FavouriteService;
import com.zhandabo.overgame.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class FavouriteServiceImpl implements FavouriteService {

    private final GameRepository gameRepository;
    private final FavouriteGamesRepository favouriteGamesRepository;
    private final FavouriteDevelopersRepository favouriteDevelopersRepository;
    private final GameViewDtoConverter gameViewDtoConverter;
    private final UserRepository userRepository;
    private final UserShortViewDtoConverter userShortViewDtoConverter;
    private final UserService userService;

    @Override
    public void addGameToFavourite(Long gameId) {
        FavouriteGames favouriteGames = new FavouriteGames();
        Long userId = userService.getCurrentUser().getId();
        User user = userRepository.findById(userId).get();
        favouriteGames.setGame(gameRepository.getById(gameId));
        favouriteGames.setUser(user);

        favouriteGamesRepository.save(favouriteGames);
    }

    @Override
    public void removeGameFromFavorite(Long gameId) {
        Long userId = userService.getCurrentUser().getId();
        FavouriteGames favouriteGames = favouriteGamesRepository.getByGameIdAndUserId(gameId, userId);

        favouriteGamesRepository.delete(favouriteGames);
    }

    @Override
    public List<GameViewDto> getUserFavouriteGames() {
        Long userId = userService.getCurrentUser().getId();
        User user = userRepository.findById(userId).get();
        Set<FavouriteGames> favouriteGames = user.getFavouriteGames();
        List<GameViewDto> gameViewDtoList = new ArrayList<>();

        for (FavouriteGames favouriteGame : favouriteGames) {
            Game game = gameRepository.getGameById(favouriteGame.getGame().getId());
            gameViewDtoList.add(gameViewDtoConverter.convert(game));
        }
        return gameViewDtoList;
    }

    @Override
    public List<GameViewDto> getUserFavouriteGamesByUserId(Long userId) {

        User user = userRepository.findById(userId).get();
        Set<FavouriteGames> favouriteGames = user.getFavouriteGames();
        List<GameViewDto> gameViewDtoList = new ArrayList<>();

        for (FavouriteGames favouriteGame : favouriteGames) {
            Game game = gameRepository.getGameById(favouriteGame.getGame().getId());
            gameViewDtoList.add(gameViewDtoConverter.convert(game));
        }
        return gameViewDtoList;
    }

    @Override
    public void addDeveloperToFavourite(Long developerId) {

        FavouriteDevelopers favouriteDevelopers = new FavouriteDevelopers();
        Long userId = userService.getCurrentUser().getId();
        User user = userRepository.findById(userId).get();
        User developer = userRepository.getById(developerId);

        favouriteDevelopers.setDeveloper(developer);
        favouriteDevelopers.setUser(user);

        favouriteDevelopersRepository.save(favouriteDevelopers);
    }

    @Override
    public void removeDeveloperFromFavorite(Long developerId) {
        Long userId = userService.getCurrentUser().getId();
        FavouriteDevelopers favouriteDevelopers = favouriteDevelopersRepository.getByDeveloperIdAndUserId(developerId, userId);
        favouriteDevelopersRepository.delete(favouriteDevelopers);
    }

    @Override
    public List<UserShortViewDto> getUserFavouriteDevelopers() {
        Long userId = userService.getCurrentUser().getId();
        List<UserShortViewDto> userShortViewDtoList = new ArrayList<>();
        List<User> users = userRepository.getFavouriteDevelopersByUserId(userId);
        for (User user : users) {
            userShortViewDtoList.add(userShortViewDtoConverter.convert(user));
        }
        return userShortViewDtoList;
    }

    @Override
    public List<UserShortViewDto> getUserFavouriteDevelopersByUserId(Long userId) {

        List<UserShortViewDto> userShortViewDtoList = new ArrayList<>();
        List<User> users = userRepository.getFavouriteDevelopersByUserId(userId);
        for (User user : users) {
            userShortViewDtoList.add(userShortViewDtoConverter.convert(user));
        }
        return userShortViewDtoList;
    }
}
