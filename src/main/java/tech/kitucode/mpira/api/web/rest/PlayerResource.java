package tech.kitucode.mpira.api.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.kitucode.mpira.api.domain.Player;
import tech.kitucode.mpira.api.domain.enumerations.PlayerPosition;
import tech.kitucode.mpira.api.service.PlayerService;
import tech.kitucode.mpira.api.web.util.PaginationUtil;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/players")
public class PlayerResource {
    private final PlayerService playerService;

    public PlayerResource(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public ResponseEntity<List<Player>> filter(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "country", required = false) String country,
            @RequestParam(name = "clubId", required = false) Long clubId,
            @RequestParam(name = "position", required = false) PlayerPosition position,
            @RequestParam(name = "jersey", required = false) Integer jersey,
            @RequestParam(name = "alumni", required = false) String alumni,
            Pageable pageable
    ) {
        log.debug("REST Request to filter players given name: {}, country: {}, clubId: {}, position:{}, jersey: {}, alumni: {}",
                name, country, clubId, position, jersey, alumni);
        Page<Player> page = playerService.filter(name, country, clubId, position, jersey, alumni, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/players");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> findOne(@PathVariable Long id) {
        log.debug("REST request to find player by id: {}", id);
        Player player = playerService.findOne(id);
        return ResponseEntity.ok(player);
    }
}
