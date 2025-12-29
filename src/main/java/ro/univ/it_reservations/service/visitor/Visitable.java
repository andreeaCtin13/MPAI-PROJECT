package ro.univ.it_reservations.service.visitor;

public interface Visitable {
    void accept(ReportVisitor visitor);
}