package ro.univ.it_reservations.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.univ.it_reservations.dto.report.UserActivityReportResponse;
import ro.univ.it_reservations.dto.report.UtilizationReportResponse;
import ro.univ.it_reservations.entity.Equipment;
import ro.univ.it_reservations.entity.Reservation;
import ro.univ.it_reservations.repository.EquipmentRepository;
import ro.univ.it_reservations.repository.ReservationRepository;
import ro.univ.it_reservations.service.visitor.UserActivityVisitor;
import ro.univ.it_reservations.service.visitor.UtilizationVisitor;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final EquipmentRepository equipmentRepository;
    private final ReservationRepository reservationRepository;

    public ReportController(EquipmentRepository equipmentRepository,
                            ReservationRepository reservationRepository) {
        this.equipmentRepository = equipmentRepository;
        this.reservationRepository = reservationRepository;
    }

    @GetMapping("/utilization")
    public UtilizationReportResponse getUtilizationReport() {
        List<Equipment> allEquipment = equipmentRepository.findAll();

        UtilizationVisitor visitor = new UtilizationVisitor();

        for (Equipment eq : allEquipment) {
            eq.accept(visitor);
        }

        return visitor.getResult();
    }

    @GetMapping("/user-activity")
    public UserActivityReportResponse getUserActivityReport() {
        List<Reservation> allReservations = reservationRepository.findAll();
        UserActivityVisitor visitor = new UserActivityVisitor();

        for (Reservation res : allReservations) {
            res.accept(visitor);
        }

        return visitor.getResult();
    }
}