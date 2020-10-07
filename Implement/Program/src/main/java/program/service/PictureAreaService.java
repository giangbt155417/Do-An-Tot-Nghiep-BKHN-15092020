package program.service;

import program.service.dto.PictureAreaDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link program.domain.PictureArea}.
 */
public interface PictureAreaService {

    /**
     * Save a pictureArea.
     *
     * @param pictureAreaDTO the entity to save.
     * @return the persisted entity.
     */
    PictureAreaDTO save(PictureAreaDTO pictureAreaDTO);

    /**
     * Get all the pictureAreas.
     *
     * @return the list of entities.
     */
    List<PictureAreaDTO> findAll();


    /**
     * Get the "id" pictureArea.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PictureAreaDTO> findOne(Long id);

    /**
     * Delete the "id" pictureArea.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
