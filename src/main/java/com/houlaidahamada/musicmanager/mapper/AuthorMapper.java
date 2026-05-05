package com.houlaidahamada.musicmanager.mapper;

import com.houlaidahamada.musicmanager.dto.author.AuthorCreateDto;
import com.houlaidahamada.musicmanager.dto.author.AuthorResponseDto;
import com.houlaidahamada.musicmanager.dto.author.AuthorUpdateDto;
import com.houlaidahamada.musicmanager.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    Author toEntity(AuthorCreateDto dto);

    AuthorResponseDto toDto(Author author);

    void updateEntityFromDto(AuthorUpdateDto dto, @MappingTarget Author author);

    Author toEntity(AuthorUpdateDto dto);
}
