package tech.kitucode.mpira.api.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.kitucode.mpira.api.domain.Manager;
import tech.kitucode.mpira.api.service.ManagerService;
import tech.kitucode.mpira.api.web.util.PaginationUtil;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/managers")
public class ManagerResource {
    private final ManagerService managerService;

    public ManagerResource(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping
    public ResponseEntity<List<Manager>> filter(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "country", required = false) String country,
            Pageable pageable
    ) {
        log.debug("REST request to filter managers by name: {}, country: {}", name, country);
        Page<Manager> page = managerService.filter(name, country, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/managers");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manager> findOne(@PathVariable Long id) {
        log.debug("REST request to find manager by id: {}", id);
        Manager manager = managerService.findOne(id);
        return ResponseEntity.ok(manager);
    }
}
