package ro.univ.it_reservations.service.observer.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import ro.univ.it_reservations.dto.ReservationCreatedEvent;
import ro.univ.it_reservations.dto.ReservationStatusChangedEvent;

@Service
public class EmailNotificationListener {

    @EventListener
    public void onCreated(ReservationCreatedEvent e) {
        System.out.println("[EMAIL] Reservation created: " + e.reservationId());
    }

    @EventListener
    public void onStatusChanged(ReservationStatusChangedEvent e) {
        System.out.println("[EMAIL] Reservation status changed: " + e.reservationId()
                + " " + e.oldStatus() + " -> " + e.newStatus());
    }
}

