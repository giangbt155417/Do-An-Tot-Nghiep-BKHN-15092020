package program.service.mapper;


import program.domain.*;
import program.service.dto.TextAreaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TextArea} and its DTO {@link TextAreaDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TextAreaMapper extends EntityMapper<TextAreaDTO, TextArea> {



    default TextArea fromId(Long id) {
        if (id == null) {
            return null;
        }
        TextArea textArea = new TextArea();
        textArea.setId(id);
        return textArea;
    }
}
