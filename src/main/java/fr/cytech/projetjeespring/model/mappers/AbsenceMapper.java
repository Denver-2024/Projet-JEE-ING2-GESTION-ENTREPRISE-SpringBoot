package fr.cytech.projetjeespring.model.mappers;

import fr.cytech.projetjeespring.model.dtos.AbsenceFormDTO;
import fr.cytech.projetjeespring.model.entities.Absence;
import fr.cytech.projetjeespring.model.entities.Employee;
import fr.cytech.projetjeespring.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AbsenceMapper {

    private final EmployeeRepository employeeRepository;

    public AbsenceFormDTO toDto(Absence entity) {
        if (entity == null) return null;
        AbsenceFormDTO dto = new AbsenceFormDTO();
        dto.setId(entity.getId());
        dto.setEmployeeId(entity.getEmployee().getId());
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        dto.setReason(entity.getReason());
        dto.setExpected(entity.isExpected());
        dto.setPaidLeave(entity.isPaidLeave());
        return dto;
    }

    public Absence toEntity(AbsenceFormDTO dto) {
        Absence entity = new Absence();
        updateEntityFromDto(dto, entity);
        return entity;
    }

    public void updateEntityFromDto(AbsenceFormDTO dto, Absence entity) {
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setReason(dto.getReason());
        entity.setExpected(dto.isExpected());
        entity.setPaidLeave(dto.isPaidLeave());

        if (dto.getEmployeeId() != null) {
            Employee emp = employeeRepository.findById(dto.getEmployeeId()).orElse(null);
            entity.setEmployee(emp);
        }
    }
}