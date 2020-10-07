package program.service;

import program.domain.RouteBusStop;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link RouteBusStop}.
 */
public interface RouteBusStopService {

    /**
     * Save a routeBusStop.
     *
     * @param routeBusStop the entity to save.
     * @return the persisted entity.
     */
    RouteBusStop save(RouteBusStop routeBusStop);

    /**
     * Get all the routeBusStops.
     *
     * @return the list of entities.
     */
    List<RouteBusStop> findAll();


    /**
     * Get the "id" routeBusStop.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RouteBusStop> findOne(Long id);

    /**
     * Delete the "id" routeBusStop.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
