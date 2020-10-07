package program.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import program.web.rest.TestUtil;

public class BusStopNearbyPlaceTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BusStopNearbyPlace.class);
        BusStopNearbyPlace busStopNearbyPlace1 = new BusStopNearbyPlace();
        busStopNearbyPlace1.setId(1L);
        BusStopNearbyPlace busStopNearbyPlace2 = new BusStopNearbyPlace();
        busStopNearbyPlace2.setId(busStopNearbyPlace1.getId());
        assertThat(busStopNearbyPlace1).isEqualTo(busStopNearbyPlace2);
        busStopNearbyPlace2.setId(2L);
        assertThat(busStopNearbyPlace1).isNotEqualTo(busStopNearbyPlace2);
        busStopNearbyPlace1.setId(null);
        assertThat(busStopNearbyPlace1).isNotEqualTo(busStopNearbyPlace2);
    }
}
