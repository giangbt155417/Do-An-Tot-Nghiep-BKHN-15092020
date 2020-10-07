package program.service.mapper;


import program.domain.*;
import program.service.dto.NearbyPlaceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link NearbyPlace} and its DTO {@link NearbyPlaceDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NearbyPlaceMapper extends EntityMapper<NearbyPlaceDTO, NearbyPlace> {



    default NearbyPlace fromId(Long id) {
        if (id == null) {
            return null;
        }
        NearbyPlace nearbyPlace = new NearbyPlace();
        nearbyPlace.setId(id);
        return nearbyPlace;
    }
}
