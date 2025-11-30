package fr.cytech.projetjeespring.services;

import fr.cytech.projetjeespring.model.entities.Employee;
import fr.cytech.projetjeespring.repositories.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    public EmployeeRepository repository() { return this.employeeRepository; }

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
        return employeeRepository.save(employee);
    }

    public void delete(Integer id) {
        employeeRepository.deleteById(id);
    }

    public List<Employee> search(String keyword, Integer deptId) {
        return employeeRepository.search(keyword, deptId);
    }

    @Transactional
    public void changePassword(Integer employeeId, String oldPassword, String newPassword) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        if (!passwordEncoder.matches(oldPassword, employee.getPassword())) {
            throw new BadCredentialsException("Incorrect current password");
        }

        employee.setPassword(passwordEncoder.encode(newPassword));
        employeeRepository.save(employee);
    }
}