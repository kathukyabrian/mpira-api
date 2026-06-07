package tech.kitucode.mpira.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.kitucode.mpira.api.domain.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
