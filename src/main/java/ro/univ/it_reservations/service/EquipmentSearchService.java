package ro.univ.it_reservations.service;

import org.springframework.stereotype.Service;
import ro.univ.it_reservations.entity.Equipment;
import ro.univ.it_reservations.repository.EquipmentRepository;
import ro.univ.it_reservations.service.interpreter.Expression;
import ro.univ.it_reservations.service.interpreter.QueryParser;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EquipmentSearchService {

    private final EquipmentRepository equipmentRepository;
    private final QueryParser queryParser;

    public EquipmentSearchService(EquipmentRepository equipmentRepository, QueryParser queryParser) {
        this.equipmentRepository = equipmentRepository;
        this.queryParser = queryParser;
    }

    public List<Equipment> search(String query) {
        Expression expression = queryParser.parse(query);

        List<Equipment> allEquipment = equipmentRepository.findAll();

        return allEquipment.stream()
                .filter(expression::interpret)
                .collect(Collectors.toList());
    }
}