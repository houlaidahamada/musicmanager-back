package com.houlaidahamada.musicmanager.mapper;

import com.houlaidahamada.musicmanager.dto.lyrics.LyricsCreateDto;
import com.houlaidahamada.musicmanager.dto.lyrics.LyricsResponseDto;
import com.houlaidahamada.musicmanager.dto.lyrics.LyricsUpdateDto;
import com.houlaidahamada.musicmanager.entity.Lyrics;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface LyricsMapper {

    Lyrics toEntity(LyricsCreateDto dto);

    LyricsResponseDto toDto(Lyrics lyrics);

    void updateEntityFromDto(LyricsUpdateDto dto, @MappingTarget Lyrics lyrics);
}
