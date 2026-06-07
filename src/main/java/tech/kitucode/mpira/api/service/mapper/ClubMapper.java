package tech.kitucode.mpira.api.service.mapper;

import org.springframework.stereotype.Component;
import tech.kitucode.mpira.api.domain.Club;
import tech.kitucode.mpira.api.service.dto.ClubSummary;

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
}
