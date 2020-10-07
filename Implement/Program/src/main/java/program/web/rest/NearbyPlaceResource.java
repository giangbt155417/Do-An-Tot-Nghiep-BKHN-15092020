package program.web.rest;

import program.service.NearbyPlaceService;
import program.web.rest.errors.BadRequestAlertException;
import program.service.dto.NearbyPlaceDTO;

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
 * REST controller for managing {@link program.domain.NearbyPlace}.
 */
@RestController
@RequestMapping("/api")
public class NearbyPlaceResource {

    private final Logger log = LoggerFactory.getLogger(NearbyPlaceResource.class);

    private static final String ENTITY_NAME = "programNearbyPlace";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NearbyPlaceService nearbyPlaceService;

    public NearbyPlaceResource(NearbyPlaceService nearbyPlaceService) {
        this.nearbyPlaceService = nearbyPlaceService;
    }

    /**
     * {@code POST  /nearby-places} : Create a new nearbyPlace.
     *
     * @param nearbyPlaceDTO the nearbyPlaceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new nearbyPlaceDTO, or with status {@code 400 (Bad Request)} if the nearbyPlace has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/nearby-places")
    public ResponseEntity<NearbyPlaceDTO> createNearbyPlace(@Valid @RequestBody NearbyPlaceDTO nearbyPlaceDTO) throws URISyntaxException {
        log.debug("REST request to save NearbyPlace : {}", nearbyPlaceDTO);
        if (nearbyPlaceDTO.getId() != null) {
            throw new BadRequestAlertException("A new nearbyPlace cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NearbyPlaceDTO result = nearbyPlaceService.save(nearbyPlaceDTO);
        return ResponseEntity.created(new URI("/api/nearby-places/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /nearby-places} : Updates an existing nearbyPlace.
     *
     * @param nearbyPlaceDTO the nearbyPlaceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated nearbyPlaceDTO,
     * or with status {@code 400 (Bad Request)} if the nearbyPlaceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the nearbyPlaceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/nearby-places")
    public ResponseEntity<NearbyPlaceDTO> updateNearbyPlace(@Valid @RequestBody NearbyPlaceDTO nearbyPlaceDTO) throws URISyntaxException {
        log.debug("REST request to update NearbyPlace : {}", nearbyPlaceDTO);
        if (nearbyPlaceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NearbyPlaceDTO result = nearbyPlaceService.save(nearbyPlaceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, nearbyPlaceDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /nearby-places} : get all the nearbyPlaces.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of nearbyPlaces in body.
     */
    @GetMapping("/nearby-places")
    public ResponseEntity<List<NearbyPlaceDTO>> getAllNearbyPlaces(Pageable pageable) {
        log.debug("REST request to get a page of NearbyPlaces");
        Page<NearbyPlaceDTO> page = nearbyPlaceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /nearby-places/:id} : get the "id" nearbyPlace.
     *
     * @param id the id of the nearbyPlaceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the nearbyPlaceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/nearby-places/{id}")
    public ResponseEntity<NearbyPlaceDTO> getNearbyPlace(@PathVariable Long id) {
        log.debug("REST request to get NearbyPlace : {}", id);
        Optional<NearbyPlaceDTO> nearbyPlaceDTO = nearbyPlaceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(nearbyPlaceDTO);
    }

    /**
     * {@code DELETE  /nearby-places/:id} : delete the "id" nearbyPlace.
     *
     * @param id the id of the nearbyPlaceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/nearby-places/{id}")
    public ResponseEntity<Void> deleteNearbyPlace(@PathVariable Long id) {
        log.debug("REST request to delete NearbyPlace : {}", id);
        nearbyPlaceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
