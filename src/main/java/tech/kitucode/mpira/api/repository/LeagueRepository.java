package tech.kitucode.mpira.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.kitucode.mpira.api.domain.League;

public interface LeagueRepository extends JpaRepository<League, Long> {
}
