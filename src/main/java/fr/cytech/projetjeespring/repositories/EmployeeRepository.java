package fr.cytech.projetjeespring.repositories;

import fr.cytech.projetjeespring.model.entities.Department;
import fr.cytech.projetjeespring.model.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByEmail(String email);

    List<Employee> findByDepartment(Department department);

    @Query("SELECT e FROM Employee e WHERE " +
            "(:keyword IS NULL OR :keyword = '' OR " +
            "LOWER(e.firstName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(e.lastName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(e.email) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
            "AND (:deptId IS NULL OR e.department.id = :deptId)")
    List<Employee> search(@Param("keyword") String keyword, @Param("deptId") Integer deptId);
}