package fr.cytech.projetjeespring.repositories;

import fr.cytech.projetjeespring.entities.Absence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Integer> {}