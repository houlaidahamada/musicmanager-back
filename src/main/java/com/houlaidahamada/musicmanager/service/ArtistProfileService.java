package com.houlaidahamada.musicmanager.service;

import com.houlaidahamada.musicmanager.dto.artistprofile.ArtistProfileCreateDto;
import com.houlaidahamada.musicmanager.dto.artistprofile.ArtistProfileResponseDto;
import com.houlaidahamada.musicmanager.dto.artistprofile.ArtistProfileUpdateDto;
import com.houlaidahamada.musicmanager.entity.ArtistProfile;
import com.houlaidahamada.musicmanager.entity.User;
import com.houlaidahamada.musicmanager.mapper.ArtistProfileMapper;
import com.houlaidahamada.musicmanager.repository.ArtistProfileRepository;
import com.houlaidahamada.musicmanager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

        if (artistProfileRepository.existsByUserIdAndArtistNameIgnoreCase(user.getId(), dto.getArtistName())) {
            throw new IllegalArgumentException("Un profil artistique avec ce nom existe déjà pour cet utilisateur.");
        }

        ArtistProfile artistProfile = artistProfileMapper.toEntity(dto);
        artistProfile.setUser(user);
        return artistProfileMapper.toDto(artistProfileRepository.save(artistProfile));
    }

    public List<ArtistProfileResponseDto> getArtistProfiles(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        return artistProfileRepository.findByUserId(user.getId())
                .stream()
                .map(artistProfileMapper::toDto)
                .collect(Collectors.toList());
    }

    public ArtistProfileResponseDto updateArtistProfile(Long id, ArtistProfileUpdateDto dto, String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        ArtistProfile artistProfile = artistProfileRepository.findById(id).orElseThrow();

        // Vérifier que l'utilisateur connecté est propriétaire du profil
        if (!artistProfile.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("Vous n'avez pas la permission de modifier ce profil artistique.");
        }

        // Vérifier si le nouveau nom d'artiste est déjà utilisé par un autre profil
        if (dto.getArtistName() != null && !dto.getArtistName().equals(artistProfile.getArtistName())) {
            if (artistProfileRepository.existsByUserIdAndArtistNameIgnoreCase(user.getId(), dto.getArtistName())) {
                throw new IllegalArgumentException("Un profil artistique avec ce nom existe déjà pour cet utilisateur.");
            }
        }

        artistProfileMapper.updateEntityFromDto(dto, artistProfile);
        return artistProfileMapper.toDto(artistProfileRepository.save(artistProfile));
    }
}
