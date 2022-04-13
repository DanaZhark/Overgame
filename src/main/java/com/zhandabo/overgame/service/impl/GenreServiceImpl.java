package com.zhandabo.overgame.service.impl;

import com.zhandabo.overgame.converter.GenreViewDtoConverter;
import com.zhandabo.overgame.model.dto.genre.GenreViewDto;
import com.zhandabo.overgame.model.entity.Genre;
import com.zhandabo.overgame.repository.GenreRepository;
import com.zhandabo.overgame.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final GenreViewDtoConverter genreViewDtoConverter;

    @Override
    public List<GenreViewDto> getAll() {
        List<GenreViewDto> genreViewDtoList = new ArrayList<>();
        List<Genre> genres = genreRepository.findAll();
        
        for (Genre genre : genres) {
            genreViewDtoList.add(genreViewDtoConverter.convert(genre));
        }
        return genreViewDtoList;
    }
}
