package com.houlaidahamada.musicmanager.mapper;

import com.houlaidahamada.musicmanager.dto.author.AuthorCreateDto;
import com.houlaidahamada.musicmanager.dto.author.AuthorResponseDto;
import com.houlaidahamada.musicmanager.entity.Author;
import com.houlaidahamada.musicmanager.entity.Song;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    Author toEntity(AuthorCreateDto dto);

    @Mapping(source = "songs", target = "songIds")
    AuthorResponseDto toDto(Author author);

    default Long map(Song song) {
        return song.getId();
    }
}
