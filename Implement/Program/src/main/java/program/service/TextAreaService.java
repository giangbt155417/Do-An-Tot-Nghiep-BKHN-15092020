package program.service;

import program.service.dto.TextAreaDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link program.domain.TextArea}.
 */
public interface TextAreaService {

    /**
     * Save a textArea.
     *
     * @param textAreaDTO the entity to save.
     * @return the persisted entity.
     */
    TextAreaDTO save(TextAreaDTO textAreaDTO);

    /**
     * Get all the textAreas.
     *
     * @return the list of entities.
     */
    List<TextAreaDTO> findAll();


    /**
     * Get the "id" textArea.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TextAreaDTO> findOne(Long id);

    /**
     * Delete the "id" textArea.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
