package tech.kitucode.mpira.api.service.dto;

import tech.kitucode.mpira.api.domain.enumerations.PlayerPosition;

import java.time.LocalDate;

public record PlayerDTO(
        Long id,
        String name,
        String iconUrl,
        LocalDate dateOfBirth,
        String country,
        ClubSummary club,
        PlayerPosition position,
        Integer jerseyNumber,
        String alumni
) {
}

