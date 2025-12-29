package ro.univ.it_reservations.dto.report;

import java.util.Map;

public class UserActivityReportResponse {
    private Map<String, Integer> reservationsByUser;

    public UserActivityReportResponse(Map<String, Integer> reservationsByUser) {
        this.reservationsByUser = reservationsByUser;
    }

    public Map<String, Integer> getReservationsByUser() { return reservationsByUser; }
}