package program.repository;

import program.domain.NearbyPlace;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the NearbyPlace entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NearbyPlaceRepository extends JpaRepository<NearbyPlace, Long> {
}
