package tech.kitucode.mpira.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.kitucode.mpira.api.domain.PlayerStint;

public interface PlayerStintRepository extends JpaRepository<PlayerStint, Long> {
}
