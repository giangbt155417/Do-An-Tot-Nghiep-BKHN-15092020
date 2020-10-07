package program.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import program.web.rest.TestUtil;

public class BusStopTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BusStop.class);
        BusStop busStop1 = new BusStop();
        busStop1.setId(1L);
        BusStop busStop2 = new BusStop();
        busStop2.setId(busStop1.getId());
        assertThat(busStop1).isEqualTo(busStop2);
        busStop2.setId(2L);
        assertThat(busStop1).isNotEqualTo(busStop2);
        busStop1.setId(null);
        assertThat(busStop1).isNotEqualTo(busStop2);
    }
}
