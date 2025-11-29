package fr.cytech.projetjeespring.services;

import fr.cytech.projetjeespring.entities.Department;
import fr.cytech.projetjeespring.entities.Employee;
import fr.cytech.projetjeespring.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findById(Integer id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public List<Employee> findByExample(Employee probe) {
        return employeeRepository.findAll(Example.of(probe));
    }

    public Employee save(Employee employee) {
        // TODO: Hasher le mot de passe
        // employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        return employeeRepository.save(employee);
    }

    public void delete(Integer id) {
        employeeRepository.deleteById(id);
    }
}