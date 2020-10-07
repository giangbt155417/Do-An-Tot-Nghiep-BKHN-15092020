package program.service.impl;

import program.service.RouteBusStopService;
import program.domain.RouteBusStop;
import program.repository.RouteBusStopRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link RouteBusStop}.
 */
@Service
@Transactional
public class RouteBusStopServiceImpl implements RouteBusStopService {

    private final Logger log = LoggerFactory.getLogger(RouteBusStopServiceImpl.class);

    private final RouteBusStopRepository routeBusStopRepository;

    public RouteBusStopServiceImpl(RouteBusStopRepository routeBusStopRepository) {
        this.routeBusStopRepository = routeBusStopRepository;
    }

    @Override
    public RouteBusStop save(RouteBusStop routeBusStop) {
        log.debug("Request to save RouteBusStop : {}", routeBusStop);
        return routeBusStopRepository.save(routeBusStop);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RouteBusStop> findAll() {
        log.debug("Request to get all RouteBusStops");
        return routeBusStopRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<RouteBusStop> findOne(Long id) {
        log.debug("Request to get RouteBusStop : {}", id);
        return routeBusStopRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete RouteBusStop : {}", id);
        routeBusStopRepository.deleteById(id);
    }
}
