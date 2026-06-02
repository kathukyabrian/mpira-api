package tech.kitucode.mpira.api.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tech.kitucode.mpira.api.domain.League;
import tech.kitucode.mpira.api.domain.enumerations.LeagueLevel;
import tech.kitucode.mpira.api.error.EntityNotFoundException;
import tech.kitucode.mpira.api.repository.LeagueRepository;

/**
 * read only service for the league entity
 */
@Slf4j
@Service
public class LeagueService {
    private final LeagueRepository leagueRepository;

    public LeagueService(LeagueRepository leagueRepository) {
        this.leagueRepository = leagueRepository;
    }

    public Page<League> filter(String name, LeagueLevel level, Pageable pageable) {
        log.debug("Request to filter leagues by name: {}, level: {}", name, level);

        League probe = getProbe(name, level);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<League> example = Example.of(probe, matcher);

        return leagueRepository.findAll(example, pageable);
    }

    public League findOne(Long id) {
        log.debug("Request to find league by id: {}", id);
        return leagueRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("League", id));
    }

    private League getProbe(String name, LeagueLevel level) {
        League league = new League();
        if (name != null && !name.isEmpty()) {
            league.setName(name);
        }
        if (level != null) {
            league.setLevel(level);
        }
        return league;
    }
}
