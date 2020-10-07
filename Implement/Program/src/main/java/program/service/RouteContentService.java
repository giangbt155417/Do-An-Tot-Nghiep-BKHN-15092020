package program.service;

import program.domain.RouteContent;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link RouteContent}.
 */
public interface RouteContentService {

    /**
     * Save a routeContent.
     *
     * @param routeContent the entity to save.
     * @return the persisted entity.
     */
    RouteContent save(RouteContent routeContent);

    /**
     * Get all the routeContents.
     *
     * @return the list of entities.
     */
    List<RouteContent> findAll();


    /**
     * Get the "id" routeContent.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RouteContent> findOne(Long id);

    /**
     * Delete the "id" routeContent.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
