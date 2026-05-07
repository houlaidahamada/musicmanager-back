package com.houlaidahamada.musicmanager.dto.song;

import com.houlaidahamada.musicmanager.dto.author.AuthorResponseDto;
import com.houlaidahamada.musicmanager.dto.lyrics.LyricsResponseDto;
import com.houlaidahamada.musicmanager.dto.reference.ReferenceResponseDto;
import com.houlaidahamada.musicmanager.dto.theme.ThemeResponseDto;
import com.houlaidahamada.musicmanager.entity.Song;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class SongResponseDto {

    private Long id;
    private String title;
    private String description;
    private Song.SongStatus status;
    private Double duration;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Set<AuthorResponseDto> authors;
    private Set<ThemeResponseDto> themes;
    private LyricsResponseDto lyrics;
    private Set<ReferenceResponseDto> references;
}
