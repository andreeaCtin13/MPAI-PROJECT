package ro.univ.it_reservations.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.univ.it_reservations.dto.CreateReservationRequest;
import ro.univ.it_reservations.dto.ReservationCreatedEvent;
import ro.univ.it_reservations.dto.ReservationStatusChangedEvent;
import ro.univ.it_reservations.entity.*;
import ro.univ.it_reservations.mapper.ReservationMapper;
import ro.univ.it_reservations.repository.EquipmentRepository;
import ro.univ.it_reservations.repository.ReservationRepository;
import ro.univ.it_reservations.service.validation.ConfirmReservationChain;
import ro.univ.it_reservations.service.validation.FinishReservationChain;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final EquipmentRepository equipmentRepository;
    private final ReservationMapper reservationMapper;
    private final ApplicationEventPublisher publisher;

    private final ConfirmReservationChain confirmReservationChain;
    private final FinishReservationChain finishReservationChain;

    public ReservationService(ReservationRepository reservationRepository,
                              EquipmentRepository equipmentRepository,
                              ReservationMapper reservationMapper,
                              ApplicationEventPublisher publisher,
                              ConfirmReservationChain confirmReservationChain,
                              FinishReservationChain finishReservationChain) {
        this.reservationRepository = reservationRepository;
        this.equipmentRepository = equipmentRepository;
        this.reservationMapper = reservationMapper;
        this.publisher = publisher;
        this.confirmReservationChain = confirmReservationChain;
        this.finishReservationChain = finishReservationChain;
    }

    @Transactional(readOnly = true)
    public List<Reservation> listAll() {
        return reservationRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Reservation getById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));
    }

    @Transactional
    public Reservation create(CreateReservationRequest req) {
        Equipment eq = equipmentRepository.findById(req.equipmentId)
                .orElseThrow(() -> new IllegalArgumentException("Equipment not found"));

        // verificare simplă: doar AVAILABLE
        if (eq.getStatus() != EquipmentStatus.AVAILABLE) {
            throw new IllegalStateException("Equipment not available.");
        }

        Reservation r = reservationMapper.toEntity(req, eq);
        Reservation saved = reservationRepository.save(r);

        publisher.publishEvent(new ReservationCreatedEvent(saved.getId()));
        return saved;
    }

    @Transactional
    public Reservation changeStatus(Long reservationId, ReservationStatus newStatus) {
        Reservation r = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));

        ReservationStatus old = r.getStatus();
        Equipment eq = r.getEquipment();

        // ✅ VALIDĂRI (Chain of Responsibility)
        if (newStatus == ReservationStatus.CONFIRMED) {
            // confirm doar din CREATED + (optional) equipment available
            confirmReservationChain.validate(r);
        }

        if (newStatus == ReservationStatus.FINISHED) {
            // finish doar din CONFIRMED
            finishReservationChain.validate(r);
        }

        // ✅ MUTĂRI DE STATUS
        r.setStatus(newStatus);

        if (newStatus == ReservationStatus.CONFIRMED) {
            // rezervarea ține echipamentul
            eq.setStatus(EquipmentStatus.RESERVED);
        }

        if (newStatus == ReservationStatus.CANCELLED) {
            // eliberează echipamentul
            eq.setStatus(EquipmentStatus.AVAILABLE);
        }

        if (newStatus == ReservationStatus.FINISHED) {
            // eliberează + update usage
            eq.setStatus(EquipmentStatus.AVAILABLE);
            eq.setUsageCount(eq.getUsageCount() + 1);
            eq.setLastUsedAt(LocalDateTime.now());
        }

        Reservation saved = reservationRepository.save(r);

        publisher.publishEvent(new ReservationStatusChangedEvent(saved.getId(), old, newStatus));
        return saved;
    }
}
