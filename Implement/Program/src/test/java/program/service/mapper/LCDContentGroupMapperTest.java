package program.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class LCDContentGroupMapperTest {

    private LCDContentGroupMapper lCDContentGroupMapper;

    @BeforeEach
    public void setUp() {
        lCDContentGroupMapper = new LCDContentGroupMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(lCDContentGroupMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(lCDContentGroupMapper.fromId(null)).isNull();
    }
}
