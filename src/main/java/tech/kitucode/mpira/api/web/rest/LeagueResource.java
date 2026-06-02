package tech.kitucode.mpira.api.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.kitucode.mpira.api.domain.League;
import tech.kitucode.mpira.api.domain.enumerations.LeagueLevel;
import tech.kitucode.mpira.api.service.LeagueService;
import tech.kitucode.mpira.api.web.util.PaginationUtil;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/leagues")
public class LeagueResource {
    private final LeagueService leagueService;

    public LeagueResource(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    @GetMapping
    public ResponseEntity<List<League>> filter(
            @RequestParam(required = false, name = "name") String name,
            @RequestParam(required = false, name = "level") LeagueLevel level,
            Pageable pageable
    ) {
        log.debug("REST request to filter leagues given name: {} and level: {}", name, level);
        Page<League> page = leagueService.filter(name, level, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/leagues");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<League> findOne(@PathVariable Long id) {
        log.debug("REST request to find league by id: {}", id);
        League league = leagueService.findOne(id);
        return ResponseEntity.ok(league);
    }
}
