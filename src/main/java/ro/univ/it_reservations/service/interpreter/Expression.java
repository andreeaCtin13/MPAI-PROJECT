package ro.univ.it_reservations.service.interpreter;

import ro.univ.it_reservations.entity.Equipment;

public interface Expression {
    boolean interpret(Equipment equipment);
}