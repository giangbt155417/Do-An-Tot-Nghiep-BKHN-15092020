package program.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import program.web.rest.TestUtil;

public class NearbyPlaceTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NearbyPlace.class);
        NearbyPlace nearbyPlace1 = new NearbyPlace();
        nearbyPlace1.setId(1L);
        NearbyPlace nearbyPlace2 = new NearbyPlace();
        nearbyPlace2.setId(nearbyPlace1.getId());
        assertThat(nearbyPlace1).isEqualTo(nearbyPlace2);
        nearbyPlace2.setId(2L);
        assertThat(nearbyPlace1).isNotEqualTo(nearbyPlace2);
        nearbyPlace1.setId(null);
        assertThat(nearbyPlace1).isNotEqualTo(nearbyPlace2);
    }
}
