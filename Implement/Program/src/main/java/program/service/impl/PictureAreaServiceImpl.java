package program.service.impl;

import program.service.PictureAreaService;
import program.domain.PictureArea;
import program.repository.PictureAreaRepository;
import program.service.dto.PictureAreaDTO;
import program.service.mapper.PictureAreaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link PictureArea}.
 */
@Service
@Transactional
public class PictureAreaServiceImpl implements PictureAreaService {

    private final Logger log = LoggerFactory.getLogger(PictureAreaServiceImpl.class);

    private final PictureAreaRepository pictureAreaRepository;

    private final PictureAreaMapper pictureAreaMapper;

    public PictureAreaServiceImpl(PictureAreaRepository pictureAreaRepository, PictureAreaMapper pictureAreaMapper) {
        this.pictureAreaRepository = pictureAreaRepository;
        this.pictureAreaMapper = pictureAreaMapper;
    }

    @Override
    public PictureAreaDTO save(PictureAreaDTO pictureAreaDTO) {
        log.debug("Request to save PictureArea : {}", pictureAreaDTO);
        PictureArea pictureArea = pictureAreaMapper.toEntity(pictureAreaDTO);
        pictureArea = pictureAreaRepository.save(pictureArea);
        return pictureAreaMapper.toDto(pictureArea);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PictureAreaDTO> findAll() {
        log.debug("Request to get all PictureAreas");
        return pictureAreaRepository.findAll().stream()
            .map(pictureAreaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<PictureAreaDTO> findOne(Long id) {
        log.debug("Request to get PictureArea : {}", id);
        return pictureAreaRepository.findById(id)
            .map(pictureAreaMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PictureArea : {}", id);
        pictureAreaRepository.deleteById(id);
    }
}
