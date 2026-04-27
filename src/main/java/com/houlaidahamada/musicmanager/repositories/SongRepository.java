package com.houlaidahamada.musicmanager.repositories;

import com.houlaidahamada.musicmanager.entities.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
  List<Song> findByCompleted(boolean completed);
  List<Song> findByTitleContainingIgnoreCase(String keyword);
}
