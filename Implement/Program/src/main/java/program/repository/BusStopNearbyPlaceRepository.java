package program.repository;

import program.domain.BusStopNearbyPlace;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the BusStopNearbyPlace entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BusStopNearbyPlaceRepository extends JpaRepository<BusStopNearbyPlace, Long> {
}
