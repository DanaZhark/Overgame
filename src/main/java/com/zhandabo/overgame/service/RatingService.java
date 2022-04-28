package com.zhandabo.overgame.service;

import java.math.BigDecimal;

public interface RatingService {

    void userRateGame(Long gameId, BigDecimal grade);
}
