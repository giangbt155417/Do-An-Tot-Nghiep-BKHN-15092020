package program.service.impl;

import program.service.ProjectUserService;
import program.domain.ProjectUser;
import program.repository.ProjectUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link ProjectUser}.
 */
@Service
@Transactional
public class ProjectUserServiceImpl implements ProjectUserService {

    private final Logger log = LoggerFactory.getLogger(ProjectUserServiceImpl.class);

    private final ProjectUserRepository projectUserRepository;

    public ProjectUserServiceImpl(ProjectUserRepository projectUserRepository) {
        this.projectUserRepository = projectUserRepository;
    }

    @Override
    public ProjectUser save(ProjectUser projectUser) {
        log.debug("Request to save ProjectUser : {}", projectUser);
        return projectUserRepository.save(projectUser);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectUser> findAll() {
        log.debug("Request to get all ProjectUsers");
        return projectUserRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ProjectUser> findOne(Long id) {
        log.debug("Request to get ProjectUser : {}", id);
        return projectUserRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProjectUser : {}", id);
        projectUserRepository.deleteById(id);
    }
}
