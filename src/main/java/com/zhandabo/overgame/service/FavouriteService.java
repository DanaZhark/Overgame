package com.zhandabo.overgame.service;

import com.zhandabo.overgame.model.dto.UserViewDto;
import com.zhandabo.overgame.model.dto.game.GameViewDto;

import java.util.List;

public interface FavouriteService {


    void addGameToFavourite(Long gameId);

    List<GameViewDto> getUserFavouriteGames();

    void removeGameFromFavorite(Long gameId);

    void addDeveloperToFavourite(Long developerId);

    void removeDeveloperFromFavorite(Long developerId);

    List<UserViewDto> getUserFavouriteDevelopers();
}
