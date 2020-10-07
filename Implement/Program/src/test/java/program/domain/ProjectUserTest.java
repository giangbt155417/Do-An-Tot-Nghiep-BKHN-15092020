package program.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import program.web.rest.TestUtil;

public class ProjectUserTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProjectUser.class);
        ProjectUser projectUser1 = new ProjectUser();
        projectUser1.setId(1L);
        ProjectUser projectUser2 = new ProjectUser();
        projectUser2.setId(projectUser1.getId());
        assertThat(projectUser1).isEqualTo(projectUser2);
        projectUser2.setId(2L);
        assertThat(projectUser1).isNotEqualTo(projectUser2);
        projectUser1.setId(null);
        assertThat(projectUser1).isNotEqualTo(projectUser2);
    }
}
