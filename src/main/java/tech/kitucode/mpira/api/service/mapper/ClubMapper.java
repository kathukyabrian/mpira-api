package tech.kitucode.mpira.api.service.mapper;

import org.springframework.stereotype.Component;
import tech.kitucode.mpira.api.domain.Club;
import tech.kitucode.mpira.api.service.dto.club.ClubDTO;
import tech.kitucode.mpira.api.service.dto.club.ClubSummary;

@Component
public class ClubMapper {

    public ClubSummary toSummary(Club club) {
        if (club == null) {
            return null;
        }

        return new ClubSummary(
                club.getId(),
                club.getName(),
                club.getIconUrl()
        );
    }

    public ClubDTO toDTO(Club club) {
        if (club == null) {
            return null;
        }

        return new ClubDTO(
                club.getId(),
                club.getName(),
                club.getEstablishmentYear(),
                club.getDescription(),
                club.getIconUrl(),
                club.getLeague() != null ? club.getLeague().getName() : null
        );
    }
}
