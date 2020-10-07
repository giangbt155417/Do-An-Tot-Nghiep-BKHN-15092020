package program.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import program.web.rest.TestUtil;

public class LCDContentGroupTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LCDContentGroup.class);
        LCDContentGroup lCDContentGroup1 = new LCDContentGroup();
        lCDContentGroup1.setId(1L);
        LCDContentGroup lCDContentGroup2 = new LCDContentGroup();
        lCDContentGroup2.setId(lCDContentGroup1.getId());
        assertThat(lCDContentGroup1).isEqualTo(lCDContentGroup2);
        lCDContentGroup2.setId(2L);
        assertThat(lCDContentGroup1).isNotEqualTo(lCDContentGroup2);
        lCDContentGroup1.setId(null);
        assertThat(lCDContentGroup1).isNotEqualTo(lCDContentGroup2);
    }
}
