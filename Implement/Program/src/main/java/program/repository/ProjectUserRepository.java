package program.repository;

import program.domain.ProjectUser;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ProjectUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProjectUserRepository extends JpaRepository<ProjectUser, Long> {

	void deleteByUserId(Long userId);
}
