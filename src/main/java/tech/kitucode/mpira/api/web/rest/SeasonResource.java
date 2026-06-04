package tech.kitucode.mpira.api.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.kitucode.mpira.api.domain.Season;
import tech.kitucode.mpira.api.service.SeasonService;
import tech.kitucode.mpira.api.web.util.PaginationUtil;

import java.util.List;

@Slf4j
@RequestMapping("/api/seasons")
@RestController
public class SeasonResource {
    private final SeasonService seasonService;

    public SeasonResource(SeasonService seasonService) {
        this.seasonService = seasonService;
    }

    @GetMapping
    public ResponseEntity<List<Season>> filter(
            @RequestParam(required = false, name = "leagueId") Long leagueId,
            @RequestParam(required = false, name = "startYear") Integer startYear,
            @RequestParam(required = false, name = "endYear") Integer endYear,
            @RequestParam(required = false, name = "active") Boolean active,
            Pageable pageable) {
        log.debug("REST request to find season by leagueId: {}, startYear: {}, endYear: {}, active: {}",
                leagueId, startYear, endYear, active);

        Page<Season> page = seasonService.filter(leagueId, startYear, endYear, active, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/seasons");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/active")
    public ResponseEntity<Season> findActive(@RequestParam(name = "leagueId") Long leagueId) {
        log.debug("Request to find active season by leagueId: {}", leagueId);
        Season season = seasonService.findActiveSeasonByLeagueId(leagueId);
        return ResponseEntity.ok(season);
    }

}
