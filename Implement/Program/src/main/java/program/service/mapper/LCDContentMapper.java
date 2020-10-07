package program.service.mapper;


import program.domain.*;
import program.service.dto.LCDContentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link LCDContent} and its DTO {@link LCDContentDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LCDContentMapper extends EntityMapper<LCDContentDTO, LCDContent> {



    default LCDContent fromId(Long id) {
        if (id == null) {
            return null;
        }
        LCDContent lCDContent = new LCDContent();
        lCDContent.setId(id);
        return lCDContent;
    }
}
