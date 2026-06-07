package tech.kitucode.mpira.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.kitucode.mpira.api.domain.Stadium;

public interface StadiumRepository extends JpaRepository<Stadium, Long> {
}
