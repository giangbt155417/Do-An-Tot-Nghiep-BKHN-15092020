package program.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import program.web.rest.TestUtil;

public class TextAreaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TextArea.class);
        TextArea textArea1 = new TextArea();
        textArea1.setId(1L);
        TextArea textArea2 = new TextArea();
        textArea2.setId(textArea1.getId());
        assertThat(textArea1).isEqualTo(textArea2);
        textArea2.setId(2L);
        assertThat(textArea1).isNotEqualTo(textArea2);
        textArea1.setId(null);
        assertThat(textArea1).isNotEqualTo(textArea2);
    }
}
