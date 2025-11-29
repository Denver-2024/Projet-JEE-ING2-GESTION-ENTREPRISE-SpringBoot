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
    @Setter
    private Integer id;

    @Column(name = "first_name", nullable = false)
    @Getter
    @Setter
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private String address;

    @Column(nullable = false, unique = true)
    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String gender;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Getter
    @Setter
    private EmployeeType type;

    @Column(nullable = false)
    @Getter
    @Setter
    private Float salary;

    @Column(nullable = false)
    @Getter
    @Setter
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    @Getter
    @Setter
    private Department department;

    // Sinon on a une FAT récurison infinie, woups!
    @Override
    public String toString() {
        return "Employee{id=" + id + ", email='" + email + "'}";
    }
}