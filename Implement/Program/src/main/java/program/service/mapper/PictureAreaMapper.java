package program.service.mapper;


import program.domain.*;
import program.service.dto.PictureAreaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PictureArea} and its DTO {@link PictureAreaDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PictureAreaMapper extends EntityMapper<PictureAreaDTO, PictureArea> {



    default PictureArea fromId(Long id) {
        if (id == null) {
            return null;
        }
        PictureArea pictureArea = new PictureArea();
        pictureArea.setId(id);
        return pictureArea;
    }
}
