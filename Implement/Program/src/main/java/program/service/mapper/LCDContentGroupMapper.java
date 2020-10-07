package program.service.mapper;


import program.domain.*;
import program.service.dto.LCDContentGroupDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link LCDContentGroup} and its DTO {@link LCDContentGroupDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LCDContentGroupMapper extends EntityMapper<LCDContentGroupDTO, LCDContentGroup> {



    default LCDContentGroup fromId(Long id) {
        if (id == null) {
            return null;
        }
        LCDContentGroup lCDContentGroup = new LCDContentGroup();
        lCDContentGroup.setId(id);
        return lCDContentGroup;
    }
}
