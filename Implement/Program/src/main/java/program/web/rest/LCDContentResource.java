package program.web.rest;

import program.service.LCDContentService;
import program.web.rest.errors.BadRequestAlertException;
import program.service.dto.LCDContentDTO;

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
 * REST controller for managing {@link program.domain.LCDContent}.
 */
@RestController
@RequestMapping("/api")
public class LCDContentResource {

    private final Logger log = LoggerFactory.getLogger(LCDContentResource.class);

    private static final String ENTITY_NAME = "programLcdContent";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LCDContentService lCDContentService;

    public LCDContentResource(LCDContentService lCDContentService) {
        this.lCDContentService = lCDContentService;
    }

    /**
     * {@code POST  /lcd-contents} : Create a new lCDContent.
     *
     * @param lCDContentDTO the lCDContentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new lCDContentDTO, or with status {@code 400 (Bad Request)} if the lCDContent has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/lcd-contents")
    public ResponseEntity<LCDContentDTO> createLCDContent(@Valid @RequestBody LCDContentDTO lCDContentDTO) throws URISyntaxException {
        log.debug("REST request to save LCDContent : {}", lCDContentDTO);
        if (lCDContentDTO.getId() != null) {
            throw new BadRequestAlertException("A new lCDContent cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LCDContentDTO result = lCDContentService.save(lCDContentDTO);
        return ResponseEntity.created(new URI("/api/lcd-contents/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /lcd-contents} : Updates an existing lCDContent.
     *
     * @param lCDContentDTO the lCDContentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated lCDContentDTO,
     * or with status {@code 400 (Bad Request)} if the lCDContentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the lCDContentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/lcd-contents")
    public ResponseEntity<LCDContentDTO> updateLCDContent(@Valid @RequestBody LCDContentDTO lCDContentDTO) throws URISyntaxException {
        log.debug("REST request to update LCDContent : {}", lCDContentDTO);
        if (lCDContentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LCDContentDTO result = lCDContentService.save(lCDContentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, lCDContentDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /lcd-contents} : get all the lCDContents.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of lCDContents in body.
     */
    @GetMapping("/lcd-contents")
    public ResponseEntity<List<LCDContentDTO>> getAllLCDContents(Pageable pageable) {
        log.debug("REST request to get a page of LCDContents");
        Page<LCDContentDTO> page = lCDContentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /lcd-contents/:id} : get the "id" lCDContent.
     *
     * @param id the id of the lCDContentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the lCDContentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/lcd-contents/{id}")
    public ResponseEntity<LCDContentDTO> getLCDContent(@PathVariable Long id) {
        log.debug("REST request to get LCDContent : {}", id);
        Optional<LCDContentDTO> lCDContentDTO = lCDContentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(lCDContentDTO);
    }

    /**
     * {@code DELETE  /lcd-contents/:id} : delete the "id" lCDContent.
     *
     * @param id the id of the lCDContentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/lcd-contents/{id}")
    public ResponseEntity<Void> deleteLCDContent(@PathVariable Long id) {
        log.debug("REST request to delete LCDContent : {}", id);
        lCDContentService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
