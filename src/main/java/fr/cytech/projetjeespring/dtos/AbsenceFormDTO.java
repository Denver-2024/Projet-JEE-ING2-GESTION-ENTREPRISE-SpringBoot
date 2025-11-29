package fr.cytech.projetjeespring.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Data
public class AbsenceFormDTO {
    private Integer id;

    @NotNull(message = "Employee is required")
    private Integer employeeId;

    @NotNull(message = "Start date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    private String reason;
    private boolean expected;
    private boolean paidLeave;
}