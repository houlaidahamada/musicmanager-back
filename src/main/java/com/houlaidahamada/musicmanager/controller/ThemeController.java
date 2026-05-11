package com.houlaidahamada.musicmanager.controller;

import com.houlaidahamada.musicmanager.dto.theme.ThemeCreateDto;
import com.houlaidahamada.musicmanager.dto.theme.ThemeResponseDto;
import com.houlaidahamada.musicmanager.service.ThemeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/themes")
public class ThemeController {

    private final ThemeService themeService;

    public ThemeController(ThemeService themeService) {
        this.themeService = themeService;
    }

    @GetMapping
    public Page<ThemeResponseDto> getThemes(
            @PageableDefault(sort = "name", direction = Sort.Direction.ASC, size = 20) Pageable pageable
    ) {
        return themeService.getThemes(pageable);
    }

    @PostMapping
    public ThemeResponseDto createTheme(@Valid @RequestBody ThemeCreateDto themeCreateDto) {
        return themeService.createTheme(themeCreateDto);
    }
}