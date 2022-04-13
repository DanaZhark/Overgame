package com.zhandabo.overgame.service.impl;

import com.zhandabo.overgame.converter.GameViewDtoConverter;
import com.zhandabo.overgame.model.dto.game.GameCreateDto;
import com.zhandabo.overgame.model.dto.game.GameViewDto;
import com.zhandabo.overgame.model.entity.Game;
import com.zhandabo.overgame.model.entity.GameGenre;
import com.zhandabo.overgame.model.entity.Genre;
import com.zhandabo.overgame.repository.GameGenreRepository;
import com.zhandabo.overgame.repository.GameRepository;
import com.zhandabo.overgame.repository.GenreRepository;
import com.zhandabo.overgame.service.GameService;
import com.zhandabo.overgame.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final GameGenreRepository gameGenreRepository;
    private final GenreRepository genreRepository;
    private final GameViewDtoConverter gameViewDtoConverter;

    @Override
    @Transactional
    public void create(GameCreateDto dto) {
        Game game = new Game();

        game.setName(dto.getName());
        game.setDescription(dto.getDescription());
        game.setGameLink(dto.getGameLink());
        game.setImgLink(dto.getImgLink());
        game.setPrice(dto.getPrice());
        game.setDateCreated(new Date());
        game.setCreatorId(JwtUtils.getKeycloakId());

        gameRepository.save(game);

        createGameGenre(dto.getGenreIds(), game.getId());
    }

    @Override
    @Transactional
    public void createGameGenre(List<Long> genreIds, Long gameId) {
        List<GameGenre> gameGenres = new ArrayList<>();
        Game game = gameRepository.getById(gameId);

        for (Long genreId : genreIds) {
            GameGenre gameGenre = new GameGenre();
            Genre genre = genreRepository.getById(genreId);
            gameGenre.setGame(game);
            gameGenre.setGenre(genre);
            gameGenres.add(gameGenre);
        }

        gameGenreRepository.saveAll(gameGenres);
    }

    @Override
    public List<GameViewDto> getAll() {
        List<GameViewDto> gameViewDtoList = new ArrayList<>();
        List<Game> games = gameRepository.findAll();

        for (Game game : games) {
            gameViewDtoList.add(gameViewDtoConverter.convert(game));
        }
        return gameViewDtoList;
    }

    @Override
    public List<GameViewDto> getByGenreId(Long genreId) {
        return null;
    }
}
