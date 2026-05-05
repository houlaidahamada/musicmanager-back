package com.houlaidahamada.musicmanager.dto.lyrics;

import com.houlaidahamada.musicmanager.entity.Lyrics;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class LyricsResponseDto {

    private Long id;
    private String content;
    private Lyrics.LyricsFormat format;
    private String language;
    private Integer version;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}