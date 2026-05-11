package com.houlaidahamada.musicmanager.controller;

import com.houlaidahamada.musicmanager.dto.theme.ThemeCreateDto;
import com.houlaidahamada.musicmanager.dto.theme.ThemeResponseDto;
import com.houlaidahamada.musicmanager.service.ThemeService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/themes")
public class ThemeController {

    private final ThemeService themeService;

    public ThemeController(ThemeService themeService) {
        this.themeService = themeService;
    }

    @PostMapping
    public ThemeResponseDto createTheme(@Valid @RequestBody ThemeCreateDto themeCreateDto) {
        return themeService.createTheme(themeCreateDto);
    }
}