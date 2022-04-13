package com.zhandabo.overgame.service.impl;

import com.zhandabo.overgame.model.dto.game.GameCreateDto;
import com.zhandabo.overgame.model.dto.game.GameViewDto;
import com.zhandabo.overgame.model.entity.Game;
import com.zhandabo.overgame.repository.GameRepository;
import com.zhandabo.overgame.service.GameService;
import com.zhandabo.overgame.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    @Override
    public void create(GameCreateDto gameCreateDto) {
        Game game = new Game();

        Date date = new Date();

        game.setName(game.getName());
        game.setDescription(game.getDescription());
        game.setGameLink(game.getGameLink());
        game.setImgLink(game.getImgLink());
        game.setPrice(game.getPrice());
        game.setDateCreated(date);
        game.setCreatorId(JwtUtils.getKeycloakId());

        gameRepository.save(game);
    }

    @Override
    public List<GameViewDto> getAll() {
        return null;
    }

    @Override
    public List<GameViewDto> getByGenreId(Long genreId) {
        return null;
    }
}
