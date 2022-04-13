package com.zhandabo.overgame.service;

import com.zhandabo.overgame.model.dto.genre.GenreViewDto;

import java.util.List;

public interface GenreService {

    List<GenreViewDto> getAll();
}
