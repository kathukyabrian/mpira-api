package tech.kitucode.mpira.api.service.dto.club;

public record ClubDTO(
        Long id,
        String name,
        Integer establishmentYear,
        String description,
        String iconUrl,
        String leagueName
) {
}

