package fr.cytech.projetjeespring.mappers;

import fr.cytech.projetjeespring.dtos.EmployeeFormDTO;
import fr.cytech.projetjeespring.dtos.EmployeeSummaryDTO;
import fr.cytech.projetjeespring.entities.Department;
import fr.cytech.projetjeespring.entities.Employee;
import fr.cytech.projetjeespring.repositories.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EmployeeMapper {

    private final DepartmentRepository departmentRepository;

    public EmployeeFormDTO toDto(Employee entity) {
        if (entity == null) return null;

        EmployeeFormDTO dto = new EmployeeFormDTO();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setAddress(entity.getAddress());
        dto.setGender(entity.getGender());
        dto.setType(entity.getType());
        dto.setSalary(entity.getSalary());

        if (entity.getDepartment() != null) {
            dto.setDepartmentId(entity.getDepartment().getId());
        }
        return dto;
    }

    public Employee toEntity(EmployeeFormDTO dto) {
        if (dto == null) return null;

        Employee entity = new Employee();
        updateEntityFromDto(dto, entity);
        return entity;
    }

    public void updateEntityFromDto(EmployeeFormDTO dto, Employee entity) {
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setAddress(dto.getAddress());
        entity.setGender(dto.getGender());
        entity.setType(dto.getType());
        entity.setSalary(dto.getSalary());

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            entity.setPassword(dto.getPassword());
        }

        if (dto.getDepartmentId() != null) {
            Department dept = departmentRepository.findById(dto.getDepartmentId())
                    .orElse(null);
            entity.setDepartment(dept);
        } else {
            entity.setDepartment(null);
        }
    }

    public EmployeeSummaryDTO toSummaryDto(Employee entity) {
        if (entity == null) return null;

        EmployeeSummaryDTO dto = new EmployeeSummaryDTO();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setType(entity.getType() != null ? entity.getType().name() : "");

        if (entity.getDepartment() != null) {
            dto.setDepartmentName(entity.getDepartment().getName());
        } else {
            dto.setDepartmentName("Unassigned");
        }
        return dto;
    }

    public List<EmployeeSummaryDTO> toSummaryDtoList(List<Employee> entities) {
        if (entities == null) return List.of();
        return entities.stream()
                .map(this::toSummaryDto)
                .collect(Collectors.toList());
    }
}