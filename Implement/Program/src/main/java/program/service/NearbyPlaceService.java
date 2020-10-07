package program.service;

import program.service.dto.NearbyPlaceDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link program.domain.NearbyPlace}.
 */
public interface NearbyPlaceService {

    /**
     * Save a nearbyPlace.
     *
     * @param nearbyPlaceDTO the entity to save.
     * @return the persisted entity.
     */
    NearbyPlaceDTO save(NearbyPlaceDTO nearbyPlaceDTO);

    /**
     * Get all the nearbyPlaces.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<NearbyPlaceDTO> findAll(Pageable pageable);


    /**
     * Get the "id" nearbyPlace.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NearbyPlaceDTO> findOne(Long id);

    /**
     * Delete the "id" nearbyPlace.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
