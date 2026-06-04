package tech.kitucode.mpira.api.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tech.kitucode.mpira.api.domain.Season;
import tech.kitucode.mpira.api.error.EntityNotFoundException;
import tech.kitucode.mpira.api.repository.SeasonRepository;

@Slf4j
@Service
public class SeasonService {
    private final SeasonRepository seasonRepository;

    public SeasonService(SeasonRepository seasonRepository) {
        this.seasonRepository = seasonRepository;
    }

    public Page<Season> filter(Long leagueId, Integer startYear, Integer endYear, Boolean active, Pageable pageable) {
        log.info("Request to filter seasons given leagueId: {}, startYear: {}, endYear: {}, active: {}", leagueId, startYear, endYear, active);
        Season probe = getProbe(leagueId, startYear, endYear, active);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Season> example = Example.of(probe, matcher);
        return seasonRepository.findAll(example, pageable);
    }

    public Season findActiveSeasonByLeagueId(Long leagueId) {
        log.info("Request to get active season by leagueId: {}", leagueId);
        return seasonRepository.findOneByLeagueIdAndActive(leagueId, true)
                .orElseThrow(() -> new EntityNotFoundException("Active season for league '" + leagueId + "' does not exist"));
    }

    public Season getProbe(Long leagueId, Integer startYear, Integer endYear, Boolean active) {
        Season season = new Season();

        if (leagueId != null) {
            season.setLeagueId(leagueId);
        }

        if (startYear != null) {
            season.setStartYear(startYear);
        }

        if (endYear != null) {
            season.setEndYear(endYear);
        }

        if (active != null) {
            season.setActive(active);
        }

        return season;
    }
}
