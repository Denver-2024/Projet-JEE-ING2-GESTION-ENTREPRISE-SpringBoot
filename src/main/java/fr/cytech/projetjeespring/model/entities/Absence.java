package fr.cytech.projetjeespring.model.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "absences")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Absence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    @ToString.Exclude
    private Employee employee;

    @Column(name = "start_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @Column(name = "end_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    private String reason;

    @Column(nullable = false)
    private boolean expected;

    @Column(name = "is_paid_leave", nullable = false)
    private boolean paidLeave;

    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    @PrePersist
    @PreUpdate
    public void updateTimestamp() {
        this.lastUpdate = LocalDateTime.now();
    }
}