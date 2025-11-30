package fr.cytech.projetjeespring.repositories;

import fr.cytech.projetjeespring.model.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    Department findByHeadId(Integer headId);
}