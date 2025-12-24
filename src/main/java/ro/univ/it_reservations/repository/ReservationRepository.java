package ro.univ.it_reservations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.univ.it_reservations.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
