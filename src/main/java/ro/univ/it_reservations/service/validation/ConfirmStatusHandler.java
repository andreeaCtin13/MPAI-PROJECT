package ro.univ.it_reservations.service.validation;

import org.springframework.stereotype.Component;
import ro.univ.it_reservations.entity.Reservation;
import ro.univ.it_reservations.entity.ReservationStatus;

@Component
public class ConfirmStatusHandler extends BaseReservationHandler {

    @Override
    protected void doHandle(Reservation reservation) {
        if (reservation.getStatus() == ReservationStatus.CANCELLED) {
            throw new IllegalStateException("Cannot confirm a CANCELLED reservation.");
        }
        if (reservation.getStatus() == ReservationStatus.FINISHED) {
            throw new IllegalStateException("Cannot confirm a FINISHED reservation.");
        }
        if (reservation.getStatus() != ReservationStatus.CREATED) {
            throw new IllegalStateException("Reservation must be CREATED to confirm.");
        }
    }
}
