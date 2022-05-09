package com.zhandabo.overgame.util;

import com.zhandabo.overgame.model.dto.PageDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public class PageConverterUtils {
    public PageConverterUtils() {
    }

    public static <T> PageDTO<T> convert(Page<T> page) {
        return convert(page, page.getContent());
    }

    public static <T> PageDTO<T> convert(Page<?> page, List<T> content) {
        return of(content, page.getTotalElements(), page.getNumber(), page.getTotalPages());
    }

    public static <T> PageDTO<T> of(List<T> content, long total, int pageNumber, int pageTotal) {
        PageDTO<T> pageDTO = new PageDTO();
        pageDTO.setContent(content);
        pageDTO.setTotal(total);
        pageDTO.setPageNumber(pageNumber);
        pageDTO.setTotalPages(pageTotal);
        return pageDTO;
    }
}