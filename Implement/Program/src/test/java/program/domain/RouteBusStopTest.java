package program.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import program.web.rest.TestUtil;

public class RouteBusStopTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RouteBusStop.class);
        RouteBusStop routeBusStop1 = new RouteBusStop();
        routeBusStop1.setId(1L);
        RouteBusStop routeBusStop2 = new RouteBusStop();
        routeBusStop2.setId(routeBusStop1.getId());
        assertThat(routeBusStop1).isEqualTo(routeBusStop2);
        routeBusStop2.setId(2L);
        assertThat(routeBusStop1).isNotEqualTo(routeBusStop2);
        routeBusStop1.setId(null);
        assertThat(routeBusStop1).isNotEqualTo(routeBusStop2);
    }
}
