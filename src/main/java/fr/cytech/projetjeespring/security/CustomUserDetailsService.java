package fr.cytech.projetjeespring.security;

import fr.cytech.projetjeespring.model.entities.Employee;
import fr.cytech.projetjeespring.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Integer employeeId = null;
        try {
            employeeId = Integer.parseInt(username);
        } catch (NumberFormatException e) {
            throw new UsernameNotFoundException("Username must be an Employee ID");
        }

        Employee emp = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new UsernameNotFoundException("Employee not found"));

        return new CustomUserDetails(
                String.valueOf(emp.getId()),
                emp.getPassword(),
                emp.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toList()),
                emp.getFirstName(),
                emp.getLastName()
        );
    }
}