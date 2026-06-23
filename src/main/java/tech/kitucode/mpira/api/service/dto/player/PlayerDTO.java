package tech.kitucode.mpira.api.service.dto.player;

import tech.kitucode.mpira.api.domain.enumerations.PlayerPosition;
import tech.kitucode.mpira.api.service.dto.club.ClubSummary;

import java.time.LocalDate;
import java.util.List;

public record PlayerDTO(
        Long id,
        String name,
        String iconUrl,
        LocalDate dateOfBirth,
        String country,
        ClubSummary club,
        PlayerPosition position,
        Integer jerseyNumber,
        String alumni,
        List<PlayerStintDTO> stints
) {
}

