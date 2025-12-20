package ro.univ.it_reservations.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "equipments")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private EquipmentType type;

    private boolean available = true;

    public Equipment() {}

    public Equipment(Long id, String name, EquipmentType type, boolean available) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.available = available;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public EquipmentType getType() { return type; }
    public boolean isAvailable() { return available; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setType(EquipmentType type) { this.type = type; }
    public void setAvailable(boolean available) { this.available = available; }
}
