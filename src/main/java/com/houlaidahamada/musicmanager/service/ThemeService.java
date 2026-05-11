package com.houlaidahamada.musicmanager.service;

import com.houlaidahamada.musicmanager.dto.theme.ThemeCreateDto;
import com.houlaidahamada.musicmanager.dto.theme.ThemeResponseDto;
import com.houlaidahamada.musicmanager.entity.Theme;
import com.houlaidahamada.musicmanager.mapper.ThemeMapper;
import com.houlaidahamada.musicmanager.repository.ThemeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ThemeService {

    private final ThemeRepository themeRepository;
    private final ThemeMapper themeMapper;

    public ThemeService(ThemeRepository themeRepository, ThemeMapper themeMapper) {
        this.themeRepository = themeRepository;
        this.themeMapper = themeMapper;
    }

    public ThemeResponseDto createTheme(ThemeCreateDto dto) {
        // Check if name is unique
        if (themeRepository.findByName(dto.getName()).isPresent()) {
            throw new IllegalArgumentException("Theme name must be unique");
        }

        Theme theme = themeMapper.toEntity(dto);
        Theme saved = themeRepository.save(theme);
        return themeMapper.toDto(saved);
    }
}