package program.service.impl;

import program.service.LCDContentGroupService;
import program.domain.LCDContentGroup;
import program.repository.LCDContentGroupRepository;
import program.service.dto.LCDContentGroupDTO;
import program.service.mapper.LCDContentGroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link LCDContentGroup}.
 */
@Service
@Transactional
public class LCDContentGroupServiceImpl implements LCDContentGroupService {

    private final Logger log = LoggerFactory.getLogger(LCDContentGroupServiceImpl.class);

    private final LCDContentGroupRepository lCDContentGroupRepository;

    private final LCDContentGroupMapper lCDContentGroupMapper;

    public LCDContentGroupServiceImpl(LCDContentGroupRepository lCDContentGroupRepository, LCDContentGroupMapper lCDContentGroupMapper) {
        this.lCDContentGroupRepository = lCDContentGroupRepository;
        this.lCDContentGroupMapper = lCDContentGroupMapper;
    }

    @Override
    public LCDContentGroupDTO save(LCDContentGroupDTO lCDContentGroupDTO) {
        log.debug("Request to save LCDContentGroup : {}", lCDContentGroupDTO);
        LCDContentGroup lCDContentGroup = lCDContentGroupMapper.toEntity(lCDContentGroupDTO);
        lCDContentGroup = lCDContentGroupRepository.save(lCDContentGroup);
        return lCDContentGroupMapper.toDto(lCDContentGroup);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<LCDContentGroupDTO> findAll(Pageable pageable) {
        log.debug("Request to get all LCDContentGroups");
        return lCDContentGroupRepository.findAll(pageable)
            .map(lCDContentGroupMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<LCDContentGroupDTO> findOne(Long id) {
        log.debug("Request to get LCDContentGroup : {}", id);
        return lCDContentGroupRepository.findById(id)
            .map(lCDContentGroupMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete LCDContentGroup : {}", id);
        lCDContentGroupRepository.deleteById(id);
    }
}
