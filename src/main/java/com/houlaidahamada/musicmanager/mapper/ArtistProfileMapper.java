package com.houlaidahamada.musicmanager.mapper;

import com.houlaidahamada.musicmanager.dto.artistprofile.ArtistProfileCreateDto;
import com.houlaidahamada.musicmanager.dto.artistprofile.ArtistProfileResponseDto;
import com.houlaidahamada.musicmanager.dto.artistprofile.ArtistProfileUpdateDto;
import com.houlaidahamada.musicmanager.entity.ArtistProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ArtistProfileMapper {

    ArtistProfile toEntity(ArtistProfileCreateDto dto);

    @Mapping(target = "songCount", expression = "java(artistProfile.getSongs() != null ? artistProfile.getSongs().size() : 0)")
    @Mapping(target = "albumCount", expression = "java(artistProfile.getProjects() != null ? artistProfile.getProjects().size() : 0)")
    ArtistProfileResponseDto toDto(ArtistProfile artistProfile);

    void updateEntityFromDto(ArtistProfileUpdateDto dto, @MappingTarget ArtistProfile artistProfile);
}
