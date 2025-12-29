package ro.univ.it_reservations.service.visitor;

import ro.univ.it_reservations.dto.report.UserActivityReportResponse;
import ro.univ.it_reservations.entity.Equipment;
import ro.univ.it_reservations.entity.Reservation;
import java.util.HashMap;
import java.util.Map;

public class UserActivityVisitor implements ReportVisitor {

    private final Map<String, Integer> userStats = new HashMap<>();

    @Override
    public void visit(Equipment equipment) {
    }

    @Override
    public void visit(Reservation reservation) {
        String email = reservation.getUserEmail();
        userStats.put(email, userStats.getOrDefault(email, 0) + 1);
    }

    public UserActivityReportResponse getResult() {
        return new UserActivityReportResponse(userStats);
    }
}