package program.service.impl;

import program.service.BusStopService;
import program.domain.BusStop;
import program.repository.BusStopRepository;
import program.service.dto.BusStopDTO;
import program.service.mapper.BusStopMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link BusStop}.
 */
@Service
@Transactional
public class BusStopServiceImpl implements BusStopService {

    private final Logger log = LoggerFactory.getLogger(BusStopServiceImpl.class);

    private final BusStopRepository busStopRepository;

    private final BusStopMapper busStopMapper;

    public BusStopServiceImpl(BusStopRepository busStopRepository, BusStopMapper busStopMapper) {
        this.busStopRepository = busStopRepository;
        this.busStopMapper = busStopMapper;
    }

    @Override
    public BusStopDTO save(BusStopDTO busStopDTO) {
        log.debug("Request to save BusStop : {}", busStopDTO);
        BusStop busStop = busStopMapper.toEntity(busStopDTO);
        busStop = busStopRepository.save(busStop);
        return busStopMapper.toDto(busStop);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BusStopDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BusStops");
        return busStopRepository.findAll(pageable)
            .map(busStopMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<BusStopDTO> findOne(Long id) {
        log.debug("Request to get BusStop : {}", id);
        return busStopRepository.findById(id)
            .map(busStopMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete BusStop : {}", id);
        busStopRepository.deleteById(id);
    }
}
