package ro.univ.it_reservations.controller;

import org.springframework.web.bind.annotation.*;
import ro.univ.it_reservations.dto.EquipmentRequest;
import ro.univ.it_reservations.dto.EquipmentResponse;
import ro.univ.it_reservations.entity.Equipment;
import ro.univ.it_reservations.mapper.EquipmentMapper;
import ro.univ.it_reservations.service.EquipmentService;

import java.util.List;

@RestController
@RequestMapping("/equipments")
public class EquipmentController {

    private final EquipmentService service;
    private final EquipmentMapper mapper;

    public EquipmentController(EquipmentService service, EquipmentMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public EquipmentResponse create(@RequestBody EquipmentRequest req) {
        Equipment entity = mapper.toEntityForCreate(req);
        Equipment saved = service.create(entity);
        return mapper.toResponse(saved);
    }

    @GetMapping
    public List<EquipmentResponse> getAll() {
        return service.findAll().stream().map(mapper::toResponse).toList();
    }

    @GetMapping("/{id}")
    public EquipmentResponse getById(@PathVariable Long id) {
        return mapper.toResponse(service.findById(id));
    }

    @PutMapping("/{id}")
    public EquipmentResponse update(@PathVariable Long id, @RequestBody EquipmentRequest req) {
        Equipment existing = service.findById(id);
        mapper.updateEntity(existing, req);
        Equipment saved = service.create(existing);
        return mapper.toResponse(saved);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
