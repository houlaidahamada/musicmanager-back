package com.houlaidahamada.musicmanager.mapper;

import com.houlaidahamada.musicmanager.dto.project.ProjectCreateDto;
import com.houlaidahamada.musicmanager.dto.project.ProjectResponseDto;
import com.houlaidahamada.musicmanager.dto.project.ProjectUpdateDto;
import com.houlaidahamada.musicmanager.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    Project toEntity(ProjectCreateDto dto);

    ProjectResponseDto toDto(Project project);

    void updateEntityFromDto(ProjectUpdateDto dto, @MappingTarget Project project);
}
