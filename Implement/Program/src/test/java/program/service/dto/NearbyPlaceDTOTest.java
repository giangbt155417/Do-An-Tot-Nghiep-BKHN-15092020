package program.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import program.web.rest.TestUtil;

public class NearbyPlaceDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NearbyPlaceDTO.class);
        NearbyPlaceDTO nearbyPlaceDTO1 = new NearbyPlaceDTO();
        nearbyPlaceDTO1.setId(1L);
        NearbyPlaceDTO nearbyPlaceDTO2 = new NearbyPlaceDTO();
        assertThat(nearbyPlaceDTO1).isNotEqualTo(nearbyPlaceDTO2);
        nearbyPlaceDTO2.setId(nearbyPlaceDTO1.getId());
        assertThat(nearbyPlaceDTO1).isEqualTo(nearbyPlaceDTO2);
        nearbyPlaceDTO2.setId(2L);
        assertThat(nearbyPlaceDTO1).isNotEqualTo(nearbyPlaceDTO2);
        nearbyPlaceDTO1.setId(null);
        assertThat(nearbyPlaceDTO1).isNotEqualTo(nearbyPlaceDTO2);
    }
}
