package com.houlaidahamada.musicmanager.mapper;

import com.houlaidahamada.musicmanager.dto.theme.ThemeCreateDto;
import com.houlaidahamada.musicmanager.dto.theme.ThemeResponseDto;
import com.houlaidahamada.musicmanager.dto.theme.ThemeUpdateDto;
import com.houlaidahamada.musicmanager.entity.Theme;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ThemeMapper {

    Theme toEntity(ThemeCreateDto dto);

    ThemeResponseDto toDto(Theme theme);

    void updateEntityFromDto(ThemeUpdateDto dto, @MappingTarget Theme theme);
}
