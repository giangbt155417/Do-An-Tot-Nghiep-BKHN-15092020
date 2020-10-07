package program.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import program.web.rest.TestUtil;

public class TextAreaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TextAreaDTO.class);
        TextAreaDTO textAreaDTO1 = new TextAreaDTO();
        textAreaDTO1.setId(1L);
        TextAreaDTO textAreaDTO2 = new TextAreaDTO();
        assertThat(textAreaDTO1).isNotEqualTo(textAreaDTO2);
        textAreaDTO2.setId(textAreaDTO1.getId());
        assertThat(textAreaDTO1).isEqualTo(textAreaDTO2);
        textAreaDTO2.setId(2L);
        assertThat(textAreaDTO1).isNotEqualTo(textAreaDTO2);
        textAreaDTO1.setId(null);
        assertThat(textAreaDTO1).isNotEqualTo(textAreaDTO2);
    }
}
