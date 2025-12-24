package ro.univ.it_reservations.service;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import ro.univ.it_reservations.dto.ReservationCreatedEvent;
import ro.univ.it_reservations.dto.ReservationStatusChangedEvent;
import ro.univ.it_reservations.entity.AuditLog;
import ro.univ.it_reservations.entity.Reservation;
import ro.univ.it_reservations.repository.AuditLogRepository;
import ro.univ.it_reservations.repository.ReservationRepository;

@Service
public class AuditLogListener {

    private final AuditLogRepository auditLogRepository;
    private final ReservationRepository reservationRepository;

    public AuditLogListener(AuditLogRepository auditLogRepository,
                            ReservationRepository reservationRepository) {
        this.auditLogRepository = auditLogRepository;
        this.reservationRepository = reservationRepository;
    }

    @EventListener
    public void onCreated(ReservationCreatedEvent e) {
        Reservation r = reservationRepository.findById(e.reservationId()).orElse(null);
        if (r == null) return;

        auditLogRepository.save(new AuditLog(
                "ReservationCreated",
                r.getId(),
                r.getEquipment().getId(),
                "Created for " + r.getUserEmail()
        ));
    }

    @EventListener
    public void onStatusChanged(ReservationStatusChangedEvent e) {
        Reservation r = reservationRepository.findById(e.reservationId()).orElse(null);
        if (r == null) return;

        auditLogRepository.save(new AuditLog(
                "ReservationStatusChanged",
                r.getId(),
                r.getEquipment().getId(),
                "Status: " + e.oldStatus() + " -> " + e.newStatus()
        ));
    }
}
