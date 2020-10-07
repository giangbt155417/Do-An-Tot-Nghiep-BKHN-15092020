package program.repository;

import program.domain.RouteContent;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the RouteContent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RouteContentRepository extends JpaRepository<RouteContent, Long> {
}
