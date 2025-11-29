package fr.cytech.projetjeespring.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "bonuses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bonus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    @ToString.Exclude
    private Employee employee;

    private String reason;

    @Column(nullable = false)
    private Float amount;

    @Column(name = "award_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate awardDate;

    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    @PrePersist
    @PreUpdate
    public void updateTimestamp() {
        this.lastUpdate = LocalDateTime.now();
    }
}