package fr.cytech.projetjeespring.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class BonusFormDTO {
    private Integer id;

    @NotNull(message = "Employee is required")
    private Integer employeeId;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    private BigDecimal amount;

    private String reason;

    @NotNull(message = "Award date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate awardDate;
}