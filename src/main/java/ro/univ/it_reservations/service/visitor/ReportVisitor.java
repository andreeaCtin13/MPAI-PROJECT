package ro.univ.it_reservations.service.visitor;

import ro.univ.it_reservations.entity.Equipment;
import ro.univ.it_reservations.entity.Reservation;

public interface ReportVisitor {
    void visit(Equipment equipment);
    void visit(Reservation reservation);
}