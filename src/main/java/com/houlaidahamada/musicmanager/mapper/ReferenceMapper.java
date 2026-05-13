package com.houlaidahamada.musicmanager.mapper;

import com.houlaidahamada.musicmanager.dto.reference.ReferenceCreateDto;
import com.houlaidahamada.musicmanager.dto.reference.ReferenceResponseDto;
import com.houlaidahamada.musicmanager.entity.SongReference;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReferenceMapper {

    SongReference toEntity(ReferenceCreateDto dto);

    ReferenceResponseDto toDto(SongReference reference);
}
