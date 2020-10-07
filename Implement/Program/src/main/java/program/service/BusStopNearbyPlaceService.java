package program.service;

import program.domain.BusStopNearbyPlace;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link BusStopNearbyPlace}.
 */
public interface BusStopNearbyPlaceService {

    /**
     * Save a busStopNearbyPlace.
     *
     * @param busStopNearbyPlace the entity to save.
     * @return the persisted entity.
     */
    BusStopNearbyPlace save(BusStopNearbyPlace busStopNearbyPlace);

    /**
     * Get all the busStopNearbyPlaces.
     *
     * @return the list of entities.
     */
    List<BusStopNearbyPlace> findAll();


    /**
     * Get the "id" busStopNearbyPlace.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BusStopNearbyPlace> findOne(Long id);

    /**
     * Delete the "id" busStopNearbyPlace.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
