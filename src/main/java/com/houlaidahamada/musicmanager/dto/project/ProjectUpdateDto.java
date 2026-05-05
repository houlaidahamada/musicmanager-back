package com.houlaidahamada.musicmanager.dto.project;

import com.houlaidahamada.musicmanager.entity.Project;
import lombok.Data;
import java.time.LocalDate;

@Data
public class ProjectUpdateDto {

    private String name;
    private String description;
    private Project.ProjectType type;
    private LocalDate releaseDate;
    private String coverImageUrl;
}