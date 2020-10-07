package program.repository;

import program.domain.PictureArea;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PictureArea entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PictureAreaRepository extends JpaRepository<PictureArea, Long> {
}
