package program.service;

import program.service.dto.LCDContentDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link program.domain.LCDContent}.
 */
public interface LCDContentService {

    /**
     * Save a lCDContent.
     *
     * @param lCDContentDTO the entity to save.
     * @return the persisted entity.
     */
    LCDContentDTO save(LCDContentDTO lCDContentDTO);

    /**
     * Get all the lCDContents.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<LCDContentDTO> findAll(Pageable pageable);


    /**
     * Get the "id" lCDContent.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LCDContentDTO> findOne(Long id);

    /**
     * Delete the "id" lCDContent.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
