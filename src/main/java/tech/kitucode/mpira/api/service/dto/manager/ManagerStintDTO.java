package tech.kitucode.mpira.api.service.dto.manager;

import tech.kitucode.mpira.api.service.dto.club.ClubDTO;

import java.time.LocalDate;

public record ManagerStintDTO(
        Long id,
        LocalDate startDate,
        LocalDate endDate,
        Double signingFee,
        Boolean active,
        ClubDTO club
) {
}

