package io.clnra.c_l_n_r_a.service;

import io.clnra.c_l_n_r_a.domain.GameCount;
import io.clnra.c_l_n_r_a.model.GameCountDTO;
import io.clnra.c_l_n_r_a.repos.GameCountRepository;
import io.clnra.c_l_n_r_a.util.NotFoundException;
import io.clnra.c_l_n_r_a.util.WebUtils;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class GameCountService {

    private final GameCountRepository gameCountRepository;

    public GameCountService(final GameCountRepository gameCountRepository) {
        this.gameCountRepository = gameCountRepository;
    }

    public List<GameCountDTO> findAll() {
        final List<GameCount> gameCounts = gameCountRepository.findAll(Sort.by("id"));
        return gameCounts.stream()
                .map((gameCount) -> mapToDTO(gameCount, new GameCountDTO()))
                .collect(Collectors.toList());
    }

    public GameCountDTO get(final Long id) {
        return gameCountRepository.findById(id)
                .map(gameCount -> mapToDTO(gameCount, new GameCountDTO()))
                .orElseThrow(() -> new NotFoundException());
    }

    public Long create(final GameCountDTO gameCountDTO) {
        final GameCount gameCount = new GameCount();
        mapToEntity(gameCountDTO, gameCount);
        return gameCountRepository.save(gameCount).getId();
    }

    public void update(final Long id, final GameCountDTO gameCountDTO) {
        final GameCount gameCount = gameCountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
        mapToEntity(gameCountDTO, gameCount);
        gameCountRepository.save(gameCount);
    }

    public void delete(final Long id) {
        gameCountRepository.deleteById(id);
    }

    private GameCountDTO mapToDTO(final GameCount gameCount, final GameCountDTO gameCountDTO) {
        gameCountDTO.setId(gameCount.getId());
        gameCountDTO.setGameCount(gameCount.getGameCount());
        gameCountDTO.setAnimalType(gameCount.getAnimalType());
        gameCountDTO.setAdultMale(gameCount.getAdultMale());
        gameCountDTO.setAdultFemale(gameCount.getAdultFemale());
        gameCountDTO.setSubAdultMale(gameCount.getSubAdultMale());
        gameCountDTO.setSubadultFemale(gameCount.getSubadultFemale());
        gameCountDTO.setJuvenileMale(gameCount.getJuvenileMale());
        gameCountDTO.setJuvenileFemale(gameCount.getJuvenileFemale());
        return gameCountDTO;
    }

    private GameCount mapToEntity(final GameCountDTO gameCountDTO, final GameCount gameCount) {
        gameCount.setGameCount(gameCountDTO.getGameCount());
        gameCount.setAnimalType(gameCountDTO.getAnimalType());
        gameCount.setAdultMale(gameCountDTO.getAdultMale());
        gameCount.setAdultFemale(gameCountDTO.getAdultFemale());
        gameCount.setSubAdultMale(gameCountDTO.getSubAdultMale());
        gameCount.setSubadultFemale(gameCountDTO.getSubadultFemale());
        gameCount.setJuvenileMale(gameCountDTO.getJuvenileMale());
        gameCount.setJuvenileFemale(gameCountDTO.getJuvenileFemale());
        return gameCount;
    }

    @Transactional
    public String getReferencedWarning(final Long id) {
        final GameCount gameCount = gameCountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
        if (!gameCount.getWildlifePPCBORegistrations().isEmpty()) {
            return WebUtils.getMessage("gameCount.cBORegistration.manyToMany.referenced", gameCount.getWildlifePPCBORegistrations().iterator().next().getId());
        }
        return null;
    }

}
