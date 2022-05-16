package com.zhandabo.overgame.converter;

import com.zhandabo.overgame.model.dto.banner.BannerViewDto;
import com.zhandabo.overgame.model.entity.Banner;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BannerViewDtoConverter implements Converter<Banner, BannerViewDto> {
    @Override
    public BannerViewDto convert(Banner source) {
        BannerViewDto target = new BannerViewDto();
        target.setId(source.getId());
        target.setCode(source.getCode());
        target.setName(source.getName().get("en"));
        target.setDescription(source.getDescription().get("en"));
        target.setImgUrl(source.getImgLink());
        return target;
    }
}
