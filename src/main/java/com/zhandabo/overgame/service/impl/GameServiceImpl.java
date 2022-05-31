package com.zhandabo.overgame.service.impl;

import com.zhandabo.overgame.converter.GameViewDtoConverter;
import com.zhandabo.overgame.model.dto.PageDTO;
import com.zhandabo.overgame.model.dto.game.GameCreateDto;
import com.zhandabo.overgame.model.dto.game.GameViewDto;
import com.zhandabo.overgame.model.entity.Game;
import com.zhandabo.overgame.model.entity.GameGenre;
import com.zhandabo.overgame.model.entity.Genre;
import com.zhandabo.overgame.model.enums.GameStatus;
import com.zhandabo.overgame.repository.GameGenreRepository;
import com.zhandabo.overgame.repository.GameRepository;
import com.zhandabo.overgame.repository.GenreRepository;
import com.zhandabo.overgame.repository.UserRepository;
import com.zhandabo.overgame.service.GameService;
import com.zhandabo.overgame.service.StorageService;
import com.zhandabo.overgame.service.UserService;
import com.zhandabo.overgame.util.PageConverterUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final UserRepository userRepository;
    private final GameViewDtoConverter gameViewDtoConverter;
    private final StorageService storageService;
    private final UserService userService;

    private final String uploadPath = "https://overgame.s3.us-west-2.amazonaws.com/games/";

    @Override
    @Transactional
    public void create(GameCreateDto dto) {
        storageService.uploadFile(dto.getImgFile(), "games");

        Game game = new Game();
        game.setName(dto.getName());
        game.setDescription(dto.getDescription());
        game.setGameLink(dto.getGameLink());
        game.setImgLink(uploadPath + dto.getImgFile().getOriginalFilename());
        game.setPrice(dto.getPrice());
        game.setDateCreated(new Date());
        game.setCreatorId(userService.getCurrentUser().getId());
        game.setStatus(GameStatus.PENDING_MODERATOR);
        gameRepository.save(game);

        createGameGenre(dto.getGenreIds(), game.getId());
    }

    @Override
    @Transactional
    public void edit(GameCreateDto dto, Long gameId) {

        storageService.uploadFile(dto.getImgFile(), "games");

        Game game = gameRepository.getById(gameId);
        game.setName(dto.getName());
        game.setDescription(dto.getDescription());
        game.setGameLink(dto.getGameLink());
        game.setImgLink(uploadPath + dto.getImgFile().getOriginalFilename());
        game.setPrice(dto.getPrice());
        game.setDateUpdated(new Date());

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
    @Transactional
    public PageDTO<GameViewDto> getGamesByStatus(GameStatus status, Pageable pageable) {
        List<GameViewDto> gameViewDtoList = new ArrayList<>();
        Page<Game> games = gameRepository.getAllByStatus(status, pageable);

        for (Game game : games.getContent()) {
            gameViewDtoList.add(gameViewDtoConverter.convert(game));
        }
        return PageConverterUtils.convert(games, gameViewDtoList);
    }

    @Override
    @Transactional
    public PageDTO<GameViewDto> getAllGamesByDeveloperId(Pageable pageable) {
        List<GameViewDto> gameViewDtoList = new ArrayList<>();
        Page<Game> games = gameRepository.getAllByDeveloperId(userService.getCurrentUser().getId(), pageable);

        for (Game game : games.getContent()) {
            gameViewDtoList.add(gameViewDtoConverter.convert(game));
        }
        return PageConverterUtils.convert(games, gameViewDtoList);
    }

    @Override
    public PageDTO<GameViewDto> getAllAcceptedGames(String gameName, List<Long> genreIds, Pageable pageable) {
        List<GameViewDto> gameViewDtoList = new ArrayList<>();
        Page<Game> games = gameRepository.findAll(pageable);
        if ((genreIds == null || !genreIds.isEmpty()) && gameName == null) {
            games = gameRepository.getAllByGenreIds(GameStatus.ACCEPTED, genreIds, pageable);
        } else if (genreIds.isEmpty() && gameName != null) {
            games = gameRepository.findAllByName(GameStatus.ACCEPTED, gameName.toLowerCase(), pageable);
        } else if ((genreIds == null || !genreIds.isEmpty()) && gameName != null) {
            games = gameRepository.findAllByNameAndGenreIds(GameStatus.ACCEPTED, genreIds, gameName.toLowerCase(), pageable);
        }


        for (Game game : games) {
            gameViewDtoList.add(gameViewDtoConverter.convert(game));
        }

        return PageConverterUtils.convert(games, gameViewDtoList);
    }

    @Override
    public void changeGameStatus(Long gameId, GameStatus status) {
        Game game = gameRepository.getGameById(gameId);
        game.setStatus(status);
        game.setModeratorId(userService.getCurrentUser().getId());
        gameRepository.save(game);
    }

    @Override
    public GameViewDto getGameById(Long gameId) {
        Game game = gameRepository.getGameById(gameId);
        return gameViewDtoConverter.convert(game);
    }

    @Override
    public List<GameViewDto> getGamesByGenreId(Long genreId) {
        List<GameViewDto> gameViewDtoList = new ArrayList<>();
        List<Game> games = gameRepository.getAllByGenreId(genreId);

        for (Game game : games) {
            gameViewDtoList.add(gameViewDtoConverter.convert(game));
        }
        return gameViewDtoList;
    }
}
