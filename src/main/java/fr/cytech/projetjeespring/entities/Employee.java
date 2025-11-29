package fr.cytech.projetjeespring.entities;

import fr.cytech.projetjeespring.enums.EmployeeType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    private String address;

    @Column(nullable = false, unique = true)
    private String email;

    private String gender;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmployeeType type;

    @Column(nullable = false)
    private Float salary;

    @Column(nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    // Sinon on a une FAT récurison infinie, woups!
    @Override
    public String toString() {
        return "Employee{id=" + id + ", email='" + email + "'}";
    }
}