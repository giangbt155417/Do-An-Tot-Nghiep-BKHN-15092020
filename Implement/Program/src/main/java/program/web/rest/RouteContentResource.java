package program.web.rest;

import program.domain.RouteContent;
import program.service.RouteContentService;
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
 * REST controller for managing {@link program.domain.RouteContent}.
 */
@RestController
@RequestMapping("/api")
public class RouteContentResource {

    private final Logger log = LoggerFactory.getLogger(RouteContentResource.class);

    private static final String ENTITY_NAME = "programRouteContent";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RouteContentService routeContentService;

    public RouteContentResource(RouteContentService routeContentService) {
        this.routeContentService = routeContentService;
    }

    /**
     * {@code POST  /route-contents} : Create a new routeContent.
     *
     * @param routeContent the routeContent to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new routeContent, or with status {@code 400 (Bad Request)} if the routeContent has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/route-contents")
    public ResponseEntity<RouteContent> createRouteContent(@Valid @RequestBody RouteContent routeContent) throws URISyntaxException {
        log.debug("REST request to save RouteContent : {}", routeContent);
        if (routeContent.getId() != null) {
            throw new BadRequestAlertException("A new routeContent cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RouteContent result = routeContentService.save(routeContent);
        return ResponseEntity.created(new URI("/api/route-contents/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /route-contents} : Updates an existing routeContent.
     *
     * @param routeContent the routeContent to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated routeContent,
     * or with status {@code 400 (Bad Request)} if the routeContent is not valid,
     * or with status {@code 500 (Internal Server Error)} if the routeContent couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/route-contents")
    public ResponseEntity<RouteContent> updateRouteContent(@Valid @RequestBody RouteContent routeContent) throws URISyntaxException {
        log.debug("REST request to update RouteContent : {}", routeContent);
        if (routeContent.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RouteContent result = routeContentService.save(routeContent);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, routeContent.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /route-contents} : get all the routeContents.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of routeContents in body.
     */
    @GetMapping("/route-contents")
    public List<RouteContent> getAllRouteContents() {
        log.debug("REST request to get all RouteContents");
        return routeContentService.findAll();
    }

    /**
     * {@code GET  /route-contents/:id} : get the "id" routeContent.
     *
     * @param id the id of the routeContent to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the routeContent, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/route-contents/{id}")
    public ResponseEntity<RouteContent> getRouteContent(@PathVariable Long id) {
        log.debug("REST request to get RouteContent : {}", id);
        Optional<RouteContent> routeContent = routeContentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(routeContent);
    }

    /**
     * {@code DELETE  /route-contents/:id} : delete the "id" routeContent.
     *
     * @param id the id of the routeContent to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/route-contents/{id}")
    public ResponseEntity<Void> deleteRouteContent(@PathVariable Long id) {
        log.debug("REST request to delete RouteContent : {}", id);
        routeContentService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
