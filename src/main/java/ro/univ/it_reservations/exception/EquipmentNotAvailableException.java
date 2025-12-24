package ro.univ.it_reservations.exception;

public class EquipmentNotAvailableException extends RuntimeException {
    public EquipmentNotAvailableException(Long equipmentId) {
        super("Equipment " + equipmentId + " is not available.");
    }

    public EquipmentNotAvailableException(String message) {
        super(message);
    }
}
