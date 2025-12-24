package ro.univ.it_reservations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.univ.it_reservations.entity.AuditLog;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {}
