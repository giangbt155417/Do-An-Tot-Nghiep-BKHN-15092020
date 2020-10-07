package program.repository;

import program.domain.Project;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Project entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

	@Query(value="SELECT prj.id, prj.name, prj.owner, prj.created_date, prj.expiry_date, prj.description "
			+ "FROM tbl_project prj "
			+ "LEFT JOIN tbl_project_user prju "
			+ "ON prj.id = prju.project_id "
			+ "WHERE prju.user_id = ?1", 
			countQuery = "SELECT count(prj.id) "
					+ "FROM tbl_project prj "
					+ "LEFT JOIN tbl_project_user prju "
					+ "ON prj.id = prju.project_id "
					+ "WHERE prju.user_id = ?1" ,nativeQuery = true)
	Page<Project> findProjectsByUserId(Long userId, Pageable pageable);
	
	@Query(value="SELECT count(prj.id) "
			+ "FROM tbl_project prj "
			+ "LEFT JOIN tbl_project_user prju "
			+ "ON prj.id = prju.project_id "
			+ "WHERE prju.user_id = ?1",nativeQuery = true)
	int countProjectsByUserId(Long userId);
}
