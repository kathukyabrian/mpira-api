package tech.kitucode.mpira.api.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.kitucode.mpira.api.domain.PlayerStint;
import tech.kitucode.mpira.api.repository.PlayerStintRepository;
import tech.kitucode.mpira.api.service.dto.player.PlayerStintDTO;
import tech.kitucode.mpira.api.service.mapper.PlayerStintMapper;

import java.util.List;

@Slf4j
@Service
public class PlayerStintService {
    private final PlayerStintRepository playerStintRepository;
    private final PlayerStintMapper playerStintMapper;

    public PlayerStintService(PlayerStintRepository playerStintRepository, PlayerStintMapper playerStintMapper) {
        this.playerStintRepository = playerStintRepository;
        this.playerStintMapper = playerStintMapper;
    }

    public List<PlayerStintDTO> findByPlayerId(Long playerId) {
        log.debug("Request to find player stints by playerId: {}", playerId);
        List<PlayerStint> stints = playerStintRepository.findAllByPlayerId(playerId);
        return playerStintMapper.toDTO(stints);
    }
}
