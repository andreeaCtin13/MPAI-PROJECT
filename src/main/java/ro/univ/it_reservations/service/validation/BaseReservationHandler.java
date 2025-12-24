package ro.univ.it_reservations.service.validation;

import ro.univ.it_reservations.entity.Reservation;

public abstract class BaseReservationHandler implements ReservationHandler {

    private ReservationHandler next;

    @Override
    public void setNext(ReservationHandler next) {
        this.next = next;
    }

    @Override
    public void handle(Reservation reservation) {
        doHandle(reservation);
        if (next != null) {
            next.handle(reservation);
        }
    }

    protected abstract void doHandle(Reservation reservation);
}
