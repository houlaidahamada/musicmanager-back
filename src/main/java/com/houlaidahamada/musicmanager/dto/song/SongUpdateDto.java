package com.houlaidahamada.musicmanager.dto.song;

import com.houlaidahamada.musicmanager.entity.Song;
import lombok.Data;

@Data
public class SongUpdateDto {

    private String title;
    private String description;
    private Song.SongStatus status;
    private Double duration;
}
