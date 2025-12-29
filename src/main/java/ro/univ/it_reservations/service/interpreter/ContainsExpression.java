package ro.univ.it_reservations.service.interpreter;

import ro.univ.it_reservations.entity.Equipment;

public class ContainsExpression implements Expression {
    private String field;
    private String value;

    public ContainsExpression(String field, String value) {
        this.field = field;
        this.value = value;
    }

    @Override
    public boolean interpret(Equipment equipment) {
        if ("name".equalsIgnoreCase(field)) {
            return equipment.getName().toLowerCase().contains(value.toLowerCase());
        }
        return false;
    }
}