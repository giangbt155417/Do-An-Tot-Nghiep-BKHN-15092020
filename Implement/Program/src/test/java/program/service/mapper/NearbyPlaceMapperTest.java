package program.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class NearbyPlaceMapperTest {

    private NearbyPlaceMapper nearbyPlaceMapper;

    @BeforeEach
    public void setUp() {
        nearbyPlaceMapper = new NearbyPlaceMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(nearbyPlaceMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(nearbyPlaceMapper.fromId(null)).isNull();
    }
}
