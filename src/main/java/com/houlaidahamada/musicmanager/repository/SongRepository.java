package com.houlaidahamada.musicmanager.repository;

import com.houlaidahamada.musicmanager.entity.Song;
import com.houlaidahamada.musicmanager.entity.Song.SongStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findByArtistProfileId(Long profileId);
    List<Song> findByProjectId(Long projectId);
    List<Song> findByStatus(SongStatus status);
}
