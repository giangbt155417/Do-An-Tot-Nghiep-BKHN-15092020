package program.service.mapper;


import program.domain.*;
import program.service.dto.BusStopDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link BusStop} and its DTO {@link BusStopDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BusStopMapper extends EntityMapper<BusStopDTO, BusStop> {



    default BusStop fromId(Long id) {
        if (id == null) {
            return null;
        }
        BusStop busStop = new BusStop();
        busStop.setId(id);
        return busStop;
    }
}
