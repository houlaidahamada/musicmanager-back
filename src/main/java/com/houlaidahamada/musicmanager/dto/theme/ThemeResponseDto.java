package com.houlaidahamada.musicmanager.dto.theme;

import lombok.Data;

@Data
public class ThemeResponseDto {

    private Long id;
    private String name;
    private String description;
    private String color;
}