package com.houlaidahamada.musicmanager.service;

import com.houlaidahamada.musicmanager.dto.artistprofile.ArtistProfileCreateDto;
import com.houlaidahamada.musicmanager.dto.artistprofile.ArtistProfileResponseDto;
import com.houlaidahamada.musicmanager.entity.ArtistProfile;
import com.houlaidahamada.musicmanager.entity.User;
import com.houlaidahamada.musicmanager.mapper.ArtistProfileMapper;
import com.houlaidahamada.musicmanager.repository.ArtistProfileRepository;
import com.houlaidahamada.musicmanager.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ArtistProfileService {

    private final ArtistProfileRepository artistProfileRepository;
    private final ArtistProfileMapper artistProfileMapper;
    private final UserRepository userRepository;

    public ArtistProfileService(ArtistProfileRepository artistProfileRepository,
                                ArtistProfileMapper artistProfileMapper,
                                UserRepository userRepository) {
        this.artistProfileRepository = artistProfileRepository;
        this.artistProfileMapper = artistProfileMapper;
        this.userRepository = userRepository;
    }

    public ArtistProfileResponseDto createArtistProfile(ArtistProfileCreateDto dto, String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        ArtistProfile artistProfile = artistProfileMapper.toEntity(dto);
        artistProfile.setUser(user);
        return artistProfileMapper.toDto(artistProfileRepository.save(artistProfile));
    }
}
