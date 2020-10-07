package program.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class BusStopMapperTest {

    private BusStopMapper busStopMapper;

    @BeforeEach
    public void setUp() {
        busStopMapper = new BusStopMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(busStopMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(busStopMapper.fromId(null)).isNull();
    }
}
