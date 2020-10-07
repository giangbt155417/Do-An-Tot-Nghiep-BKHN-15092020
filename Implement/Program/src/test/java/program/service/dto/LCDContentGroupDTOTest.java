package program.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import program.web.rest.TestUtil;

public class LCDContentGroupDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LCDContentGroupDTO.class);
        LCDContentGroupDTO lCDContentGroupDTO1 = new LCDContentGroupDTO();
        lCDContentGroupDTO1.setId(1L);
        LCDContentGroupDTO lCDContentGroupDTO2 = new LCDContentGroupDTO();
        assertThat(lCDContentGroupDTO1).isNotEqualTo(lCDContentGroupDTO2);
        lCDContentGroupDTO2.setId(lCDContentGroupDTO1.getId());
        assertThat(lCDContentGroupDTO1).isEqualTo(lCDContentGroupDTO2);
        lCDContentGroupDTO2.setId(2L);
        assertThat(lCDContentGroupDTO1).isNotEqualTo(lCDContentGroupDTO2);
        lCDContentGroupDTO1.setId(null);
        assertThat(lCDContentGroupDTO1).isNotEqualTo(lCDContentGroupDTO2);
    }
}
