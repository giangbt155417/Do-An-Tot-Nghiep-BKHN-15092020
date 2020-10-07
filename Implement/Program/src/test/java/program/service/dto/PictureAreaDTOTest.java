package program.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import program.web.rest.TestUtil;

public class PictureAreaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PictureAreaDTO.class);
        PictureAreaDTO pictureAreaDTO1 = new PictureAreaDTO();
        pictureAreaDTO1.setId(1L);
        PictureAreaDTO pictureAreaDTO2 = new PictureAreaDTO();
        assertThat(pictureAreaDTO1).isNotEqualTo(pictureAreaDTO2);
        pictureAreaDTO2.setId(pictureAreaDTO1.getId());
        assertThat(pictureAreaDTO1).isEqualTo(pictureAreaDTO2);
        pictureAreaDTO2.setId(2L);
        assertThat(pictureAreaDTO1).isNotEqualTo(pictureAreaDTO2);
        pictureAreaDTO1.setId(null);
        assertThat(pictureAreaDTO1).isNotEqualTo(pictureAreaDTO2);
    }
}
