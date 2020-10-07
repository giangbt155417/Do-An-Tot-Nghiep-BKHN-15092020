package program.service.impl;

import program.service.TextAreaService;
import program.domain.TextArea;
import program.repository.TextAreaRepository;
import program.service.dto.TextAreaDTO;
import program.service.mapper.TextAreaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link TextArea}.
 */
@Service
@Transactional
public class TextAreaServiceImpl implements TextAreaService {

    private final Logger log = LoggerFactory.getLogger(TextAreaServiceImpl.class);

    private final TextAreaRepository textAreaRepository;

    private final TextAreaMapper textAreaMapper;

    public TextAreaServiceImpl(TextAreaRepository textAreaRepository, TextAreaMapper textAreaMapper) {
        this.textAreaRepository = textAreaRepository;
        this.textAreaMapper = textAreaMapper;
    }

    @Override
    public TextAreaDTO save(TextAreaDTO textAreaDTO) {
        log.debug("Request to save TextArea : {}", textAreaDTO);
        TextArea textArea = textAreaMapper.toEntity(textAreaDTO);
        textArea = textAreaRepository.save(textArea);
        return textAreaMapper.toDto(textArea);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TextAreaDTO> findAll() {
        log.debug("Request to get all TextAreas");
        return textAreaRepository.findAll().stream()
            .map(textAreaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<TextAreaDTO> findOne(Long id) {
        log.debug("Request to get TextArea : {}", id);
        return textAreaRepository.findById(id)
            .map(textAreaMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TextArea : {}", id);
        textAreaRepository.deleteById(id);
    }
}
