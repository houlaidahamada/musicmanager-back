package com.houlaidahamada.musicmanager.dto.lyrics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LyricsCompareResponseDto {

    private LyricsResponseDto baseVersion;
    private LyricsResponseDto compareVersion;
}
