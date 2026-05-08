package com.houlaidahamada.musicmanager.dto.song;

import com.houlaidahamada.musicmanager.entity.Song;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SongStatusUpdateDto {

    @NotNull(message = "Status is required")
    private Song.SongStatus status;
}