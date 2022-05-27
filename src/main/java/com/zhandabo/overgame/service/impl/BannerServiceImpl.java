package com.zhandabo.overgame.service.impl;

import com.zhandabo.overgame.converter.BannerViewDtoConverter;
import com.zhandabo.overgame.model.dto.banner.BannerCreateDto;
import com.zhandabo.overgame.model.dto.banner.BannerViewDto;
import com.zhandabo.overgame.model.entity.Banner;
import com.zhandabo.overgame.model.enums.BannerCode;
import com.zhandabo.overgame.repository.BannerRepository;
import com.zhandabo.overgame.service.BannerService;
import com.zhandabo.overgame.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BannerServiceImpl implements BannerService {

    private final BannerRepository bannerRepository;
    private final StorageService storageService;
    private final BannerViewDtoConverter bannerViewDtoConverter;

    private final String uploadPath = "https://overgame.s3.us-west-2.amazonaws.com/banners/";

    @Override
    public void create(BannerCreateDto dto) {
        storageService.uploadFile(dto.getImgFile(), "banners");

        Banner banner = new Banner();
        banner.setName(dto.getName());
        banner.setDescription(dto.getDescription());
        banner.setImgLink(uploadPath + dto.getImgFile().getOriginalFilename());
        banner.setCode(dto.getCode());
        banner.setActive(true);

        bannerRepository.save(banner);
    }

    @Override
    public void edit(BannerCreateDto dto, Long bannerId) {
        storageService.uploadFile(dto.getImgFile(), "banners");

        Banner banner = bannerRepository.getById(bannerId);
        banner.setName(dto.getName());
        banner.setDescription(dto.getDescription());
        banner.setImgLink(uploadPath + dto.getImgFile().getOriginalFilename());
        banner.setCode(dto.getCode());

        bannerRepository.save(banner);
    }

    @Override
    public void delete(Long bannerId) {
        Banner banner = bannerRepository.findById(bannerId).get();
        banner.setActive(false);
        bannerRepository.save(banner);
    }

    @Override
    public List<BannerViewDto> getAllActiveBanners() {
        List<Banner> banners = bannerRepository.getByActiveTrue();
        List<BannerViewDto> bannerViewDtoList = new ArrayList<>();
        for (Banner banner : banners) {
            bannerViewDtoList.add(bannerViewDtoConverter.convert(banner));
        }
        return bannerViewDtoList;
    }

    @Override
    public List<BannerCode> getAllBannersCode() {
        return List.of(BannerCode.values());
    }
}
