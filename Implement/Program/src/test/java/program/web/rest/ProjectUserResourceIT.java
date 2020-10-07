package program.web.rest;

import program.ProgramApp;
import program.config.SecurityBeanOverrideConfiguration;
import program.domain.ProjectUser;
import program.repository.ProjectUserRepository;
import program.service.ProjectUserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ProjectUserResource} REST controller.
 */
@SpringBootTest(classes = { SecurityBeanOverrideConfiguration.class, ProgramApp.class })
@AutoConfigureMockMvc
@WithMockUser
public class ProjectUserResourceIT {

    private static final Long DEFAULT_PROJECT_ID = 1L;
    private static final Long UPDATED_PROJECT_ID = 2L;

    private static final Long DEFAULT_USER_ID = 1L;
    private static final Long UPDATED_USER_ID = 2L;

    @Autowired
    private ProjectUserRepository projectUserRepository;

    @Autowired
    private ProjectUserService projectUserService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProjectUserMockMvc;

    private ProjectUser projectUser;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProjectUser createEntity(EntityManager em) {
        ProjectUser projectUser = new ProjectUser()
            .projectId(DEFAULT_PROJECT_ID)
            .userId(DEFAULT_USER_ID);
        return projectUser;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProjectUser createUpdatedEntity(EntityManager em) {
        ProjectUser projectUser = new ProjectUser()
            .projectId(UPDATED_PROJECT_ID)
            .userId(UPDATED_USER_ID);
        return projectUser;
    }

    @BeforeEach
    public void initTest() {
        projectUser = createEntity(em);
    }

    @Test
    @Transactional
    public void createProjectUser() throws Exception {
        int databaseSizeBeforeCreate = projectUserRepository.findAll().size();
        // Create the ProjectUser
        restProjectUserMockMvc.perform(post("/api/project-users").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectUser)))
            .andExpect(status().isCreated());

        // Validate the ProjectUser in the database
        List<ProjectUser> projectUserList = projectUserRepository.findAll();
        assertThat(projectUserList).hasSize(databaseSizeBeforeCreate + 1);
        ProjectUser testProjectUser = projectUserList.get(projectUserList.size() - 1);
        assertThat(testProjectUser.getProjectId()).isEqualTo(DEFAULT_PROJECT_ID);
        assertThat(testProjectUser.getUserId()).isEqualTo(DEFAULT_USER_ID);
    }

    @Test
    @Transactional
    public void createProjectUserWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = projectUserRepository.findAll().size();

        // Create the ProjectUser with an existing ID
        projectUser.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjectUserMockMvc.perform(post("/api/project-users").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectUser)))
            .andExpect(status().isBadRequest());

        // Validate the ProjectUser in the database
        List<ProjectUser> projectUserList = projectUserRepository.findAll();
        assertThat(projectUserList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkProjectIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectUserRepository.findAll().size();
        // set the field null
        projectUser.setProjectId(null);

        // Create the ProjectUser, which fails.


        restProjectUserMockMvc.perform(post("/api/project-users").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectUser)))
            .andExpect(status().isBadRequest());

        List<ProjectUser> projectUserList = projectUserRepository.findAll();
        assertThat(projectUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUserIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectUserRepository.findAll().size();
        // set the field null
        projectUser.setUserId(null);

        // Create the ProjectUser, which fails.


        restProjectUserMockMvc.perform(post("/api/project-users").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectUser)))
            .andExpect(status().isBadRequest());

        List<ProjectUser> projectUserList = projectUserRepository.findAll();
        assertThat(projectUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProjectUsers() throws Exception {
        // Initialize the database
        projectUserRepository.saveAndFlush(projectUser);

        // Get all the projectUserList
        restProjectUserMockMvc.perform(get("/api/project-users?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projectUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].projectId").value(hasItem(DEFAULT_PROJECT_ID.intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.intValue())));
    }
    
    @Test
    @Transactional
    public void getProjectUser() throws Exception {
        // Initialize the database
        projectUserRepository.saveAndFlush(projectUser);

        // Get the projectUser
        restProjectUserMockMvc.perform(get("/api/project-users/{id}", projectUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(projectUser.getId().intValue()))
            .andExpect(jsonPath("$.projectId").value(DEFAULT_PROJECT_ID.intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingProjectUser() throws Exception {
        // Get the projectUser
        restProjectUserMockMvc.perform(get("/api/project-users/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProjectUser() throws Exception {
        // Initialize the database
        projectUserService.save(projectUser);

        int databaseSizeBeforeUpdate = projectUserRepository.findAll().size();

        // Update the projectUser
        ProjectUser updatedProjectUser = projectUserRepository.findById(projectUser.getId()).get();
        // Disconnect from session so that the updates on updatedProjectUser are not directly saved in db
        em.detach(updatedProjectUser);
        updatedProjectUser
            .projectId(UPDATED_PROJECT_ID)
            .userId(UPDATED_USER_ID);

        restProjectUserMockMvc.perform(put("/api/project-users").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedProjectUser)))
            .andExpect(status().isOk());

        // Validate the ProjectUser in the database
        List<ProjectUser> projectUserList = projectUserRepository.findAll();
        assertThat(projectUserList).hasSize(databaseSizeBeforeUpdate);
        ProjectUser testProjectUser = projectUserList.get(projectUserList.size() - 1);
        assertThat(testProjectUser.getProjectId()).isEqualTo(UPDATED_PROJECT_ID);
        assertThat(testProjectUser.getUserId()).isEqualTo(UPDATED_USER_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingProjectUser() throws Exception {
        int databaseSizeBeforeUpdate = projectUserRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProjectUserMockMvc.perform(put("/api/project-users").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectUser)))
            .andExpect(status().isBadRequest());

        // Validate the ProjectUser in the database
        List<ProjectUser> projectUserList = projectUserRepository.findAll();
        assertThat(projectUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProjectUser() throws Exception {
        // Initialize the database
        projectUserService.save(projectUser);

        int databaseSizeBeforeDelete = projectUserRepository.findAll().size();

        // Delete the projectUser
        restProjectUserMockMvc.perform(delete("/api/project-users/{id}", projectUser.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProjectUser> projectUserList = projectUserRepository.findAll();
        assertThat(projectUserList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
