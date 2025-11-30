package fr.cytech.projetjeespring.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    private String description;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinTable(
            name = "department_heads",
            joinColumns = @JoinColumn(name = "department_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    @ToString.Exclude
    private Employee head;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Employee> employees = new ArrayList<>();

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Project> projects = new ArrayList<>();
}