package fr.cytech.projetjeespring.mappers;

import fr.cytech.projetjeespring.dtos.BonusFormDTO;
import fr.cytech.projetjeespring.entities.Bonus;
import fr.cytech.projetjeespring.entities.Employee;
import fr.cytech.projetjeespring.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BonusMapper {

    private final EmployeeRepository employeeRepository;

    public BonusFormDTO toDto(Bonus entity) {
        if (entity == null) return null;
        BonusFormDTO dto = new BonusFormDTO();
        dto.setId(entity.getId());
        dto.setEmployeeId(entity.getEmployee().getId());
        dto.setAmount(entity.getAmount());
        dto.setReason(entity.getReason());
        dto.setAwardDate(entity.getAwardDate());
        return dto;
    }

    public Bonus toEntity(BonusFormDTO dto) {
        Bonus entity = new Bonus();
        updateEntityFromDto(dto, entity);
        return entity;
    }

    public void updateEntityFromDto(BonusFormDTO dto, Bonus entity) {
        entity.setAmount(dto.getAmount());
        entity.setReason(dto.getReason());
        entity.setAwardDate(dto.getAwardDate());

        if (dto.getEmployeeId() != null) {
            Employee emp = employeeRepository.findById(dto.getEmployeeId()).orElse(null);
            entity.setEmployee(emp);
        }
    }
}