package com.zhandabo.overgame.service;

import com.zhandabo.overgame.model.dto.game.GameViewDto;
import com.zhandabo.overgame.model.dto.user.UserShortViewDto;

import java.util.List;

public interface FavouriteService {

    void addGameToFavourite(Long gameId);

    List<GameViewDto> getUserFavouriteGames();

    List<GameViewDto> getUserFavouriteGamesByUserId(Long userId);

    void removeGameFromFavorite(Long gameId);

    void addDeveloperToFavourite(Long developerId);

    void removeDeveloperFromFavorite(Long developerId);

    List<UserShortViewDto> getUserFavouriteDevelopers();

    List<UserShortViewDto> getUserFavouriteDevelopersByUserId(Long userId);
}
