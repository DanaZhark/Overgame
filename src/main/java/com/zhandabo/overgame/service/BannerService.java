package com.zhandabo.overgame.service;

import com.zhandabo.overgame.model.dto.banner.BannerCreateDto;
import com.zhandabo.overgame.model.dto.banner.BannerViewDto;
import com.zhandabo.overgame.model.enums.BannerCode;

import java.util.List;

public interface BannerService {

    void create(BannerCreateDto bannerCreateDto);

    void edit(BannerCreateDto bannerCreateDto, Long bannerId);

    void delete(Long bannerId);

    List<BannerViewDto> getAllBannersByCodeAndIsActive(BannerCode code, Boolean isActive);

    List<String> getAllBannersCode();
}
