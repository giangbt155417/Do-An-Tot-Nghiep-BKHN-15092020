package program.web.rest;

import program.domain.BusStopNearbyPlace;
import program.service.BusStopNearbyPlaceService;
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
 * REST controller for managing {@link program.domain.BusStopNearbyPlace}.
 */
@RestController
@RequestMapping("/api")
public class BusStopNearbyPlaceResource {

    private final Logger log = LoggerFactory.getLogger(BusStopNearbyPlaceResource.class);

    private static final String ENTITY_NAME = "programBusStopNearbyPlace";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BusStopNearbyPlaceService busStopNearbyPlaceService;

    public BusStopNearbyPlaceResource(BusStopNearbyPlaceService busStopNearbyPlaceService) {
        this.busStopNearbyPlaceService = busStopNearbyPlaceService;
    }

    /**
     * {@code POST  /bus-stop-nearby-places} : Create a new busStopNearbyPlace.
     *
     * @param busStopNearbyPlace the busStopNearbyPlace to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new busStopNearbyPlace, or with status {@code 400 (Bad Request)} if the busStopNearbyPlace has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bus-stop-nearby-places")
    public ResponseEntity<BusStopNearbyPlace> createBusStopNearbyPlace(@Valid @RequestBody BusStopNearbyPlace busStopNearbyPlace) throws URISyntaxException {
        log.debug("REST request to save BusStopNearbyPlace : {}", busStopNearbyPlace);
        if (busStopNearbyPlace.getId() != null) {
            throw new BadRequestAlertException("A new busStopNearbyPlace cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BusStopNearbyPlace result = busStopNearbyPlaceService.save(busStopNearbyPlace);
        return ResponseEntity.created(new URI("/api/bus-stop-nearby-places/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bus-stop-nearby-places} : Updates an existing busStopNearbyPlace.
     *
     * @param busStopNearbyPlace the busStopNearbyPlace to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated busStopNearbyPlace,
     * or with status {@code 400 (Bad Request)} if the busStopNearbyPlace is not valid,
     * or with status {@code 500 (Internal Server Error)} if the busStopNearbyPlace couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bus-stop-nearby-places")
    public ResponseEntity<BusStopNearbyPlace> updateBusStopNearbyPlace(@Valid @RequestBody BusStopNearbyPlace busStopNearbyPlace) throws URISyntaxException {
        log.debug("REST request to update BusStopNearbyPlace : {}", busStopNearbyPlace);
        if (busStopNearbyPlace.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BusStopNearbyPlace result = busStopNearbyPlaceService.save(busStopNearbyPlace);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, busStopNearbyPlace.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bus-stop-nearby-places} : get all the busStopNearbyPlaces.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of busStopNearbyPlaces in body.
     */
    @GetMapping("/bus-stop-nearby-places")
    public List<BusStopNearbyPlace> getAllBusStopNearbyPlaces() {
        log.debug("REST request to get all BusStopNearbyPlaces");
        return busStopNearbyPlaceService.findAll();
    }

    /**
     * {@code GET  /bus-stop-nearby-places/:id} : get the "id" busStopNearbyPlace.
     *
     * @param id the id of the busStopNearbyPlace to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the busStopNearbyPlace, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bus-stop-nearby-places/{id}")
    public ResponseEntity<BusStopNearbyPlace> getBusStopNearbyPlace(@PathVariable Long id) {
        log.debug("REST request to get BusStopNearbyPlace : {}", id);
        Optional<BusStopNearbyPlace> busStopNearbyPlace = busStopNearbyPlaceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(busStopNearbyPlace);
    }

    /**
     * {@code DELETE  /bus-stop-nearby-places/:id} : delete the "id" busStopNearbyPlace.
     *
     * @param id the id of the busStopNearbyPlace to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bus-stop-nearby-places/{id}")
    public ResponseEntity<Void> deleteBusStopNearbyPlace(@PathVariable Long id) {
        log.debug("REST request to delete BusStopNearbyPlace : {}", id);
        busStopNearbyPlaceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
