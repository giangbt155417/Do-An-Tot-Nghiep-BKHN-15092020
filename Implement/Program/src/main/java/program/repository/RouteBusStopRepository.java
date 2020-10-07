package program.repository;

import program.domain.RouteBusStop;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the RouteBusStop entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RouteBusStopRepository extends JpaRepository<RouteBusStop, Long> {
}
