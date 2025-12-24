package ro.univ.it_reservations.service.validation;

import org.springframework.stereotype.Component;
import ro.univ.it_reservations.entity.Reservation;

@Component
public class FinishReservationChain {

    private final FinishStatusHandler finishStatusHandler;

    public FinishReservationChain(FinishStatusHandler finishStatusHandler) {
        this.finishStatusHandler = finishStatusHandler;
    }

    public void validate(Reservation reservation) {
        finishStatusHandler.handle(reservation);
    }
}
