package program.repository;

import program.domain.TextArea;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TextArea entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TextAreaRepository extends JpaRepository<TextArea, Long> {
}
