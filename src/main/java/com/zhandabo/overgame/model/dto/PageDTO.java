package com.zhandabo.overgame.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PageDTO<T> {

    private Long total;
    private Integer pageNumber;
    private Integer totalPages;
    private List<T> content;

    public PageDTO() {
        this.total = 0L;
        this.pageNumber = 0;
        this.totalPages = 0;
        this.content = new ArrayList<>();
    }

    public static <T> PageDTO<T> empty() {
        return new PageDTO<>();
    }
}
