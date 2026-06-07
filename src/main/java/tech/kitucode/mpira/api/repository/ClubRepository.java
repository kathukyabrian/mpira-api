package tech.kitucode.mpira.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.kitucode.mpira.api.domain.Club;

public interface ClubRepository extends JpaRepository<Club, Long> {
}
