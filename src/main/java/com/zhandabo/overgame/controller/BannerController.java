package com.zhandabo.overgame.controller;

import com.zhandabo.overgame.model.dto.banner.BannerCreateDto;
import com.zhandabo.overgame.model.dto.banner.BannerViewDto;
import com.zhandabo.overgame.model.enums.BannerCode;
import com.zhandabo.overgame.service.BannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/banners")
@Api(value = "Методы для работы с банерами")
public class BannerController {

    private final BannerService bannerService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation("Создание банера")
    public void create(@RequestPart BannerCreateDto banner,
                       @RequestPart(value = "imgFile") MultipartFile imgFile) {
        banner.setImgFile(imgFile);
        bannerService.create(banner);
    }

    @PutMapping("/{bannerId}")
    @ApiOperation("Редактирование банера")
    public void edit(@RequestBody BannerCreateDto banner, @PathVariable Long bannerId) {
        bannerService.edit(banner, bannerId);
    }

    @DeleteMapping("/{bannerId}")
    @ApiOperation("Удаление банера")
    public void delete(@PathVariable Long bannerId) {
        bannerService.delete(bannerId);
    }

    @GetMapping
    @ApiOperation("Получение списка всех банеров")
    public List<BannerViewDto> getAllActiveBanners() {
        return bannerService.getAllActiveBanners();
    }

    @GetMapping("/codes")
    @ApiOperation("Получение списка всех кодов банеров")
    public List<BannerCode> getAllBannersCode() {
        return bannerService.getAllBannersCode();
    }
}
