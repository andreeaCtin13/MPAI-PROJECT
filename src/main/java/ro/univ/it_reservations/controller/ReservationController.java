package ro.univ.it_reservations.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.univ.it_reservations.dto.CreateReservationRequest;
import ro.univ.it_reservations.dto.ReservationResponse;
import ro.univ.it_reservations.entity.ReservationStatus;
import ro.univ.it_reservations.mapper.ReservationMapper;
import ro.univ.it_reservations.service.ReservationService;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final ReservationMapper reservationMapper;

    public ReservationController(ReservationService reservationService,
                                 ReservationMapper reservationMapper) {
        this.reservationService = reservationService;
        this.reservationMapper = reservationMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationResponse create(@RequestBody CreateReservationRequest req) {
        return reservationMapper.toResponse(reservationService.create(req));
    }

    // ✅ GET /reservations
    @GetMapping
    public List<ReservationResponse> list() {
        return reservationService.listAll()
                .stream()
                .map(reservationMapper::toResponse)
                .toList();
    }

    // ✅ GET /reservations/{id}
    @GetMapping("/{id}")
    public ReservationResponse getById(@PathVariable Long id) {
        return reservationMapper.toResponse(reservationService.getById(id));
    }

    // confirm (ex: CREATED -> CONFIRMED)
    @PostMapping("/{id}/confirm")
    public ReservationResponse confirm(@PathVariable Long id) {
        return reservationMapper.toResponse(
                reservationService.changeStatus(id, ReservationStatus.CONFIRMED)
        );
    }

    // cancel (eliberează echipamentul)
    @PostMapping("/{id}/cancel")
    public ReservationResponse cancel(@PathVariable Long id) {
        return reservationMapper.toResponse(
                reservationService.changeStatus(id, ReservationStatus.CANCELLED)
        );
    }

    // finish (eliberează echipamentul + de obicei crești usage_count)
    @PostMapping("/{id}/finish")
    public ReservationResponse finish(@PathVariable Long id) {
        return reservationMapper.toResponse(
                reservationService.changeStatus(id, ReservationStatus.FINISHED)
        );
    }
}
