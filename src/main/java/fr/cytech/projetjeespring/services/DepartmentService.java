package fr.cytech.projetjeespring.services;

import fr.cytech.projetjeespring.entities.Department;
import fr.cytech.projetjeespring.entities.Employee;
import fr.cytech.projetjeespring.repositories.DepartmentRepository;
import fr.cytech.projetjeespring.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    public List<Department> findByExample(Department probe) {
        return departmentRepository.findAll(Example.of(probe));
    }

    public Department findById(Integer id) {
        return departmentRepository.findById(id).orElse(null);
    }

    public Department findByHeadId(Integer id) {
        return departmentRepository.findByHeadId(id);
    }

    @Transactional
    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    @Transactional
    public void delete(Integer id) {
        departmentRepository.deleteById(id);
    }
}