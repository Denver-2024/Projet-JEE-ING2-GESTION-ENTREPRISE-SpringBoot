package fr.cytech.projetjeespring.mappers;

import fr.cytech.projetjeespring.dtos.DepartmentFormDTO;
import fr.cytech.projetjeespring.entities.Department;
import fr.cytech.projetjeespring.entities.Employee;
import fr.cytech.projetjeespring.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DepartmentMapper {

    private final EmployeeRepository employeeRepository;

    public DepartmentFormDTO toDto(Department entity) {
        if (entity == null) return null;

        DepartmentFormDTO dto = new DepartmentFormDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());

        if (entity.getHead() != null) {
            dto.setHeadEmployeeId(entity.getHead().getId());
        }
        return dto;
    }

    public Department toEntity(DepartmentFormDTO dto) {
        Department entity = new Department();
        updateEntityFromDto(dto, entity);
        return entity;
    }

    public void updateEntityFromDto(DepartmentFormDTO dto, Department entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());

        if (dto.getHeadEmployeeId() != null) {
            Employee head = employeeRepository.findById(dto.getHeadEmployeeId()).orElse(null);
            entity.setHead(head);
        } else {
            entity.setHead(null);
        }
    }
}