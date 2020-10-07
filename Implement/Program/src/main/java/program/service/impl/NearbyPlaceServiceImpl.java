package program.service.impl;

import program.service.NearbyPlaceService;
import program.domain.NearbyPlace;
import program.repository.NearbyPlaceRepository;
import program.service.dto.NearbyPlaceDTO;
import program.service.mapper.NearbyPlaceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link NearbyPlace}.
 */
@Service
@Transactional
public class NearbyPlaceServiceImpl implements NearbyPlaceService {

    private final Logger log = LoggerFactory.getLogger(NearbyPlaceServiceImpl.class);

    private final NearbyPlaceRepository nearbyPlaceRepository;

    private final NearbyPlaceMapper nearbyPlaceMapper;

    public NearbyPlaceServiceImpl(NearbyPlaceRepository nearbyPlaceRepository, NearbyPlaceMapper nearbyPlaceMapper) {
        this.nearbyPlaceRepository = nearbyPlaceRepository;
        this.nearbyPlaceMapper = nearbyPlaceMapper;
    }

    @Override
    public NearbyPlaceDTO save(NearbyPlaceDTO nearbyPlaceDTO) {
        log.debug("Request to save NearbyPlace : {}", nearbyPlaceDTO);
        NearbyPlace nearbyPlace = nearbyPlaceMapper.toEntity(nearbyPlaceDTO);
        nearbyPlace = nearbyPlaceRepository.save(nearbyPlace);
        return nearbyPlaceMapper.toDto(nearbyPlace);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<NearbyPlaceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NearbyPlaces");
        return nearbyPlaceRepository.findAll(pageable)
            .map(nearbyPlaceMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<NearbyPlaceDTO> findOne(Long id) {
        log.debug("Request to get NearbyPlace : {}", id);
        return nearbyPlaceRepository.findById(id)
            .map(nearbyPlaceMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete NearbyPlace : {}", id);
        nearbyPlaceRepository.deleteById(id);
    }
}
