package program.service.impl;

import program.service.LCDContentService;
import program.domain.LCDContent;
import program.repository.LCDContentRepository;
import program.service.dto.LCDContentDTO;
import program.service.mapper.LCDContentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link LCDContent}.
 */
@Service
@Transactional
public class LCDContentServiceImpl implements LCDContentService {

    private final Logger log = LoggerFactory.getLogger(LCDContentServiceImpl.class);

    private final LCDContentRepository lCDContentRepository;

    private final LCDContentMapper lCDContentMapper;

    public LCDContentServiceImpl(LCDContentRepository lCDContentRepository, LCDContentMapper lCDContentMapper) {
        this.lCDContentRepository = lCDContentRepository;
        this.lCDContentMapper = lCDContentMapper;
    }

    @Override
    public LCDContentDTO save(LCDContentDTO lCDContentDTO) {
        log.debug("Request to save LCDContent : {}", lCDContentDTO);
        LCDContent lCDContent = lCDContentMapper.toEntity(lCDContentDTO);
        lCDContent = lCDContentRepository.save(lCDContent);
        return lCDContentMapper.toDto(lCDContent);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<LCDContentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all LCDContents");
        return lCDContentRepository.findAll(pageable)
            .map(lCDContentMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<LCDContentDTO> findOne(Long id) {
        log.debug("Request to get LCDContent : {}", id);
        return lCDContentRepository.findById(id)
            .map(lCDContentMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete LCDContent : {}", id);
        lCDContentRepository.deleteById(id);
    }
}
