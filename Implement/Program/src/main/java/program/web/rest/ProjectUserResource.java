package program.web.rest;

import program.domain.ProjectUser;
import program.service.ProjectUserService;
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
 * REST controller for managing {@link program.domain.ProjectUser}.
 */
@RestController
@RequestMapping("/api")
public class ProjectUserResource {

    private final Logger log = LoggerFactory.getLogger(ProjectUserResource.class);

    private static final String ENTITY_NAME = "programProjectUser";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProjectUserService projectUserService;

    public ProjectUserResource(ProjectUserService projectUserService) {
        this.projectUserService = projectUserService;
    }

    /**
     * {@code POST  /project-users} : Create a new projectUser.
     *
     * @param projectUser the projectUser to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new projectUser, or with status {@code 400 (Bad Request)} if the projectUser has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/project-users")
    public ResponseEntity<ProjectUser> createProjectUser(@Valid @RequestBody ProjectUser projectUser) throws URISyntaxException {
        log.debug("REST request to save ProjectUser : {}", projectUser);
        if (projectUser.getId() != null) {
            throw new BadRequestAlertException("A new projectUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProjectUser result = projectUserService.save(projectUser);
        return ResponseEntity.created(new URI("/api/project-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /project-users} : Updates an existing projectUser.
     *
     * @param projectUser the projectUser to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectUser,
     * or with status {@code 400 (Bad Request)} if the projectUser is not valid,
     * or with status {@code 500 (Internal Server Error)} if the projectUser couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/project-users")
    public ResponseEntity<ProjectUser> updateProjectUser(@Valid @RequestBody ProjectUser projectUser) throws URISyntaxException {
        log.debug("REST request to update ProjectUser : {}", projectUser);
        if (projectUser.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProjectUser result = projectUserService.save(projectUser);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, projectUser.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /project-users} : get all the projectUsers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of projectUsers in body.
     */
    @GetMapping("/project-users")
    public List<ProjectUser> getAllProjectUsers() {
        log.debug("REST request to get all ProjectUsers");
        return projectUserService.findAll();
    }

    /**
     * {@code GET  /project-users/:id} : get the "id" projectUser.
     *
     * @param id the id of the projectUser to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the projectUser, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/project-users/{id}")
    public ResponseEntity<ProjectUser> getProjectUser(@PathVariable Long id) {
        log.debug("REST request to get ProjectUser : {}", id);
        Optional<ProjectUser> projectUser = projectUserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(projectUser);
    }

    /**
     * {@code DELETE  /project-users/:id} : delete the "id" projectUser.
     *
     * @param id the id of the projectUser to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/project-users/{id}")
    public ResponseEntity<Void> deleteProjectUser(@PathVariable Long id) {
        log.debug("REST request to delete ProjectUser : {}", id);
        projectUserService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
