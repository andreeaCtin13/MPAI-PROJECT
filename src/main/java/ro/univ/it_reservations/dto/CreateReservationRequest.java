package ro.univ.it_reservations.dto;

import java.time.LocalDateTime;

public class CreateReservationRequest {
    public String userEmail;
    public Long equipmentId;
    public LocalDateTime startAt;
    public LocalDateTime endAt;
}
