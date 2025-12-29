package ro.univ.it_reservations.service.interpreter;

import ro.univ.it_reservations.entity.Equipment;

public class EqualsExpression implements Expression {
    private String field;
    private String value;

    public EqualsExpression(String field, String value) {
        this.field = field;
        this.value = value;
    }

    @Override
    public boolean interpret(Equipment equipment) {
        if ("type".equalsIgnoreCase(field)) {
            return equipment.getType().name().equalsIgnoreCase(value);
        }
        if ("status".equalsIgnoreCase(field)) {
            return equipment.getStatus().name().equalsIgnoreCase(value);
        }
        return false;
    }
}