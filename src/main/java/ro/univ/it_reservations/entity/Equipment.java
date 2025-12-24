package ro.univ.it_reservations.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "equipments")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private EquipmentType type;

    @Enumerated(EnumType.STRING)
    private EquipmentStatus status = EquipmentStatus.AVAILABLE;

    private long usageCount = 0;

    private LocalDateTime lastUsedAt;

    public Equipment() {}

    public Long getId() { return id; }
    public String getName() { return name; }
    public EquipmentType getType() { return type; }
    public EquipmentStatus getStatus() { return status; }
    public long getUsageCount() { return usageCount; }
    public LocalDateTime getLastUsedAt() { return lastUsedAt; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setType(EquipmentType type) { this.type = type; }
    public void setStatus(EquipmentStatus status) { this.status = status; }
    public void setUsageCount(long usageCount) { this.usageCount = usageCount; }
    public void setLastUsedAt(LocalDateTime lastUsedAt) { this.lastUsedAt = lastUsedAt; }
}
