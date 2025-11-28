package fr.cytech.projetjeespring.entities;

import fr.cytech.projetjeespring.enums.EmployeeType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Integer id;

    @Column(name = "first_name", nullable = false)
    @Getter
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @Getter
    private String lastName;

    @Getter
    private String address;

    @Column(nullable = false, unique = true)
    @Getter
    private String email;

    @Getter
    private String gender;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Getter
    private EmployeeType type;

    @Column(nullable = false)
    @Getter
    private Float salary;

    @Column(nullable = false)
    @Getter
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    @Getter
    private Department department;

    // Sinon on a une FAT récurison infinie, woups!
    @Override
    public String toString() {
        return "Employee{id=" + id + ", email='" + email + "'}";
    }
}