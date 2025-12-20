package ro.univ.it_reservations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.univ.it_reservations.entity.Equipment;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
}
