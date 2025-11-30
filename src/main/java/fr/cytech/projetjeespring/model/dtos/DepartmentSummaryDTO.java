package fr.cytech.projetjeespring.model.dtos;

import lombok.Data;

@Data
public class DepartmentSummaryDTO {
    private Integer id;
    private String name;
    private String description;
    private String headName;
    private String headEmail;
}