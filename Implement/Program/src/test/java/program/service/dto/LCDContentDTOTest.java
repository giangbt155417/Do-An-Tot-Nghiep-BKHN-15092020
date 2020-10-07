package program.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import program.web.rest.TestUtil;

public class LCDContentDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LCDContentDTO.class);
        LCDContentDTO lCDContentDTO1 = new LCDContentDTO();
        lCDContentDTO1.setId(1L);
        LCDContentDTO lCDContentDTO2 = new LCDContentDTO();
        assertThat(lCDContentDTO1).isNotEqualTo(lCDContentDTO2);
        lCDContentDTO2.setId(lCDContentDTO1.getId());
        assertThat(lCDContentDTO1).isEqualTo(lCDContentDTO2);
        lCDContentDTO2.setId(2L);
        assertThat(lCDContentDTO1).isNotEqualTo(lCDContentDTO2);
        lCDContentDTO1.setId(null);
        assertThat(lCDContentDTO1).isNotEqualTo(lCDContentDTO2);
    }
}
