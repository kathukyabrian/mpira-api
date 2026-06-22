package tech.kitucode.mpira.api.service.dto.manager;

import tech.kitucode.mpira.api.domain.enumerations.PlayerPosition;
import tech.kitucode.mpira.api.service.dto.club.ClubSummary;

import java.time.LocalDate;
import java.util.List;

public record ManagerDTO(
        Long id,
        String name,
        String iconUrl,
        LocalDate dateOfBirth,
        String country,
        List<ManagerStintDTO> stints
) {
}
