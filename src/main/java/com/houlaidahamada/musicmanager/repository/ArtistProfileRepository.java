package com.houlaidahamada.musicmanager.repository;

import com.houlaidahamada.musicmanager.entity.ArtistProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistProfileRepository extends JpaRepository<ArtistProfile, Long> {
    List<ArtistProfile> findByUserId(Long userId);
    boolean existsByUserIdAndArtistNameIgnoreCase(Long userId, String artistName);
}