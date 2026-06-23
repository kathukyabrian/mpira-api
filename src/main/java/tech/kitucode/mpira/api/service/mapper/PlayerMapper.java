package tech.kitucode.mpira.api.service.mapper;

import org.springframework.stereotype.Component;
import tech.kitucode.mpira.api.domain.Player;
import tech.kitucode.mpira.api.service.dto.player.PlayerDTO;
import tech.kitucode.mpira.api.service.dto.player.PlayerStintDTO;

import java.util.List;

@Component
public class PlayerMapper {
    private final ClubMapper clubMapper;

    public PlayerMapper(ClubMapper clubMapper) {
        this.clubMapper = clubMapper;
    }

    public PlayerDTO toDTO(Player player, List<PlayerStintDTO> stints) {
        if (player == null) {
            return null;
        }
        return new PlayerDTO(
                player.getId(),
                player.getName(),
                player.getIconUrl(),
                player.getDateOfBirth(),
                player.getCountry(),
                clubMapper.toSummary(player.getClub()),
                player.getPosition(),
                player.getJerseyNumber(),
                player.getAlumni(),
                stints
        );
    }
}
