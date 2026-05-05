package com.houlaidahamada.musicmanager.dto.artistprofile;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ArtistProfileResponseDto {

    private Long id;
    private String artistName;
    private String biography;
    private String profilePictureUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}