package program.repository;

import program.domain.Route;
import program.service.dto.RouteDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Route entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

	@Query(value="SELECT route.id, route.name, route.route_no, route.suffix, route.project_id, route.created_date, route.description "
			+ "FROM tbl_route route "
			+ "WHERE route.project_id = ?1", 
			countQuery = "SELECT count(route.id) "
					+ "FROM tbl_route route "
					+ "WHERE route.project_id = ?1" ,nativeQuery = true)
	Page<Route> findRoutesByProjectId(Long projectId, Pageable pageable);
	
	@Query(value="SELECT count(route.id) "
			+ "FROM tbl_route route "
			+ "WHERE route.project_id = ?1",nativeQuery = true)
	int countRoutesByProjectId(Long projectId);

}
