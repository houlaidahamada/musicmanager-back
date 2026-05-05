package com.houlaidahamada.musicmanager.repository;

import com.houlaidahamada.musicmanager.entity.Reference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReferenceRepository extends JpaRepository<Reference, Long> {
    List<Reference> findBySongId(Long songId);
}