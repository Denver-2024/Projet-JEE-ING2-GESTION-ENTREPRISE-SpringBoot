package fr.cytech.projetjeespring.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Integer id;

    @Column(nullable = false)
    @Getter
    private String name;

    @Getter
    private String description;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "head_employee_id", nullable = false)
    @Getter
    @Setter
    private Employee head;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    @Getter
    private List<Employee> employees = new ArrayList<>();

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    @Getter
    private List<Project> projects = new ArrayList<>();
}