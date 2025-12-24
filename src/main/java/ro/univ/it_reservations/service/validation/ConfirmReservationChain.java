package ro.univ.it_reservations.service.validation;

import org.springframework.stereotype.Component;

@Component
public class ConfirmReservationChain {

    private final ConfirmStatusHandler confirmStatusHandler;
    private final EquipmentAvailableHandler equipmentAvailableHandler;

    public ConfirmReservationChain(
            ConfirmStatusHandler confirmStatusHandler,
            EquipmentAvailableHandler equipmentAvailableHandler
    ) {
        this.confirmStatusHandler = confirmStatusHandler;
        this.equipmentAvailableHandler = equipmentAvailableHandler;

        // ordinea lanțului:
        this.confirmStatusHandler.setNext(this.equipmentAvailableHandler);
    }

    public void validate(ro.univ.it_reservations.entity.Reservation reservation) {
        confirmStatusHandler.handle(reservation);
    }
}
