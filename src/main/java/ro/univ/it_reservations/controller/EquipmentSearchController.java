package ro.univ.it_reservations.controller;

import org.springframework.web.bind.annotation.*;
import ro.univ.it_reservations.entity.Equipment;
import ro.univ.it_reservations.service.EquipmentSearchService;

import java.util.List;

@RestController
@RequestMapping("/equipments/search")
public class EquipmentSearchController {

    private final EquipmentSearchService searchService;

    public EquipmentSearchController(EquipmentSearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping()
    public List<Equipment> search(@RequestParam(name = "query", required = false) String query) {
        return searchService.search(query);
    }
}