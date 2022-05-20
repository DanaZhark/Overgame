package com.zhandabo.overgame.model.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FileResponse {
    private String fullPath;
}