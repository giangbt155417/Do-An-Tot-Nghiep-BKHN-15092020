package program.web.rest;

import program.service.TextAreaService;
import program.web.rest.errors.BadRequestAlertException;
import program.service.dto.TextAreaDTO;

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
 * REST controller for managing {@link program.domain.TextArea}.
 */
@RestController
@RequestMapping("/api")
public class TextAreaResource {

    private final Logger log = LoggerFactory.getLogger(TextAreaResource.class);

    private static final String ENTITY_NAME = "programTextArea";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TextAreaService textAreaService;

    public TextAreaResource(TextAreaService textAreaService) {
        this.textAreaService = textAreaService;
    }

    /**
     * {@code POST  /text-areas} : Create a new textArea.
     *
     * @param textAreaDTO the textAreaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new textAreaDTO, or with status {@code 400 (Bad Request)} if the textArea has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/text-areas")
    public ResponseEntity<TextAreaDTO> createTextArea(@Valid @RequestBody TextAreaDTO textAreaDTO) throws URISyntaxException {
        log.debug("REST request to save TextArea : {}", textAreaDTO);
        if (textAreaDTO.getId() != null) {
            throw new BadRequestAlertException("A new textArea cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TextAreaDTO result = textAreaService.save(textAreaDTO);
        return ResponseEntity.created(new URI("/api/text-areas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /text-areas} : Updates an existing textArea.
     *
     * @param textAreaDTO the textAreaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated textAreaDTO,
     * or with status {@code 400 (Bad Request)} if the textAreaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the textAreaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/text-areas")
    public ResponseEntity<TextAreaDTO> updateTextArea(@Valid @RequestBody TextAreaDTO textAreaDTO) throws URISyntaxException {
        log.debug("REST request to update TextArea : {}", textAreaDTO);
        if (textAreaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TextAreaDTO result = textAreaService.save(textAreaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, textAreaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /text-areas} : get all the textAreas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of textAreas in body.
     */
    @GetMapping("/text-areas")
    public List<TextAreaDTO> getAllTextAreas() {
        log.debug("REST request to get all TextAreas");
        return textAreaService.findAll();
    }

    /**
     * {@code GET  /text-areas/:id} : get the "id" textArea.
     *
     * @param id the id of the textAreaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the textAreaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/text-areas/{id}")
    public ResponseEntity<TextAreaDTO> getTextArea(@PathVariable Long id) {
        log.debug("REST request to get TextArea : {}", id);
        Optional<TextAreaDTO> textAreaDTO = textAreaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(textAreaDTO);
    }

    /**
     * {@code DELETE  /text-areas/:id} : delete the "id" textArea.
     *
     * @param id the id of the textAreaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/text-areas/{id}")
    public ResponseEntity<Void> deleteTextArea(@PathVariable Long id) {
        log.debug("REST request to delete TextArea : {}", id);
        textAreaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
