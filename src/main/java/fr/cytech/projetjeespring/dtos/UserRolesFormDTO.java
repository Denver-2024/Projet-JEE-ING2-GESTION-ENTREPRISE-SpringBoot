package fr.cytech.projetjeespring.dtos;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class UserRolesFormDTO {
    private Integer employeeId;
    private String employeeName;
    private List<String> selectedRoles = new ArrayList<>();
}