package tech.kitucode.mpira.api.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.kitucode.mpira.api.domain.ManagerStint;
import tech.kitucode.mpira.api.repository.ManagerStintRepository;
import tech.kitucode.mpira.api.service.dto.manager.ManagerStintDTO;
import tech.kitucode.mpira.api.service.mapper.ManagerStintMapper;

import java.util.List;

@Slf4j
@Service
public class ManagerStintService {
    private final ManagerStintRepository managerStintRepository;
    private final ManagerStintMapper managerStintMapper;

    public ManagerStintService(ManagerStintRepository managerStintRepository, ManagerStintMapper managerStintMapper) {
        this.managerStintRepository = managerStintRepository;
        this.managerStintMapper = managerStintMapper;
    }

    public List<ManagerStintDTO> findByManagerId(Long managerId) {
        log.debug("Request to find manager stints by managerId: {}", managerId);
        List<ManagerStint> stints = managerStintRepository.findAllByManagerId(managerId);
        return managerStintMapper.toDTO(stints);
    }
}
