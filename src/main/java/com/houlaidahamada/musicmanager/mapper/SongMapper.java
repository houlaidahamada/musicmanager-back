package com.houlaidahamada.musicmanager.mapper;

import com.houlaidahamada.musicmanager.dto.song.SongCreateDto;
import com.houlaidahamada.musicmanager.dto.song.SongResponseDto;
import com.houlaidahamada.musicmanager.entity.Author;
import com.houlaidahamada.musicmanager.entity.Song;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SongMapper {
    Song toEntity(SongCreateDto dto);

    SongResponseDto toDto(Song song);

    default String map(Author author) {
        return author.getFullName();
    }
}
