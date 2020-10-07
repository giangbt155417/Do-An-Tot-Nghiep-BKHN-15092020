package program.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class LCDContentMapperTest {

    private LCDContentMapper lCDContentMapper;

    @BeforeEach
    public void setUp() {
        lCDContentMapper = new LCDContentMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(lCDContentMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(lCDContentMapper.fromId(null)).isNull();
    }
}
