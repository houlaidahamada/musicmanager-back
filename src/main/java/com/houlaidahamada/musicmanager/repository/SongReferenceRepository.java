package com.houlaidahamada.musicmanager.repository;

import com.houlaidahamada.musicmanager.entity.SongReference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongReferenceRepository extends JpaRepository<SongReference, Long> {
    List<SongReference> findBySongId(Long songId);
}