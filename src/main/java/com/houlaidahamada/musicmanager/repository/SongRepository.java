package com.houlaidahamada.musicmanager.repository;

import com.houlaidahamada.musicmanager.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long>, JpaSpecificationExecutor<Song> {

    List<Song> findByArtistProfileId(Long profileId);

    List<Song> findByProjectId(Long projectId);

}
