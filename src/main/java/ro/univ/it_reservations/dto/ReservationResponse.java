package ro.univ.it_reservations.dto;

import ro.univ.it_reservations.entity.ReservationStatus;
import java.time.LocalDateTime;

public class ReservationResponse {
    public Long id;
    public String userEmail;
    public Long equipmentId;
    public LocalDateTime startAt;
    public LocalDateTime endAt;
    public ReservationStatus status;
}
