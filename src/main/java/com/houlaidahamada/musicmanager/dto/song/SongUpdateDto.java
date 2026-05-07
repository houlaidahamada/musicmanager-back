package com.houlaidahamada.musicmanager.dto.song;

import com.houlaidahamada.musicmanager.entity.Song;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class SongUpdateDto {

    private String title;

    private String description;

    private Song.SongStatus status;

    @Positive(message = "Duration must be positive")
    private Double duration;
}
