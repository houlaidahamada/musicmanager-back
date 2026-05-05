package com.houlaidahamada.musicmanager.mapper;

import com.houlaidahamada.musicmanager.dto.song.SongCreateDto;
import com.houlaidahamada.musicmanager.dto.song.SongResponseDto;
import com.houlaidahamada.musicmanager.dto.song.SongUpdateDto;
import com.houlaidahamada.musicmanager.entity.Author;
import com.houlaidahamada.musicmanager.entity.Song;
import com.houlaidahamada.musicmanager.entity.Theme;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SongMapper {
    Song toEntity(SongCreateDto dto);

    SongResponseDto toDto(Song song);

    void updateEntityFromDto(SongUpdateDto dto, @MappingTarget Song song);

    default String map(Author author) {
        return author.getFullName();
    }

    default String mapTheme(Theme theme) {
        return theme.getName();
    }
}
