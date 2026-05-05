package com.houlaidahamada.musicmanager.dto.lyrics;

import com.houlaidahamada.musicmanager.entity.Lyrics;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LyricsCreateDto {

    @NotBlank
    private String content;

    private Lyrics.LyricsFormat format = Lyrics.LyricsFormat.PLAIN_TEXT;

    private String language;
}