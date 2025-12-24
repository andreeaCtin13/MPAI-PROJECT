package ro.univ.it_reservations.dto;

import ro.univ.it_reservations.entity.EquipmentStatus;
import ro.univ.it_reservations.entity.EquipmentType;

public class EquipmentRequest {
    public String name;
    public EquipmentType type;
    public EquipmentStatus status; // SINGURA variabilă de stare
}
