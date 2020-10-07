package program.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import program.web.rest.TestUtil;

public class PictureAreaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PictureArea.class);
        PictureArea pictureArea1 = new PictureArea();
        pictureArea1.setId(1L);
        PictureArea pictureArea2 = new PictureArea();
        pictureArea2.setId(pictureArea1.getId());
        assertThat(pictureArea1).isEqualTo(pictureArea2);
        pictureArea2.setId(2L);
        assertThat(pictureArea1).isNotEqualTo(pictureArea2);
        pictureArea1.setId(null);
        assertThat(pictureArea1).isNotEqualTo(pictureArea2);
    }
}
