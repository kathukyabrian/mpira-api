package tech.kitucode.mpira.api.service.dto.player;

import tech.kitucode.mpira.api.domain.enumerations.ContractType;
import tech.kitucode.mpira.api.service.dto.club.ClubDTO;

import java.time.LocalDate;

public record PlayerStintDTO(
        Long id,
        LocalDate startDate,
        LocalDate endDate,
        Double signingFee,
        ContractType contractType,
        Boolean active,
        ClubDTO club
) {
}
