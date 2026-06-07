package tech.kitucode.mpira.api.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.kitucode.mpira.api.domain.Stadium;
import tech.kitucode.mpira.api.service.StadiumService;
import tech.kitucode.mpira.api.web.util.PaginationUtil;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/stadiums")
public class StadiumResource {
    private final StadiumService stadiumService;

    public StadiumResource(StadiumService stadiumService) {
        this.stadiumService = stadiumService;
    }

    @GetMapping
    public ResponseEntity<List<Stadium>> filter(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "location", required = false) String location,
            Pageable pageable
    ) {
        log.debug("REST request to filter stadiums by name: {}, location: {}", name, location);
        Page<Stadium> page = stadiumService.filter(name, location, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/stadiums");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stadium> findOne(@PathVariable Long id) {
        log.debug("REST request to find stadium by id: {}", id);
        Stadium stadium = stadiumService.findOne(id);
        return ResponseEntity.ok(stadium);
    }
}
