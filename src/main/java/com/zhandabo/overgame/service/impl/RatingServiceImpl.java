package com.zhandabo.overgame.service.impl;

import com.zhandabo.overgame.model.entity.Game;
import com.zhandabo.overgame.model.entity.Rating;
import com.zhandabo.overgame.repository.GameRepository;
import com.zhandabo.overgame.repository.RatingRepository;
import com.zhandabo.overgame.repository.UserRepository;
import com.zhandabo.overgame.service.RatingService;
import com.zhandabo.overgame.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final GameRepository gameRepository;
    private final UserRepository userRepository;
    private final RatingRepository ratingRepository;
    private final UserService userService;

    @Override
    public void userRateGame(Long gameId, BigDecimal grade) {
        Rating rating = new Rating();
        Game game = gameRepository.getGameById(gameId);
        Long userId = userService.getCurrentUser().getId();
        rating.setGame(game);
        rating.setUser(userRepository.findById(userId).get());
        rating.setGrade(grade);
        ratingRepository.save(rating);

        game.setRating(ratingRepository.getRatingGameByGameId(gameId));
        gameRepository.save(game);
    }
}
