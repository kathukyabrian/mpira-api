package tech.kitucode.mpira.api.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tech.kitucode.mpira.api.domain.Club;
import tech.kitucode.mpira.api.domain.Player;
import tech.kitucode.mpira.api.domain.enumerations.PlayerPosition;
import tech.kitucode.mpira.api.error.EntityNotFoundException;
import tech.kitucode.mpira.api.repository.PlayerRepository;
import tech.kitucode.mpira.api.service.dto.player.PlayerDTO;
import tech.kitucode.mpira.api.service.dto.player.PlayerStintDTO;
import tech.kitucode.mpira.api.service.dto.player.PlayerWithStintDTO;
import tech.kitucode.mpira.api.service.mapper.PlayerMapper;

import java.util.List;

@Slf4j
@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;
    private final PlayerStintService playerStintService;

    public PlayerService(PlayerRepository playerRepository, PlayerMapper playerMapper, PlayerStintService playerStintService) {
        this.playerRepository = playerRepository;
        this.playerMapper = playerMapper;
        this.playerStintService = playerStintService;
    }

    public Page<PlayerDTO> filter(String name, String country, Long clubId, PlayerPosition position, Integer jerseyNumber, String alumni, Pageable pageable) {
        log.debug("Request to filter players given name: {}, country: {}, clubId: {}, position:{}, jersey: {}, alumni: {}",
                name, country, clubId, position, jerseyNumber, alumni);
        Player probe = getProbe(name, country, clubId, position, jerseyNumber, alumni);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Player> example = Example.of(probe, matcher);
        return playerRepository.findAll(example, pageable).map(playerMapper::toDTO);
    }

    public PlayerWithStintDTO findOne(Long id) {
        log.debug("Request to find player given id: {}", id);
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Player", id));
        List<PlayerStintDTO> stints = playerStintService.findByPlayerId(id);
        return playerMapper.toPlayerWithStintDTO(player, stints);
    }


    private Player getProbe(String name, String country, Long clubId, PlayerPosition position, Integer jerseyNumber, String alumni) {
        Player player = new Player();

        if (name != null && !name.isEmpty()) {
            player.setName(name);
        }

        if (country != null && !country.isEmpty()) {
            player.setCountry(country);
        }

        if (clubId != null) {
            Club club = new Club();
            club.setId(clubId);
            player.setClub(club);
        }

        if (position != null) {
            player.setPosition(position);
        }

        if (jerseyNumber != null) {
            player.setJerseyNumber(jerseyNumber);
        }

        if (alumni != null && !alumni.isEmpty()) {
            player.setAlumni(alumni);
        }

        return player;
    }

}
