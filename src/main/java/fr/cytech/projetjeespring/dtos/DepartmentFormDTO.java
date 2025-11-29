package fr.cytech.projetjeespring.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DepartmentFormDTO {
    private Integer id;

    @NotBlank(message = "Department name is required")
    private String name;

    private String description;

    private Integer headEmployeeId;
}