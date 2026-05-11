package com.houlaidahamada.musicmanager.dto.theme;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ThemeCreateDto {

    @NotBlank
    private String name;

    private String description;

    @Pattern(regexp = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$", message = "Color must be a valid hex code (e.g., #FFFFFF or #fff)")
    private String color;
}