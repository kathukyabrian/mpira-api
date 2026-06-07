package tech.kitucode.mpira.api.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tech.kitucode.mpira.api.domain.Club;
import tech.kitucode.mpira.api.domain.League;
import tech.kitucode.mpira.api.error.EntityNotFoundException;
import tech.kitucode.mpira.api.repository.ClubRepository;

@Slf4j
@Service
public class ClubService {
    private final ClubRepository clubRepository;

    public ClubService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    public Page<Club> filter(String name, Long leagueId, Integer established, Pageable pageable) {
        log.debug("Request to filter clubs by name: {}, leagueId: {}, established: {}", name, leagueId, established);
        Club probe = getProbe(name, leagueId, established);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Club> example = Example.of(probe, matcher);
        return clubRepository.findAll(example, pageable);
    }

    public Club findOne(Long id) {
        log.debug("Request to find club by id: {}", id);
        return clubRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Club", id));
    }

    private Club getProbe(String name, Long leagueId, Integer established) {
        Club club = new Club();

        if (name != null && !name.isEmpty()) {
            club.setName(name);
        }

        if (leagueId != null) {
            League league = new League();
            league.setId(leagueId);
            club.setLeague(league);
        }

        if (established != null) {
            club.setEstablishmentYear(established);
        }

        return club;
    }
}
