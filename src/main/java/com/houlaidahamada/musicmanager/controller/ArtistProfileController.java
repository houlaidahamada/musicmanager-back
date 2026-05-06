package com.houlaidahamada.musicmanager.controller;

import com.houlaidahamada.musicmanager.dto.artistprofile.ArtistProfileCreateDto;
import com.houlaidahamada.musicmanager.dto.artistprofile.ArtistProfileResponseDto;
import com.houlaidahamada.musicmanager.service.ArtistProfileService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/artist-profiles")
public class ArtistProfileController {

    private final ArtistProfileService artistProfileService;

    public ArtistProfileController(ArtistProfileService artistProfileService) {
        this.artistProfileService = artistProfileService;
    }

    @PostMapping
    public ArtistProfileResponseDto createArtistProfile(
            @Valid @RequestBody ArtistProfileCreateDto artistProfileCreateDto,
            Principal principal
    ) {
        return artistProfileService.createArtistProfile(artistProfileCreateDto, principal.getName());
    }

    @GetMapping
    public List<ArtistProfileResponseDto> getArtistProfiles(Principal principal) {
        return artistProfileService.getArtistProfiles(principal.getName());
    }
}
