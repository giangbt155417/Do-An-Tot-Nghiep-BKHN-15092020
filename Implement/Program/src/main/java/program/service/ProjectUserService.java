package program.service;

import program.domain.ProjectUser;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ProjectUser}.
 */
public interface ProjectUserService {

    /**
     * Save a projectUser.
     *
     * @param projectUser the entity to save.
     * @return the persisted entity.
     */
    ProjectUser save(ProjectUser projectUser);

    /**
     * Get all the projectUsers.
     *
     * @return the list of entities.
     */
    List<ProjectUser> findAll();


    /**
     * Get the "id" projectUser.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProjectUser> findOne(Long id);

    /**
     * Delete the "id" projectUser.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
