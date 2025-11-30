package fr.cytech.projetjeespring.dtos;

import fr.cytech.projetjeespring.enums.DeductionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class PayrollDeductionFormDTO {
    private Integer id;

    @NotBlank(message = "Name is required")
    private String name;

    private String description;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    private BigDecimal amount;

    @NotNull(message = "Type is required")
    private DeductionType type;
}