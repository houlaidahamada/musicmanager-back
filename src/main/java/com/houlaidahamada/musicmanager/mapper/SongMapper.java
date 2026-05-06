package com.houlaidahamada.musicmanager.mapper;

import com.houlaidahamada.musicmanager.dto.song.SongCreateDto;
import com.houlaidahamada.musicmanager.dto.song.SongResponseDto;
import com.houlaidahamada.musicmanager.dto.song.SongUpdateDto;
import com.houlaidahamada.musicmanager.entity.Author;
import com.houlaidahamada.musicmanager.entity.Lyrics;
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
        return author != null ? author.getFullName() : null;
    }

    default String map(Lyrics lyrics) {
        return lyrics != null ? lyrics.getContent() : null;
    }

    default String mapTheme(Theme theme) {
        return theme != null ? theme.getName() : null;
    }
}
