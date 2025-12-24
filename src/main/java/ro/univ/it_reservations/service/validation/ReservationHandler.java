package ro.univ.it_reservations.service.validation;

import ro.univ.it_reservations.entity.Reservation;

public interface ReservationHandler {
    void setNext(ReservationHandler next);
    void handle(Reservation reservation);
}
