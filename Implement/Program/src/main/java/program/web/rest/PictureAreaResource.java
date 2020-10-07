package program.web.rest;

import program.service.PictureAreaService;
import program.web.rest.errors.BadRequestAlertException;
import program.service.dto.PictureAreaDTO;

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
 * REST controller for managing {@link program.domain.PictureArea}.
 */
@RestController
@RequestMapping("/api")
public class PictureAreaResource {

    private final Logger log = LoggerFactory.getLogger(PictureAreaResource.class);

    private static final String ENTITY_NAME = "programPictureArea";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PictureAreaService pictureAreaService;

    public PictureAreaResource(PictureAreaService pictureAreaService) {
        this.pictureAreaService = pictureAreaService;
    }

    /**
     * {@code POST  /picture-areas} : Create a new pictureArea.
     *
     * @param pictureAreaDTO the pictureAreaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pictureAreaDTO, or with status {@code 400 (Bad Request)} if the pictureArea has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/picture-areas")
    public ResponseEntity<PictureAreaDTO> createPictureArea(@Valid @RequestBody PictureAreaDTO pictureAreaDTO) throws URISyntaxException {
        log.debug("REST request to save PictureArea : {}", pictureAreaDTO);
        if (pictureAreaDTO.getId() != null) {
            throw new BadRequestAlertException("A new pictureArea cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PictureAreaDTO result = pictureAreaService.save(pictureAreaDTO);
        return ResponseEntity.created(new URI("/api/picture-areas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /picture-areas} : Updates an existing pictureArea.
     *
     * @param pictureAreaDTO the pictureAreaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pictureAreaDTO,
     * or with status {@code 400 (Bad Request)} if the pictureAreaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pictureAreaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/picture-areas")
    public ResponseEntity<PictureAreaDTO> updatePictureArea(@Valid @RequestBody PictureAreaDTO pictureAreaDTO) throws URISyntaxException {
        log.debug("REST request to update PictureArea : {}", pictureAreaDTO);
        if (pictureAreaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PictureAreaDTO result = pictureAreaService.save(pictureAreaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, pictureAreaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /picture-areas} : get all the pictureAreas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pictureAreas in body.
     */
    @GetMapping("/picture-areas")
    public List<PictureAreaDTO> getAllPictureAreas() {
        log.debug("REST request to get all PictureAreas");
        return pictureAreaService.findAll();
    }

    /**
     * {@code GET  /picture-areas/:id} : get the "id" pictureArea.
     *
     * @param id the id of the pictureAreaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pictureAreaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/picture-areas/{id}")
    public ResponseEntity<PictureAreaDTO> getPictureArea(@PathVariable Long id) {
        log.debug("REST request to get PictureArea : {}", id);
        Optional<PictureAreaDTO> pictureAreaDTO = pictureAreaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pictureAreaDTO);
    }

    /**
     * {@code DELETE  /picture-areas/:id} : delete the "id" pictureArea.
     *
     * @param id the id of the pictureAreaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/picture-areas/{id}")
    public ResponseEntity<Void> deletePictureArea(@PathVariable Long id) {
        log.debug("REST request to delete PictureArea : {}", id);
        pictureAreaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
