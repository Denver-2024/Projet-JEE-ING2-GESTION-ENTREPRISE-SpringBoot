package fr.cytech.projetjeespring.model.dtos;

import fr.cytech.projetjeespring.enums.ProjectState;
import lombok.Data;

@Data
public class ProjectSummaryDTO {
    private Integer id;
    private String name;
    private String description;
    private ProjectState state;
    private String departmentName;
}