package ro.univ.it_reservations.mapper;

import org.springframework.stereotype.Component;
import ro.univ.it_reservations.dto.CreateReservationRequest;
import ro.univ.it_reservations.dto.ReservationResponse;
import ro.univ.it_reservations.entity.Equipment;
import ro.univ.it_reservations.entity.Reservation;

@Component
public class ReservationMapper {

    public Reservation toEntity(CreateReservationRequest req, Equipment equipment) {
        Reservation r = new Reservation();
        r.setUserEmail(req.userEmail);
        r.setEquipment(equipment);
        r.setStartAt(req.startAt);
        r.setEndAt(req.endAt);
        return r;
    }

    public ReservationResponse toResponse(Reservation r) {
        ReservationResponse resp = new ReservationResponse();
        resp.id = r.getId();
        resp.userEmail = r.getUserEmail();
        resp.equipmentId = r.getEquipment().getId();
        resp.startAt = r.getStartAt();
        resp.endAt = r.getEndAt();
        resp.status = r.getStatus();
        return resp;
    }
}
