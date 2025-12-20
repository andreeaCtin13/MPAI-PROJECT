package ro.univ.it_reservations.mapper;

import org.springframework.stereotype.Component;
import ro.univ.it_reservations.dto.EquipmentRequest;
import ro.univ.it_reservations.dto.EquipmentResponse;
import ro.univ.it_reservations.entity.Equipment;

@Component
public class EquipmentMapper {

    public EquipmentResponse toResponse(Equipment e) {
        EquipmentResponse r = new EquipmentResponse();
        r.id = e.getId();
        r.name = e.getName();
        r.type = e.getType();
        r.available = e.isAvailable();
        return r;
    }

    public Equipment toEntityForCreate(EquipmentRequest req) {
        Equipment e = new Equipment();
        e.setName(req.name);
        e.setType(req.type);
        e.setAvailable(req.available == null ? true : req.available);
        return e;
    }

    public void updateEntity(Equipment e, EquipmentRequest req) {
        if (req.name != null) e.setName(req.name);
        if (req.type != null) e.setType(req.type);
        if (req.available != null) e.setAvailable(req.available);
    }
}
