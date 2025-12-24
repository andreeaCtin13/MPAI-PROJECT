package ro.univ.it_reservations.service;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import ro.univ.it_reservations.dto.ReservationStatusChangedEvent;
import ro.univ.it_reservations.entity.Equipment;
import ro.univ.it_reservations.entity.Reservation;
import ro.univ.it_reservations.entity.ReservationStatus;
import ro.univ.it_reservations.repository.EquipmentRepository;
import ro.univ.it_reservations.repository.ReservationRepository;

import java.time.LocalDateTime;

@Service
public class EquipmentUsageListener {

    private final ReservationRepository reservationRepository;
    private final EquipmentRepository equipmentRepository;

    public EquipmentUsageListener(ReservationRepository reservationRepository,
                                  EquipmentRepository equipmentRepository) {
        this.reservationRepository = reservationRepository;
        this.equipmentRepository = equipmentRepository;
    }

    @EventListener
    public void onStatusChanged(ReservationStatusChangedEvent e) {
        if (e.newStatus() != ReservationStatus.FINISHED) return;

        Reservation r = reservationRepository.findById(e.reservationId()).orElse(null);
        if (r == null) return;

        Equipment eq = r.getEquipment();
        eq.setUsageCount(eq.getUsageCount() + 1);
        eq.setLastUsedAt(LocalDateTime.now());
        equipmentRepository.save(eq);
    }
}
