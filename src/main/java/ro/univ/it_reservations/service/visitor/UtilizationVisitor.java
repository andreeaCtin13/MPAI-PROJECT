package ro.univ.it_reservations.service.visitor;

import ro.univ.it_reservations.dto.report.UtilizationReportResponse;
import ro.univ.it_reservations.entity.Equipment;
import ro.univ.it_reservations.entity.Reservation;
import java.util.HashMap;
import java.util.Map;

public class UtilizationVisitor implements ReportVisitor {

    private final Map<String, Integer> stats = new HashMap<>();

    @Override
    public void visit(Equipment equipment) {
        String type = equipment.getType().name();
        stats.put(type, stats.getOrDefault(type, 0) + 1);
    }

    @Override
    public void visit(Reservation reservation) {
    }

    public UtilizationReportResponse getResult() {
        return new UtilizationReportResponse(stats);
    }
}