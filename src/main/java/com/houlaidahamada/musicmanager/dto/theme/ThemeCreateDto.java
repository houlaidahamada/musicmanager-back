package com.houlaidahamada.musicmanager.dto.theme;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ThemeCreateDto {

    @NotBlank
    private String name;

    private String description;

    private String color;
}