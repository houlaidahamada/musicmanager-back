package com.houlaidahamada.musicmanager.dto.song;

import com.houlaidahamada.musicmanager.entity.Song;
import lombok.Data;

@Data
public class SongStatusUpdateDto {
    private Song.SongStatus status;
}
