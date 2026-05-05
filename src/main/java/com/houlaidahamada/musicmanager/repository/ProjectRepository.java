package com.houlaidahamada.musicmanager.repository;

import com.houlaidahamada.musicmanager.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByArtistProfileId(Long profileId);
}