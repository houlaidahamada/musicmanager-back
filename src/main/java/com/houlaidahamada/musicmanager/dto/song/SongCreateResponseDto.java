package com.houlaidahamada.musicmanager.dto.song;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class SongCreateResponseDto {

    private Long id;
    private String title;
    private String description;
    private String status;
    private Double duration;
    private LocalDateTime createdAt;
    private String artistName;
}