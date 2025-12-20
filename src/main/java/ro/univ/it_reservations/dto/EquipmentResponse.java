package ro.univ.it_reservations.dto;

import ro.univ.it_reservations.entity.EquipmentType;

public class EquipmentResponse {
    public Long id;
    public String name;
    public EquipmentType type;
    public boolean available;
}
