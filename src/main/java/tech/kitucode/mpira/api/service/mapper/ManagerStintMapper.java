package tech.kitucode.mpira.api.service.mapper;

import org.springframework.stereotype.Component;
import tech.kitucode.mpira.api.domain.ManagerStint;
import tech.kitucode.mpira.api.service.dto.club.ClubDTO;
import tech.kitucode.mpira.api.service.dto.manager.ManagerStintDTO;

import java.util.ArrayList;
import java.util.List;

@Component
public class ManagerStintMapper {
    private final ClubMapper clubMapper;

    public ManagerStintMapper(ClubMapper clubMapper) {
        this.clubMapper = clubMapper;
    }

    public ManagerStintDTO toDTO(ManagerStint managerStint) {
        if (managerStint == null) {
            return null;
        }

        ClubDTO club = clubMapper.toDTO(managerStint.getClub());

        return new ManagerStintDTO(
                managerStint.getId(),
                managerStint.getStartDate(),
                managerStint.getEndDate(),
                managerStint.getSigningFee(),
                managerStint.getActive(),
                club
        );
    }

    public List<ManagerStintDTO> toDTO(List<ManagerStint> stints) {
        List<ManagerStintDTO> managerStints = new ArrayList<>();
        for (ManagerStint managerStint : stints) {
            managerStints.add(toDTO(managerStint));
        }
        return managerStints;
    }
}
