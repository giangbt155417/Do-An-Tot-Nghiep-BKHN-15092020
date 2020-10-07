package program.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PictureAreaMapperTest {

    private PictureAreaMapper pictureAreaMapper;

    @BeforeEach
    public void setUp() {
        pictureAreaMapper = new PictureAreaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(pictureAreaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(pictureAreaMapper.fromId(null)).isNull();
    }
}
