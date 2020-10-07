package program.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import program.web.rest.TestUtil;

public class RouteContentTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RouteContent.class);
        RouteContent routeContent1 = new RouteContent();
        routeContent1.setId(1L);
        RouteContent routeContent2 = new RouteContent();
        routeContent2.setId(routeContent1.getId());
        assertThat(routeContent1).isEqualTo(routeContent2);
        routeContent2.setId(2L);
        assertThat(routeContent1).isNotEqualTo(routeContent2);
        routeContent1.setId(null);
        assertThat(routeContent1).isNotEqualTo(routeContent2);
    }
}
