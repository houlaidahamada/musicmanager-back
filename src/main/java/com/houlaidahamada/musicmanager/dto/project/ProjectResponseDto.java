package com.houlaidahamada.musicmanager.dto.project;

import com.houlaidahamada.musicmanager.entity.Project;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ProjectResponseDto {

    private Long id;
    private String name;
    private String description;
    private Project.ProjectType type;
    private LocalDate releaseDate;
    private String coverImageUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<String> songs;
}