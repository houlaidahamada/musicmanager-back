package com.houlaidahamada.musicmanager.dto.song;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SongCreateDto {

    @NotBlank
    private String title;

    private String description;

    private Double duration;
}
