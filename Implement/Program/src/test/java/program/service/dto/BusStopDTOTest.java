package program.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import program.web.rest.TestUtil;

public class BusStopDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BusStopDTO.class);
        BusStopDTO busStopDTO1 = new BusStopDTO();
        busStopDTO1.setId(1L);
        BusStopDTO busStopDTO2 = new BusStopDTO();
        assertThat(busStopDTO1).isNotEqualTo(busStopDTO2);
        busStopDTO2.setId(busStopDTO1.getId());
        assertThat(busStopDTO1).isEqualTo(busStopDTO2);
        busStopDTO2.setId(2L);
        assertThat(busStopDTO1).isNotEqualTo(busStopDTO2);
        busStopDTO1.setId(null);
        assertThat(busStopDTO1).isNotEqualTo(busStopDTO2);
    }
}
