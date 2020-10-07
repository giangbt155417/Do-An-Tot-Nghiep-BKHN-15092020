package program.service.impl;

import program.service.RouteContentService;
import program.domain.RouteContent;
import program.repository.RouteContentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link RouteContent}.
 */
@Service
@Transactional
public class RouteContentServiceImpl implements RouteContentService {

    private final Logger log = LoggerFactory.getLogger(RouteContentServiceImpl.class);

    private final RouteContentRepository routeContentRepository;

    public RouteContentServiceImpl(RouteContentRepository routeContentRepository) {
        this.routeContentRepository = routeContentRepository;
    }

    @Override
    public RouteContent save(RouteContent routeContent) {
        log.debug("Request to save RouteContent : {}", routeContent);
        return routeContentRepository.save(routeContent);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RouteContent> findAll() {
        log.debug("Request to get all RouteContents");
        return routeContentRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<RouteContent> findOne(Long id) {
        log.debug("Request to get RouteContent : {}", id);
        return routeContentRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete RouteContent : {}", id);
        routeContentRepository.deleteById(id);
    }
}
