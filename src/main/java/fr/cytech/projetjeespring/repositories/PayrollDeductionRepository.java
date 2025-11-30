package fr.cytech.projetjeespring.repositories;

import fr.cytech.projetjeespring.model.entities.PayrollDeduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayrollDeductionRepository extends JpaRepository<PayrollDeduction, Integer> {}