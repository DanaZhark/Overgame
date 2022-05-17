package com.zhandabo.overgame.service.impl;

import com.zhandabo.overgame.model.dto.banner.BannerCreateDto;
import com.zhandabo.overgame.model.dto.banner.BannerViewDto;
import com.zhandabo.overgame.model.entity.Banner;
import com.zhandabo.overgame.model.enums.BannerCode;
import com.zhandabo.overgame.repository.BannerRepository;
import com.zhandabo.overgame.service.BannerService;
import com.zhandabo.overgame.util.ImgFileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BannerServiceImpl implements BannerService {

    private final BannerRepository bannerRepository;

    private final String uploadPath = "/overgame/src/main/resources/static/images/banners/";

    @Override
    public void create(BannerCreateDto dto) {
        try {
            ImgFileUtils.saveFile(dto.getImgFile(), uploadPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Banner banner = new Banner();
        banner.setName(dto.getName());
        banner.setDescription(dto.getDescription());
        banner.setImgLink("/static/images/banners/" + dto.getImgFile().getOriginalFilename());
        banner.setCode(dto.getCode());

        bannerRepository.save(banner);
    }

    @Override
    public void edit(BannerCreateDto dto, Long bannerId) {
        try {
            ImgFileUtils.saveFile(dto.getImgFile(), uploadPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Banner banner = bannerRepository.getById(bannerId);
        banner.setName(dto.getName());
        banner.setDescription(dto.getDescription());
        banner.setImgLink("/static/images/banners/" + dto.getImgFile().getOriginalFilename());
        banner.setCode(dto.getCode());

        bannerRepository.save(banner);
    }

    @Override
    public void delete(Long bannerId) {
        Banner banner = bannerRepository.getById(bannerId);
        banner.setActive(false);
        bannerRepository.save(banner);
    }

    @Override
    public List<BannerViewDto> getAllBannersByCodeAndIsActive(BannerCode code, Boolean isActive) {
        return null;
    }

    @Override
    public List<String> getAllBannersCode() {
        return null;
    }
}
