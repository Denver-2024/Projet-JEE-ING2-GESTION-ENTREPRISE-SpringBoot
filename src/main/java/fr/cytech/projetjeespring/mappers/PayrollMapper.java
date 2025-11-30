package fr.cytech.projetjeespring.mappers;

import fr.cytech.projetjeespring.dtos.PayrollDeductionFormDTO;
import fr.cytech.projetjeespring.entities.PayrollDeduction;
import org.springframework.stereotype.Component;

@Component
public class PayrollMapper {

    public PayrollDeductionFormDTO toDto(PayrollDeduction entity) {
        if (entity == null) return null;
        PayrollDeductionFormDTO dto = new PayrollDeductionFormDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setAmount(entity.getAmount());
        dto.setType(entity.getType());
        return dto;
    }

    public PayrollDeduction toEntity(PayrollDeductionFormDTO dto) {
        PayrollDeduction entity = new PayrollDeduction();
        updateEntityFromDto(dto, entity);
        return entity;
    }

    public void updateEntityFromDto(PayrollDeductionFormDTO dto, PayrollDeduction entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setAmount(dto.getAmount());
        entity.setType(dto.getType());
    }
}