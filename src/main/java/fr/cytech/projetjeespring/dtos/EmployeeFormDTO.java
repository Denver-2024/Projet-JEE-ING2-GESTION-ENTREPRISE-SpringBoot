package fr.cytech.projetjeespring.dtos;

import fr.cytech.projetjeespring.enums.EmployeeType;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class EmployeeFormDTO {

    private Integer id;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    private String address;
    private String gender;

    @NotNull(message = "Type is required")
    private EmployeeType type;

    @NotNull(message = "Salary is required")
    @Positive(message = "Salary must be positive")
    private BigDecimal salary;

    private String password;

    private Integer departmentId;
}