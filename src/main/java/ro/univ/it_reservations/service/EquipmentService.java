package ro.univ.it_reservations.service;

import org.springframework.stereotype.Service;
import ro.univ.it_reservations.entity.Equipment;
import ro.univ.it_reservations.repository.EquipmentRepository;

import java.util.List;

@Service
public class EquipmentService {

    private final EquipmentRepository repo;

    public EquipmentService(EquipmentRepository repo) {
        this.repo = repo;
    }

    public Equipment create(Equipment e) {
        return repo.save(e);
    }

    public List<Equipment> findAll() {
        return repo.findAll();
    }

    public Equipment findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Equipment not found: " + id));
    }

    public Equipment update(Long id, Equipment updated) {
        Equipment existing = findById(id);
        existing.setName(updated.getName());
        existing.setType(updated.getType());
        existing.setAvailable(updated.isAvailable());
        return repo.save(existing);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
