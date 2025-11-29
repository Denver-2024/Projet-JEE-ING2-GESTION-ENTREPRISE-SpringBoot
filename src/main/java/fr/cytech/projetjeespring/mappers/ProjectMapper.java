package fr.cytech.projetjeespring.mappers;

import fr.cytech.projetjeespring.dtos.ProjectFormDTO;
import fr.cytech.projetjeespring.dtos.ProjectSummaryDTO;
import fr.cytech.projetjeespring.entities.Department;
import fr.cytech.projetjeespring.entities.Project;
import fr.cytech.projetjeespring.repositories.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProjectMapper {

    private final DepartmentRepository departmentRepository;

    public ProjectFormDTO toDto(Project entity) {
        if (entity == null) return null;

        ProjectFormDTO dto = new ProjectFormDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setState(entity.getState());

        if (entity.getDepartment() != null) {
            dto.setDepartmentId(entity.getDepartment().getId());
        }
        return dto;
    }

    public Project toEntity(ProjectFormDTO dto) {
        Project entity = new Project();
        updateEntityFromDto(dto, entity);
        return entity;
    }

    public void updateEntityFromDto(ProjectFormDTO dto, Project entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setState(dto.getState());

        if (dto.getDepartmentId() != null) {
            Department dept = departmentRepository.findById(dto.getDepartmentId()).orElse(null);
            entity.setDepartment(dept);
        }
    }

    public ProjectSummaryDTO toSummaryDto(Project entity) {
        if (entity == null) return null;

        ProjectSummaryDTO dto = new ProjectSummaryDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setState(entity.getState());

        if (entity.getDepartment() != null) {
            dto.setDepartmentName(entity.getDepartment().getName());
        } else {
            dto.setDepartmentName("Unassigned");
        }
        return dto;
    }

    public List<ProjectSummaryDTO> toSummaryDtoList(List<Project> entities) {
        if (entities == null) return List.of();
        return entities.stream().map(this::toSummaryDto).collect(Collectors.toList());
    }
}