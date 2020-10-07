package program.service.impl;

import program.service.RouteService;
import program.domain.Route;
import program.repository.RouteRepository;
import program.service.dto.ProjectDTO;
import program.service.dto.RouteDTO;
import program.service.mapper.RouteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Route}.
 */
@Service
@Transactional
public class RouteServiceImpl implements RouteService {

    private final Logger log = LoggerFactory.getLogger(RouteServiceImpl.class);

    private final RouteRepository routeRepository;

    private final RouteMapper routeMapper;

    public RouteServiceImpl(RouteRepository routeRepository, RouteMapper routeMapper) {
        this.routeRepository = routeRepository;
        this.routeMapper = routeMapper;
    }

    @Override
    public RouteDTO save(RouteDTO routeDTO) {
        log.debug("Request to save Route : {}", routeDTO);
        Route route = routeMapper.toEntity(routeDTO);
        route = routeRepository.save(route);
        return routeMapper.toDto(route);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RouteDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Routes");
        return routeRepository.findAll(pageable)
            .map(routeMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<RouteDTO> findOne(Long id) {
        log.debug("Request to get Route : {}", id);
        return routeRepository.findById(id)
            .map(routeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Route : {}", id);
        routeRepository.deleteById(id);
    }

	@Override
	public List<RouteDTO> findRoutesByProjectId(Long projectId, int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber - 1, 2, Sort.by("id"));
		List<RouteDTO> routes = routeMapper.toDto(routeRepository.findRoutesByProjectId(projectId, pageable).getContent());
		return routes;
	}

	@Override
	public int countRoutesByProjectId(Long projectId) {
		return routeRepository.countRoutesByProjectId(projectId);
	}
}
