package ro.univ.it_reservations.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "audit_logs")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventType;
    private Long reservationId;
    private Long equipmentId;
    private String message;

    private LocalDateTime createdAt = LocalDateTime.now();

    public AuditLog() {}

    public AuditLog(String eventType, Long reservationId, Long equipmentId, String message) {
        this.eventType = eventType;
        this.reservationId = reservationId;
        this.equipmentId = equipmentId;
        this.message = message;
    }

    public Long getId() { return id; }
    public String getEventType() { return eventType; }
    public Long getReservationId() { return reservationId; }
    public Long getEquipmentId() { return equipmentId; }
    public String getMessage() { return message; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setId(Long id) { this.id = id; }
    public void setEventType(String eventType) { this.eventType = eventType; }
    public void setReservationId(Long reservationId) { this.reservationId = reservationId; }
    public void setEquipmentId(Long equipmentId) { this.equipmentId = equipmentId; }
    public void setMessage(String message) { this.message = message; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
