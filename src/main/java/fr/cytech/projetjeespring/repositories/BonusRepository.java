package fr.cytech.projetjeespring.repositories;

import fr.cytech.projetjeespring.entities.Bonus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BonusRepository extends JpaRepository<Bonus, Integer> {}