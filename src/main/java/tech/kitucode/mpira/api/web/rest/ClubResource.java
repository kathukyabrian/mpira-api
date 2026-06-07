package tech.kitucode.mpira.api.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.kitucode.mpira.api.domain.Club;
import tech.kitucode.mpira.api.service.ClubService;
import tech.kitucode.mpira.api.web.util.PaginationUtil;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/clubs")
public class ClubResource {
    private final ClubService clubService;

    public ClubResource(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping
    public ResponseEntity<List<Club>> filter(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "leagueId", required = false) Long leagueId,
            @RequestParam(name = "established", required = false) Integer established,
            Pageable pageable
    ) {
        log.debug("REST request to filter clubs by name: {}, leagueId: {}, established: {}", name, leagueId, established);
        Page<Club> page = clubService.filter(name, leagueId, established, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/clubs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Club> findOne(@PathVariable Long id) {
        log.debug("REST request to find club by id: {}", id);
        Club club = clubService.findOne(id);
        return ResponseEntity.ok(club);
    }
}
