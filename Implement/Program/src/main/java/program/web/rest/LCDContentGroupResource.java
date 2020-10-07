package program.web.rest;

import program.service.LCDContentGroupService;
import program.web.rest.errors.BadRequestAlertException;
import program.service.dto.LCDContentGroupDTO;

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
 * REST controller for managing {@link program.domain.LCDContentGroup}.
 */
@RestController
@RequestMapping("/api")
public class LCDContentGroupResource {

    private final Logger log = LoggerFactory.getLogger(LCDContentGroupResource.class);

    private static final String ENTITY_NAME = "programLcdContentGroup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LCDContentGroupService lCDContentGroupService;

    public LCDContentGroupResource(LCDContentGroupService lCDContentGroupService) {
        this.lCDContentGroupService = lCDContentGroupService;
    }

    /**
     * {@code POST  /lcd-content-groups} : Create a new lCDContentGroup.
     *
     * @param lCDContentGroupDTO the lCDContentGroupDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new lCDContentGroupDTO, or with status {@code 400 (Bad Request)} if the lCDContentGroup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/lcd-content-groups")
    public ResponseEntity<LCDContentGroupDTO> createLCDContentGroup(@Valid @RequestBody LCDContentGroupDTO lCDContentGroupDTO) throws URISyntaxException {
        log.debug("REST request to save LCDContentGroup : {}", lCDContentGroupDTO);
        if (lCDContentGroupDTO.getId() != null) {
            throw new BadRequestAlertException("A new lCDContentGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LCDContentGroupDTO result = lCDContentGroupService.save(lCDContentGroupDTO);
        return ResponseEntity.created(new URI("/api/lcd-content-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /lcd-content-groups} : Updates an existing lCDContentGroup.
     *
     * @param lCDContentGroupDTO the lCDContentGroupDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated lCDContentGroupDTO,
     * or with status {@code 400 (Bad Request)} if the lCDContentGroupDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the lCDContentGroupDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/lcd-content-groups")
    public ResponseEntity<LCDContentGroupDTO> updateLCDContentGroup(@Valid @RequestBody LCDContentGroupDTO lCDContentGroupDTO) throws URISyntaxException {
        log.debug("REST request to update LCDContentGroup : {}", lCDContentGroupDTO);
        if (lCDContentGroupDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LCDContentGroupDTO result = lCDContentGroupService.save(lCDContentGroupDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, lCDContentGroupDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /lcd-content-groups} : get all the lCDContentGroups.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of lCDContentGroups in body.
     */
    @GetMapping("/lcd-content-groups")
    public ResponseEntity<List<LCDContentGroupDTO>> getAllLCDContentGroups(Pageable pageable) {
        log.debug("REST request to get a page of LCDContentGroups");
        Page<LCDContentGroupDTO> page = lCDContentGroupService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /lcd-content-groups/:id} : get the "id" lCDContentGroup.
     *
     * @param id the id of the lCDContentGroupDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the lCDContentGroupDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/lcd-content-groups/{id}")
    public ResponseEntity<LCDContentGroupDTO> getLCDContentGroup(@PathVariable Long id) {
        log.debug("REST request to get LCDContentGroup : {}", id);
        Optional<LCDContentGroupDTO> lCDContentGroupDTO = lCDContentGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(lCDContentGroupDTO);
    }

    /**
     * {@code DELETE  /lcd-content-groups/:id} : delete the "id" lCDContentGroup.
     *
     * @param id the id of the lCDContentGroupDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/lcd-content-groups/{id}")
    public ResponseEntity<Void> deleteLCDContentGroup(@PathVariable Long id) {
        log.debug("REST request to delete LCDContentGroup : {}", id);
        lCDContentGroupService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
