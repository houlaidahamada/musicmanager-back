package com.houlaidahamada.musicmanager.dto.artistprofile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ArtistProfileUpdateDto {

    @NotBlank(message = "Le nom d'artiste est requis")
    @Size(min = 1, max = 255, message = "Le nom d'artiste doit contenir entre 1 et 255 caractères")
    private String artistName;

    @Size(max = 5000, message = "La biographie ne doit pas dépasser 5000 caractères")
    private String biography;

    @Size(max = 2048, message = "L'URL de la photo de profil ne doit pas dépasser 2048 caractères")
    private String profilePictureUrl;
}