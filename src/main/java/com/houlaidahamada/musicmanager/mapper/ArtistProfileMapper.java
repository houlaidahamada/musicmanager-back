package com.houlaidahamada.musicmanager.mapper;

import com.houlaidahamada.musicmanager.dto.artistprofile.ArtistProfileCreateDto;
import com.houlaidahamada.musicmanager.dto.artistprofile.ArtistProfileResponseDto;
import com.houlaidahamada.musicmanager.dto.artistprofile.ArtistProfileUpdateDto;
import com.houlaidahamada.musicmanager.entity.ArtistProfile;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ArtistProfileMapper {

    ArtistProfile toEntity(ArtistProfileCreateDto dto);

    ArtistProfileResponseDto toDto(ArtistProfile artistProfile);

    void updateEntityFromDto(ArtistProfileUpdateDto dto, @MappingTarget ArtistProfile artistProfile);
}
