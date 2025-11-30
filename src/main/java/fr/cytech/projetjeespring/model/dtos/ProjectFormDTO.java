package fr.cytech.projetjeespring.model.dtos;

import fr.cytech.projetjeespring.enums.ProjectState;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProjectFormDTO {
    private Integer id;

    @NotBlank(message = "Project name is required")
    private String name;

    private String description;

    @NotNull(message = "State is required")
    private ProjectState state;

    @NotNull(message = "Department is required")
    private Integer departmentId;
}