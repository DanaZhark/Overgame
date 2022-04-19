package com.zhandabo.overgame.service.impl;

import com.zhandabo.overgame.converter.GenreViewDtoConverter;
import com.zhandabo.overgame.model.dto.genre.GenreCreateDto;
import com.zhandabo.overgame.model.dto.genre.GenreViewDto;
import com.zhandabo.overgame.model.entity.Genre;
import com.zhandabo.overgame.repository.GenreRepository;
import com.zhandabo.overgame.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final GenreViewDtoConverter genreViewDtoConverter;

    @Override
    @Transactional
    public void create(GenreCreateDto dto) {
        Genre genre = new Genre();

        genre.setCode(dto.getCode());
        genre.setName(dto.getName());
        genre.setDescription(dto.getDescription());
        genre.setImgLink(dto.getImgLink());

        genreRepository.save(genre);
    }

    @Override
    public List<GenreViewDto> getAll() {
        List<GenreViewDto> genreViewDtoList = new ArrayList<>();
        List<Genre> genres = genreRepository.findAll();

        for (Genre genre : genres) {
            genreViewDtoList.add(genreViewDtoConverter.convert(genre));
        }
        return genreViewDtoList;
    }

    @Override
    public List<GenreViewDto> getGenresByGameId(Long gameId) {
        List<GenreViewDto> genreViewDtoList = new ArrayList<>();
        List<Genre> genres = genreRepository.getAllByGameId(gameId);

        for (Genre genre : genres) {
            genreViewDtoList.add(genreViewDtoConverter.convert(genre));
        }
        return genreViewDtoList;
    }
}
