package program.web.rest;

import program.service.RouteService;
import program.web.rest.errors.BadRequestAlertException;
import program.service.dto.ProjectDTO;
import program.service.dto.RouteDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link program.domain.Route}.
 */
@RestController
@RequestMapping("/api")
public class RouteResource {

    private final Logger log = LoggerFactory.getLogger(RouteResource.class);

    private static final String ENTITY_NAME = "programRoute";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RouteService routeService;

    public RouteResource(RouteService routeService) {
        this.routeService = routeService;
    }

    /**
     * {@code POST  /routes} : Create a new route.
     *
     * @param routeDTO the routeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new routeDTO, or with status {@code 400 (Bad Request)} if the route has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/routes")
    public ResponseEntity<RouteDTO> createRoute(@Valid @RequestBody RouteDTO routeDTO) throws URISyntaxException {
        log.debug("REST request to save Route : {}", routeDTO);
        if (routeDTO.getId() != null) {
            throw new BadRequestAlertException("A new route cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RouteDTO result = routeService.save(routeDTO);
        return ResponseEntity.created(new URI("/api/routes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /routes} : Updates an existing route.
     *
     * @param routeDTO the routeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated routeDTO,
     * or with status {@code 400 (Bad Request)} if the routeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the routeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/routes")
    public ResponseEntity<RouteDTO> updateRoute(@Valid @RequestBody RouteDTO routeDTO) throws URISyntaxException {
        log.debug("REST request to update Route : {}", routeDTO);
        if (routeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RouteDTO result = routeService.save(routeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, routeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /routes} : get all the routes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of routes in body.
     */
    @GetMapping("/routes")
    public ResponseEntity<List<RouteDTO>> getAllRoutes(Pageable pageable) {
        log.debug("REST request to get a page of Routes");
        Page<RouteDTO> page = routeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /routes/:id} : get the "id" route.
     *
     * @param id the id of the routeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the routeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/routes/{id}")
    public ResponseEntity<RouteDTO> getRoute(@PathVariable Long id) {
        log.debug("REST request to get Route : {}", id);
        Optional<RouteDTO> routeDTO = routeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(routeDTO);
    }

    /**
     * {@code DELETE  /routes/:id} : delete the "id" route.
     *
     * @param id the id of the routeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/routes/{id}")
    public ResponseEntity<Void> deleteRoute(@PathVariable Long id) {
        log.debug("REST request to delete Route : {}", id);
        routeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
    
    @GetMapping("/routes/count/project/{projectId}")
    public int countAllRoute(@PathVariable Long projectId) {
    	return routeService.countRoutesByProjectId(projectId);
    }
    
    @GetMapping("/routes/project/{projectId}")
    public List<RouteDTO> getProjectsByUserId(@PathVariable Long projectId, @RequestParam(value = "pageNumber") int pageNumber){
    	return routeService.findRoutesByProjectId(projectId, pageNumber);
    }
}
