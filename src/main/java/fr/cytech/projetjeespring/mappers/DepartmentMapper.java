package fr.cytech.projetjeespring.mappers;

import fr.cytech.projetjeespring.dtos.DepartmentFormDTO;
import fr.cytech.projetjeespring.dtos.DepartmentSummaryDTO;
import fr.cytech.projetjeespring.entities.Department;
import fr.cytech.projetjeespring.entities.Employee;
import fr.cytech.projetjeespring.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

    public DepartmentSummaryDTO toSummaryDto(Department entity) {
        if (entity == null) return null;

        DepartmentSummaryDTO dto = new DepartmentSummaryDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());

        if (entity.getHead() != null) {
            dto.setHeadName(entity.getHead().getFirstName() + " " + entity.getHead().getLastName());
            dto.setHeadEmail(entity.getHead().getEmail());
        } else {
            dto.setHeadName("VACANT");
        }
        return dto;
    }

    public List<DepartmentSummaryDTO> toSummaryDtoList(List<Department> entities) {
        if (entities == null) return List.of();
        return entities.stream().map(this::toSummaryDto).collect(Collectors.toList());
    }
}