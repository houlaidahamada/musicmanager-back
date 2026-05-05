package com.houlaidahamada.musicmanager.mapper;

import com.houlaidahamada.musicmanager.dto.reference.ReferenceCreateDto;
import com.houlaidahamada.musicmanager.dto.reference.ReferenceResponseDto;
import com.houlaidahamada.musicmanager.entity.Reference;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReferenceMapper {

    Reference toEntity(ReferenceCreateDto dto);

    ReferenceResponseDto toDto(Reference reference);
}
