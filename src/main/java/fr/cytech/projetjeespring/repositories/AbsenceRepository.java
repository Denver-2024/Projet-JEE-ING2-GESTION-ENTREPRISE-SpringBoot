package fr.cytech.projetjeespring.repositories;

import fr.cytech.projetjeespring.model.entities.Absence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Integer> {
    List<Absence> findByEmployeeIdOrderByStartDateDesc(Integer employeeId);
}