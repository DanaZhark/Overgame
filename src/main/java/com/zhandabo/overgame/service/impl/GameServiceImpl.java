package com.zhandabo.overgame.service.impl;

import com.zhandabo.overgame.converter.GameViewDtoConverter;
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
import com.zhandabo.overgame.util.ImgFileUtils;
import com.zhandabo.overgame.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
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

    private final String uploadPath = "/home/danazharkimbayeva/Documents/IITU/overgame/src/main/resources/static/images/games/";

    @Override
    @Transactional
    public void create(GameCreateDto dto) {
        try {
            ImgFileUtils.saveFile(dto.getImgFile(), uploadPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Game game = new Game();
        game.setName(dto.getName());
        game.setDescription(dto.getDescription());
        game.setGameLink(dto.getGameLink());
        game.setImgLink("/static/images/games/" + dto.getImgFile().getOriginalFilename());
        game.setPrice(dto.getPrice());
        game.setDateCreated(new Date());
        game.setCreatorId(userRepository.getIdByKeycloakId(JwtUtils.getKeycloakId()));
        game.setStatus(GameStatus.PENDING_MODERATOR);

        gameRepository.save(game);

        createGameGenre(dto.getGenreIds(), game.getId());
    }

    private String saveFile(MultipartFile file) throws IOException {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        String fileName = file.getOriginalFilename();
        File newFile = new File(uploadPath + fileName);

        try {
            inputStream = file.getInputStream();

            if (!newFile.exists()) {
                newFile.createNewFile();
            }
            outputStream = new FileOutputStream(newFile);
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return newFile.getAbsolutePath();
    }

    @Override
    @Transactional
    public void edit(GameCreateDto dto, Long gameId) {
        try {
            ImgFileUtils.saveFile(dto.getImgFile(), uploadPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Game game = gameRepository.getById(gameId);
        game.setName(dto.getName());
        game.setDescription(dto.getDescription());
        game.setGameLink(dto.getGameLink());
        game.setImgLink("/static/images/games/" + dto.getImgFile().getOriginalFilename());
        game.setPrice(dto.getPrice());
        game.setDateCreated(new Date());
        game.setCreatorId(userRepository.getIdByKeycloakId(JwtUtils.getKeycloakId()));

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
    public List<GameViewDto> getGamesByStatus(GameStatus status) {
        List<GameViewDto> gameViewDtoList = new ArrayList<>();
        List<Game> games = gameRepository.getAllByStatus(status);

        for (Game game : games) {
            gameViewDtoList.add(gameViewDtoConverter.convert(game));
        }
        return gameViewDtoList;
    }

    @Override
    public void changeGameStatus(Long gameId, GameStatus status) {
        String moderatorId = JwtUtils.getKeycloakId();
        Game game = gameRepository.getGameById(gameId);
        game.setStatus(status);
        game.setModeratorId(userRepository.getIdByKeycloakId(moderatorId));
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
