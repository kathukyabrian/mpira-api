package tech.kitucode.mpira.api.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tech.kitucode.mpira.api.domain.Manager;
import tech.kitucode.mpira.api.error.EntityNotFoundException;
import tech.kitucode.mpira.api.repository.ManagerRepository;

@Slf4j
@Service
public class ManagerService {
    private final ManagerRepository managerRepository;

    public ManagerService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    // filter
    public Page<Manager> filter(String name, String country, Pageable pageable) {
        log.debug("Request to filter managers by name: {}, country: {}", name, country);
        Manager probe = getProbe(name, country);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Manager> example = Example.of(probe, matcher);
        return managerRepository.findAll(example, pageable);
    }

    // find one by id
    public Manager findOne(Long id) {
        log.debug("Request to find manager by id: {}", id);
        return managerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Manager", id));
    }

    private Manager getProbe(String name, String country) {
        Manager manager = new Manager();

        if (name != null && !name.isEmpty()) {
            manager.setName(name);
        }

        if (country != null && !country.isEmpty()) {
            manager.setCountry(country);
        }

        return manager;
    }
}
