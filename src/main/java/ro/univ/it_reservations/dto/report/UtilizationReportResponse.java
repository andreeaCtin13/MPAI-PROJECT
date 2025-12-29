package ro.univ.it_reservations.dto.report;

import java.util.Map;

public class UtilizationReportResponse {
    private Map<String, Integer> countByType;

    public UtilizationReportResponse(Map<String, Integer> countByType) {
        this.countByType = countByType;
    }

    public Map<String, Integer> getCountByType() { return countByType; }
}