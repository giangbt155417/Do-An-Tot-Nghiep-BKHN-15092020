package program.repository;

import program.domain.LCDContent;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the LCDContent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LCDContentRepository extends JpaRepository<LCDContent, Long> {
}
