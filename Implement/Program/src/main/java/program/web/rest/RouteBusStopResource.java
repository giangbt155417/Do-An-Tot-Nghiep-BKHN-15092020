package program.web.rest;

import program.domain.RouteBusStop;
import program.service.RouteBusStopService;
import program.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link program.domain.RouteBusStop}.
 */
@RestController
@RequestMapping("/api")
public class RouteBusStopResource {

    private final Logger log = LoggerFactory.getLogger(RouteBusStopResource.class);

    private static final String ENTITY_NAME = "programRouteBusStop";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RouteBusStopService routeBusStopService;

    public RouteBusStopResource(RouteBusStopService routeBusStopService) {
        this.routeBusStopService = routeBusStopService;
    }

    /**
     * {@code POST  /route-bus-stops} : Create a new routeBusStop.
     *
     * @param routeBusStop the routeBusStop to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new routeBusStop, or with status {@code 400 (Bad Request)} if the routeBusStop has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/route-bus-stops")
    public ResponseEntity<RouteBusStop> createRouteBusStop(@Valid @RequestBody RouteBusStop routeBusStop) throws URISyntaxException {
        log.debug("REST request to save RouteBusStop : {}", routeBusStop);
        if (routeBusStop.getId() != null) {
            throw new BadRequestAlertException("A new routeBusStop cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RouteBusStop result = routeBusStopService.save(routeBusStop);
        return ResponseEntity.created(new URI("/api/route-bus-stops/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /route-bus-stops} : Updates an existing routeBusStop.
     *
     * @param routeBusStop the routeBusStop to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated routeBusStop,
     * or with status {@code 400 (Bad Request)} if the routeBusStop is not valid,
     * or with status {@code 500 (Internal Server Error)} if the routeBusStop couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/route-bus-stops")
    public ResponseEntity<RouteBusStop> updateRouteBusStop(@Valid @RequestBody RouteBusStop routeBusStop) throws URISyntaxException {
        log.debug("REST request to update RouteBusStop : {}", routeBusStop);
        if (routeBusStop.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RouteBusStop result = routeBusStopService.save(routeBusStop);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, routeBusStop.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /route-bus-stops} : get all the routeBusStops.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of routeBusStops in body.
     */
    @GetMapping("/route-bus-stops")
    public List<RouteBusStop> getAllRouteBusStops() {
        log.debug("REST request to get all RouteBusStops");
        return routeBusStopService.findAll();
    }

    /**
     * {@code GET  /route-bus-stops/:id} : get the "id" routeBusStop.
     *
     * @param id the id of the routeBusStop to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the routeBusStop, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/route-bus-stops/{id}")
    public ResponseEntity<RouteBusStop> getRouteBusStop(@PathVariable Long id) {
        log.debug("REST request to get RouteBusStop : {}", id);
        Optional<RouteBusStop> routeBusStop = routeBusStopService.findOne(id);
        return ResponseUtil.wrapOrNotFound(routeBusStop);
    }

    /**
     * {@code DELETE  /route-bus-stops/:id} : delete the "id" routeBusStop.
     *
     * @param id the id of the routeBusStop to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/route-bus-stops/{id}")
    public ResponseEntity<Void> deleteRouteBusStop(@PathVariable Long id) {
        log.debug("REST request to delete RouteBusStop : {}", id);
        routeBusStopService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
