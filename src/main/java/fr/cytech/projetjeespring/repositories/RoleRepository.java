package fr.cytech.projetjeespring.repositories;

import fr.cytech.projetjeespring.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
}
