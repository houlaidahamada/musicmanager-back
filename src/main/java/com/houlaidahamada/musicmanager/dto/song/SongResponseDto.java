package com.houlaidahamada.musicmanager.dto.song;

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
    private Set<String> authors;
    private Set<String> themes;
    private String lyrics;
}
