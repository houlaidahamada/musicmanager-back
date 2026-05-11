package com.houlaidahamada.musicmanager.dto.theme;

import lombok.Data;

@Data
public class ThemeResponseDto {

    private Long id;
    private String name;
    private String description;
    private String color;
    private Long songCount;

    public ThemeResponseDto() {
    }

    public ThemeResponseDto(Long id, String name, String description, String color, Long songCount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.color = color;
        this.songCount = songCount;
    }
}