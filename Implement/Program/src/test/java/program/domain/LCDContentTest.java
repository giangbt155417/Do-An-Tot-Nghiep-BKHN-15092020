package program.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import program.web.rest.TestUtil;

public class LCDContentTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LCDContent.class);
        LCDContent lCDContent1 = new LCDContent();
        lCDContent1.setId(1L);
        LCDContent lCDContent2 = new LCDContent();
        lCDContent2.setId(lCDContent1.getId());
        assertThat(lCDContent1).isEqualTo(lCDContent2);
        lCDContent2.setId(2L);
        assertThat(lCDContent1).isNotEqualTo(lCDContent2);
        lCDContent1.setId(null);
        assertThat(lCDContent1).isNotEqualTo(lCDContent2);
    }
}
