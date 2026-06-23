package tech.kitucode.mpira.api.service.mapper;

import org.springframework.stereotype.Component;
import tech.kitucode.mpira.api.domain.Manager;
import tech.kitucode.mpira.api.domain.Player;
import tech.kitucode.mpira.api.service.dto.manager.ManagerDTO;
import tech.kitucode.mpira.api.service.dto.manager.ManagerStintDTO;
import tech.kitucode.mpira.api.service.dto.player.PlayerStintDTO;

import java.util.List;

@Component
public class ManagerMapper {
    private final ClubMapper clubMapper;

    public ManagerMapper(ClubMapper clubMapper) {
        this.clubMapper = clubMapper;
    }

    public ManagerDTO toDTO(Manager manager, List<ManagerStintDTO> stints) {
        if (manager == null) {
            return null;
        }
        return new ManagerDTO(
                manager.getId(),
                manager.getName(),
                manager.getIconUrl(),
                manager.getDateOfBirth(),
                manager.getCountry(),
                stints
        );
    }

    public PlayerWithStintDTO toPlayerWithStintDTO(Player player, List<PlayerStintDTO> stints) {
        if (player == null) {
            return null;
        }
        return new PlayerWithStintDTO(
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
