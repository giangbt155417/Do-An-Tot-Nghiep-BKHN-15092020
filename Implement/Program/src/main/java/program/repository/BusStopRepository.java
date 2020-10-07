package program.repository;

import program.domain.BusStop;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the BusStop entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BusStopRepository extends JpaRepository<BusStop, Long> {
}
