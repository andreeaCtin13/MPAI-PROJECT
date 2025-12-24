package ro.univ.it_reservations.service.validation;

import org.springframework.stereotype.Component;
import ro.univ.it_reservations.entity.Reservation;
import ro.univ.it_reservations.entity.ReservationStatus;

@Component
public class FinishStatusHandler extends BaseReservationHandler {

    @Override
    protected void doHandle(Reservation reservation) {
        if (reservation.getStatus() != ReservationStatus.CONFIRMED) {
            throw new IllegalStateException("Reservation must be CONFIRMED to finish.");
        }
    }
}
