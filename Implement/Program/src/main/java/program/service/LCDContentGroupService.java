package program.service;

import program.service.dto.LCDContentGroupDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link program.domain.LCDContentGroup}.
 */
public interface LCDContentGroupService {

    /**
     * Save a lCDContentGroup.
     *
     * @param lCDContentGroupDTO the entity to save.
     * @return the persisted entity.
     */
    LCDContentGroupDTO save(LCDContentGroupDTO lCDContentGroupDTO);

    /**
     * Get all the lCDContentGroups.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<LCDContentGroupDTO> findAll(Pageable pageable);


    /**
     * Get the "id" lCDContentGroup.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LCDContentGroupDTO> findOne(Long id);

    /**
     * Delete the "id" lCDContentGroup.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
