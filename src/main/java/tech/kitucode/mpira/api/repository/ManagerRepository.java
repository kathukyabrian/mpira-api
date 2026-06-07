package tech.kitucode.mpira.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.kitucode.mpira.api.domain.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
}
