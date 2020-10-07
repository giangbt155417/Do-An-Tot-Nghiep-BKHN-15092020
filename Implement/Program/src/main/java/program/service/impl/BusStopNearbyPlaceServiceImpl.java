package program.service.impl;

import program.service.BusStopNearbyPlaceService;
import program.domain.BusStopNearbyPlace;
import program.repository.BusStopNearbyPlaceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link BusStopNearbyPlace}.
 */
@Service
@Transactional
public class BusStopNearbyPlaceServiceImpl implements BusStopNearbyPlaceService {

    private final Logger log = LoggerFactory.getLogger(BusStopNearbyPlaceServiceImpl.class);

    private final BusStopNearbyPlaceRepository busStopNearbyPlaceRepository;

    public BusStopNearbyPlaceServiceImpl(BusStopNearbyPlaceRepository busStopNearbyPlaceRepository) {
        this.busStopNearbyPlaceRepository = busStopNearbyPlaceRepository;
    }

    @Override
    public BusStopNearbyPlace save(BusStopNearbyPlace busStopNearbyPlace) {
        log.debug("Request to save BusStopNearbyPlace : {}", busStopNearbyPlace);
        return busStopNearbyPlaceRepository.save(busStopNearbyPlace);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BusStopNearbyPlace> findAll() {
        log.debug("Request to get all BusStopNearbyPlaces");
        return busStopNearbyPlaceRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<BusStopNearbyPlace> findOne(Long id) {
        log.debug("Request to get BusStopNearbyPlace : {}", id);
        return busStopNearbyPlaceRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete BusStopNearbyPlace : {}", id);
        busStopNearbyPlaceRepository.deleteById(id);
    }
}
