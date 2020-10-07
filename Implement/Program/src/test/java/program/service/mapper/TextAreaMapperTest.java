package program.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TextAreaMapperTest {

    private TextAreaMapper textAreaMapper;

    @BeforeEach
    public void setUp() {
        textAreaMapper = new TextAreaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(textAreaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(textAreaMapper.fromId(null)).isNull();
    }
}
