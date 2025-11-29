package fr.cytech.projetjeespring.services;

import fr.cytech.projetjeespring.entities.Department;
import fr.cytech.projetjeespring.entities.Employee;
import fr.cytech.projetjeespring.repositories.DepartmentRepository;
import fr.cytech.projetjeespring.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    public Department findById(Integer id) {
        return departmentRepository.findById(id).orElse(null);
    }

    public List<Department> findByExample(Department probe) {
        return departmentRepository.findAll(Example.of(probe));
    }

    // Petit check pour voir si le chef existe
    public Department createDepartment(Department department, Integer headEmployeeId) {
        Employee head = employeeRepository.findById(headEmployeeId)
                .orElseThrow(() -> new RuntimeException("Head Employee not found"));

        department.setHead(head);
        Department savedDept = departmentRepository.save(department);

        head.setDepartment(savedDept);
        employeeRepository.save(head);

        return savedDept;
    }

    public void delete(Integer id) {
        departmentRepository.deleteById(id);
    }
}