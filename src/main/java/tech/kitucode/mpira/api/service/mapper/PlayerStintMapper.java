package tech.kitucode.mpira.api.service.mapper;

import org.springframework.stereotype.Component;
import tech.kitucode.mpira.api.domain.PlayerStint;
import tech.kitucode.mpira.api.service.dto.club.ClubDTO;
import tech.kitucode.mpira.api.service.dto.player.PlayerStintDTO;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlayerStintMapper {
    private final ClubMapper clubMapper;

    public PlayerStintMapper(ClubMapper clubMapper) {
        this.clubMapper = clubMapper;
    }

    public PlayerStintDTO toDTO(PlayerStint playerStint) {
        if (playerStint == null) {
            return null;
        }

        ClubDTO club = clubMapper.toDTO(playerStint.getClub());

        return new PlayerStintDTO(
                playerStint.getId(),
                playerStint.getStartDate(),
                playerStint.getEndDate(),
                playerStint.getSigningFee(),
                playerStint.getContractType(),
                playerStint.getActive(),
                club
        );
    }

    public List<PlayerStintDTO> toDTO(List<PlayerStint> stints) {
        List<PlayerStintDTO> playerStints = new ArrayList<>();
        for (PlayerStint playerStint : stints) {
            playerStints.add(toDTO(playerStint));
        }
        return playerStints;
    }
}
