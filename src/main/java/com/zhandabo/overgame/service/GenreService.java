package com.zhandabo.overgame.service;

import com.zhandabo.overgame.model.dto.genre.GenreCreateDto;
import com.zhandabo.overgame.model.dto.genre.GenreViewDto;

import java.util.List;

public interface GenreService {

    void create(GenreCreateDto dto);

    List<GenreViewDto> getAll();
}
