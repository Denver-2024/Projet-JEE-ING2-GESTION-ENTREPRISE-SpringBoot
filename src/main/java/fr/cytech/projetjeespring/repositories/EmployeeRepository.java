package fr.cytech.projetjeespring.repositories;

import fr.cytech.projetjeespring.entities.Department;
import fr.cytech.projetjeespring.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByEmail(String email);

    List<Employee> findByDepartment(Department department);
}