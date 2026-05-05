package com.houlaidahamada.musicmanager.dto.artistprofile;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ArtistProfileCreateDto {

    @NotBlank
    private String artistName;

    private String biography;

    private String profilePictureUrl;
}