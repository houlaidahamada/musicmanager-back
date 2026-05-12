package com.houlaidahamada.musicmanager.mapper;

import com.houlaidahamada.musicmanager.dto.author.AuthorResponseDto;
import com.houlaidahamada.musicmanager.dto.lyrics.LyricsResponseDto;
import com.houlaidahamada.musicmanager.dto.reference.ReferenceResponseDto;
import com.houlaidahamada.musicmanager.dto.song.SongCreateDto;
import com.houlaidahamada.musicmanager.dto.song.SongCreateResponseDto;
import com.houlaidahamada.musicmanager.dto.song.SongResponseDto;
import com.houlaidahamada.musicmanager.dto.song.SongUpdateDto;
import com.houlaidahamada.musicmanager.dto.theme.ThemeResponseDto;
import com.houlaidahamada.musicmanager.entity.ArtistProfile;
import com.houlaidahamada.musicmanager.entity.Author;
import com.houlaidahamada.musicmanager.entity.Lyrics;
import com.houlaidahamada.musicmanager.entity.Reference;
import com.houlaidahamada.musicmanager.entity.Song;
import com.houlaidahamada.musicmanager.entity.Theme;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface SongMapper {
    Song toEntity(SongCreateDto dto);

    @Mapping(target = "authors", expression = "java(mapAuthorsToResponseDtos(song.getAuthors()))")
    @Mapping(target = "themes", expression = "java(mapThemesToResponseDtos(song.getThemes()))")
    @Mapping(target = "lyrics", expression = "java(song.getLatestLyrics())")
    @Mapping(target = "references", expression = "java(mapReferencesToResponseDtos(song.getReferences()))")
    SongResponseDto toDto(Song song);

    @Mapping(target = "status", expression = "java(song.getStatus().name())")
    @Mapping(target = "artistName", source = "artistProfile.artistName")
    SongCreateResponseDto toCreateResponseDto(Song song);

    void updateEntityFromDto(SongUpdateDto dto, @MappingTarget Song song);

    default String map(Song.SongStatus status) {
        return status != null ? status.name() : null;
    }

    default String map(ArtistProfile artistProfile) {
        return artistProfile != null ? artistProfile.getArtistName() : null;
    }

    default String map(Author author) {
        return author != null ? author.getFullName() : null;
    }

    default String map(Lyrics lyrics) {
        return lyrics != null ? lyrics.getContent() : null;
    }

    default String mapTheme(Theme theme) {
        return theme != null ? theme.getName() : null;
    }

    // Methods to map nested DTOs
    default Set<AuthorResponseDto> mapAuthorsToResponseDtos(Set<Author> authors) {
        if (authors == null) {
            return null;
        }
        return authors.stream()
                .map(this::mapAuthorToResponseDto)
                .collect(Collectors.toSet());
    }

    default AuthorResponseDto mapAuthorToResponseDto(Author author) {
        if (author == null) {
            return null;
        }
        AuthorResponseDto dto = new AuthorResponseDto();
        dto.setId(author.getId());
        dto.setFullName(author.getFullName());
        dto.setRole(author.getRole());
        dto.setEmail(author.getEmail());
        return dto;
    }

    default Set<ThemeResponseDto> mapThemesToResponseDtos(Set<Theme> themes) {
        if (themes == null) {
            return null;
        }
        return themes.stream()
                .map(this::mapThemeToResponseDto)
                .collect(Collectors.toSet());
    }

    default ThemeResponseDto mapThemeToResponseDto(Theme theme) {
        if (theme == null) {
            return null;
        }
        ThemeResponseDto dto = new ThemeResponseDto();
        dto.setId(theme.getId());
        dto.setName(theme.getName());
        dto.setDescription(theme.getDescription());
        dto.setColor(theme.getColor());
        return dto;
    }

    default Set<ReferenceResponseDto> mapReferencesToResponseDtos(Set<Reference> references) {
        if (references == null) {
            return null;
        }
        return references.stream()
                .map(this::mapReferenceToResponseDto)
                .collect(Collectors.toSet());
    }

    default ReferenceResponseDto mapReferenceToResponseDto(Reference reference) {
        if (reference == null) {
            return null;
        }
        ReferenceResponseDto dto = new ReferenceResponseDto();
        dto.setId(reference.getId());
        dto.setTitle(reference.getTitle());
        dto.setUrl(reference.getUrl());
        dto.setType(reference.getType());
        dto.setDescription(reference.getDescription());
        return dto;
    }

    default LyricsResponseDto mapLyricsToResponseDto(Lyrics lyrics) {
        if (lyrics == null) {
            return null;
        }
        LyricsResponseDto dto = new LyricsResponseDto();
        dto.setId(lyrics.getId());
        dto.setContent(lyrics.getContent());
        dto.setFormat(lyrics.getFormat());
        dto.setLanguage(lyrics.getLanguage());
        dto.setVersion(lyrics.getVersion());
        dto.setCreatedAt(lyrics.getCreatedAt());
        dto.setUpdatedAt(lyrics.getUpdatedAt());
        return dto;
    }
}
