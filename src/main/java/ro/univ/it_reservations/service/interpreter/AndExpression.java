package ro.univ.it_reservations.service.interpreter;

import ro.univ.it_reservations.entity.Equipment;

public class AndExpression implements Expression {
    private Expression expr1;
    private Expression expr2;

    public AndExpression(Expression expr1, Expression expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    @Override
    public boolean interpret(Equipment equipment) {
        return expr1.interpret(equipment) && expr2.interpret(equipment);
    }
}