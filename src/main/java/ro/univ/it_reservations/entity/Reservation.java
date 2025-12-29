package ro.univ.it_reservations.entity;

import jakarta.persistence.*;
import ro.univ.it_reservations.service.visitor.ReportVisitor;
import ro.univ.it_reservations.service.visitor.Visitable;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
public class Reservation implements Visitable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userEmail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_id", nullable = false)
    private Equipment equipment;

    private LocalDateTime startAt;
    private LocalDateTime endAt;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status = ReservationStatus.CREATED;

    private LocalDateTime createdAt = LocalDateTime.now();

    public Reservation() {}

    public Long getId() { return id; }
    public String getUserEmail() { return userEmail; }
    public Equipment getEquipment() { return equipment; }
    public LocalDateTime getStartAt() { return startAt; }
    public LocalDateTime getEndAt() { return endAt; }
    public ReservationStatus getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setId(Long id) { this.id = id; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }
    public void setEquipment(Equipment equipment) { this.equipment = equipment; }
    public void setStartAt(LocalDateTime startAt) { this.startAt = startAt; }
    public void setEndAt(LocalDateTime endAt) { this.endAt = endAt; }
    public void setStatus(ReservationStatus status) { this.status = status; }

    @Override
    public void accept(ReportVisitor visitor) {
        visitor.visit(this);
    }
}
