package tech.kitucode.mpira.api.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tech.kitucode.mpira.api.domain.Stadium;
import tech.kitucode.mpira.api.error.EntityNotFoundException;
import tech.kitucode.mpira.api.repository.StadiumRepository;

@Slf4j
@Service
public class StadiumService {
    private final StadiumRepository stadiumRepository;

    public StadiumService(StadiumRepository stadiumRepository) {
        this.stadiumRepository = stadiumRepository;
    }

    public Page<Stadium> filter(String name, String location, Pageable pageable) {
        log.debug("Request to filter stadiums given name: {}, location: {}", name, location);
        Stadium probe = getProbe(name, location);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Stadium> example = Example.of(probe, matcher);
        return stadiumRepository.findAll(example, pageable);
    }

    public Stadium findOne(Long id) {
        log.debug("Request to find stadium given id: {}", id);
        return stadiumRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Stadium", id));
    }

    private Stadium getProbe(String name, String location) {
        Stadium stadium = new Stadium();

        if (name != null && !name.isEmpty()) {
            stadium.setName(name);
        }

        if (location != null && !location.isEmpty()) {
            stadium.setLocation(location);
        }

        return stadium;
    }
}
