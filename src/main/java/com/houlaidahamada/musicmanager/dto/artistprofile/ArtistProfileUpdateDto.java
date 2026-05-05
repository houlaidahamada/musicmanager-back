package com.houlaidahamada.musicmanager.dto.artistprofile;

import lombok.Data;

@Data
public class ArtistProfileUpdateDto {

    private String artistName;
    private String biography;
    private String profilePictureUrl;
}