package fr.cytech.projetjeespring.dtos;

import lombok.Data;

@Data
public class EmployeeSummaryDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String type;
    private String departmentName;
}
