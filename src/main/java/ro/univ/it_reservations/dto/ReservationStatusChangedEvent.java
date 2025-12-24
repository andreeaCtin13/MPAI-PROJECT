package ro.univ.it_reservations.dto;

import ro.univ.it_reservations.entity.ReservationStatus;

public record ReservationStatusChangedEvent(
        Long reservationId,
        ReservationStatus oldStatus,
        ReservationStatus newStatus
) {}
