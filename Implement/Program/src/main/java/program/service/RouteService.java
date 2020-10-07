package program.service;

import program.service.dto.RouteDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link program.domain.Route}.
 */
public interface RouteService {

    /**
     * Save a route.
     *
     * @param routeDTO the entity to save.
     * @return the persisted entity.
     */
    RouteDTO save(RouteDTO routeDTO);

    /**
     * Get all the routes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RouteDTO> findAll(Pageable pageable);


    /**
     * Get the "id" route.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RouteDTO> findOne(Long id);

    /**
     * Delete the "id" route.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

	List<RouteDTO> findRoutesByProjectId(Long projectId, int pageNumber);

	int countRoutesByProjectId(Long projectId);
}
