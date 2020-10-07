package program.service;

import program.service.dto.BusStopDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link program.domain.BusStop}.
 */
public interface BusStopService {

    /**
     * Save a busStop.
     *
     * @param busStopDTO the entity to save.
     * @return the persisted entity.
     */
    BusStopDTO save(BusStopDTO busStopDTO);

    /**
     * Get all the busStops.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BusStopDTO> findAll(Pageable pageable);


    /**
     * Get the "id" busStop.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BusStopDTO> findOne(Long id);

    /**
     * Delete the "id" busStop.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
