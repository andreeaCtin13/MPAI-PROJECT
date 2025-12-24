package ro.univ.it_reservations.service.validation;

import org.springframework.stereotype.Component;
import ro.univ.it_reservations.entity.Equipment;
import ro.univ.it_reservations.entity.EquipmentStatus;
import ro.univ.it_reservations.entity.Reservation;
import ro.univ.it_reservations.exception.EquipmentNotAvailableException;

@Component
public class EquipmentAvailableHandler extends BaseReservationHandler {

    @Override
    protected void doHandle(Reservation reservation) {
        Equipment eq = reservation.getEquipment();
        if (eq == null) {
            throw new IllegalStateException("Reservation has no equipment.");
        }

        // strict pe status
        if (eq.getStatus() != EquipmentStatus.AVAILABLE) {
            throw new EquipmentNotAvailableException(eq.getId());
        }
    }
}
