package com.houlaidahamada.musicmanager.dto.lyrics;

import com.houlaidahamada.musicmanager.entity.Lyrics;
import lombok.Data;

@Data
public class LyricsUpdateDto {

    private String content;
    private Lyrics.LyricsFormat format;
    private String language;
}