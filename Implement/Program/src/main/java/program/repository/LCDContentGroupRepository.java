package program.repository;

import program.domain.LCDContentGroup;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the LCDContentGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LCDContentGroupRepository extends JpaRepository<LCDContentGroup, Long> {
}
