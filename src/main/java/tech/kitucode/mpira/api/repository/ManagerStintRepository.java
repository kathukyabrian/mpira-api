package tech.kitucode.mpira.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.kitucode.mpira.api.domain.ManagerStint;

import java.util.List;

public interface ManagerStintRepository extends JpaRepository<ManagerStint, Long> {
    List<ManagerStint> findAllByManagerId(Long managerId);
}
