package fr.cytech.projetjeespring.entities;

import fr.cytech.projetjeespring.enums.ProjectState;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;

    @Column(nullable = false)
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Getter
    @Setter
    private ProjectState state;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    @Getter
    @Setter
    private Department department;
}