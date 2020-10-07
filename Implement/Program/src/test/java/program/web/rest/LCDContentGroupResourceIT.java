package program.web.rest;

import program.ProgramApp;
import program.config.SecurityBeanOverrideConfiguration;
import program.domain.LCDContentGroup;
import program.repository.LCDContentGroupRepository;
import program.service.LCDContentGroupService;
import program.service.dto.LCDContentGroupDTO;
import program.service.mapper.LCDContentGroupMapper;

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
 * Integration tests for the {@link LCDContentGroupResource} REST controller.
 */
@SpringBootTest(classes = { SecurityBeanOverrideConfiguration.class, ProgramApp.class })
@AutoConfigureMockMvc
@WithMockUser
public class LCDContentGroupResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_WIDTH = 1;
    private static final Integer UPDATED_WIDTH = 2;

    private static final Integer DEFAULT_HEIGHT = 1;
    private static final Integer UPDATED_HEIGHT = 2;

    private static final Long DEFAULT_PROJECT_ID = 1L;
    private static final Long UPDATED_PROJECT_ID = 2L;

    private static final String DEFAULT_CREATED_DATE = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private LCDContentGroupRepository lCDContentGroupRepository;

    @Autowired
    private LCDContentGroupMapper lCDContentGroupMapper;

    @Autowired
    private LCDContentGroupService lCDContentGroupService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLCDContentGroupMockMvc;

    private LCDContentGroup lCDContentGroup;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LCDContentGroup createEntity(EntityManager em) {
        LCDContentGroup lCDContentGroup = new LCDContentGroup()
            .name(DEFAULT_NAME)
            .width(DEFAULT_WIDTH)
            .height(DEFAULT_HEIGHT)
            .projectId(DEFAULT_PROJECT_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .description(DEFAULT_DESCRIPTION);
        return lCDContentGroup;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LCDContentGroup createUpdatedEntity(EntityManager em) {
        LCDContentGroup lCDContentGroup = new LCDContentGroup()
            .name(UPDATED_NAME)
            .width(UPDATED_WIDTH)
            .height(UPDATED_HEIGHT)
            .projectId(UPDATED_PROJECT_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .description(UPDATED_DESCRIPTION);
        return lCDContentGroup;
    }

    @BeforeEach
    public void initTest() {
        lCDContentGroup = createEntity(em);
    }

    @Test
    @Transactional
    public void createLCDContentGroup() throws Exception {
        int databaseSizeBeforeCreate = lCDContentGroupRepository.findAll().size();
        // Create the LCDContentGroup
        LCDContentGroupDTO lCDContentGroupDTO = lCDContentGroupMapper.toDto(lCDContentGroup);
        restLCDContentGroupMockMvc.perform(post("/api/lcd-content-groups").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lCDContentGroupDTO)))
            .andExpect(status().isCreated());

        // Validate the LCDContentGroup in the database
        List<LCDContentGroup> lCDContentGroupList = lCDContentGroupRepository.findAll();
        assertThat(lCDContentGroupList).hasSize(databaseSizeBeforeCreate + 1);
        LCDContentGroup testLCDContentGroup = lCDContentGroupList.get(lCDContentGroupList.size() - 1);
        assertThat(testLCDContentGroup.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testLCDContentGroup.getWidth()).isEqualTo(DEFAULT_WIDTH);
        assertThat(testLCDContentGroup.getHeight()).isEqualTo(DEFAULT_HEIGHT);
        assertThat(testLCDContentGroup.getProjectId()).isEqualTo(DEFAULT_PROJECT_ID);
        assertThat(testLCDContentGroup.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testLCDContentGroup.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createLCDContentGroupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = lCDContentGroupRepository.findAll().size();

        // Create the LCDContentGroup with an existing ID
        lCDContentGroup.setId(1L);
        LCDContentGroupDTO lCDContentGroupDTO = lCDContentGroupMapper.toDto(lCDContentGroup);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLCDContentGroupMockMvc.perform(post("/api/lcd-content-groups").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lCDContentGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LCDContentGroup in the database
        List<LCDContentGroup> lCDContentGroupList = lCDContentGroupRepository.findAll();
        assertThat(lCDContentGroupList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = lCDContentGroupRepository.findAll().size();
        // set the field null
        lCDContentGroup.setName(null);

        // Create the LCDContentGroup, which fails.
        LCDContentGroupDTO lCDContentGroupDTO = lCDContentGroupMapper.toDto(lCDContentGroup);


        restLCDContentGroupMockMvc.perform(post("/api/lcd-content-groups").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lCDContentGroupDTO)))
            .andExpect(status().isBadRequest());

        List<LCDContentGroup> lCDContentGroupList = lCDContentGroupRepository.findAll();
        assertThat(lCDContentGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWidthIsRequired() throws Exception {
        int databaseSizeBeforeTest = lCDContentGroupRepository.findAll().size();
        // set the field null
        lCDContentGroup.setWidth(null);

        // Create the LCDContentGroup, which fails.
        LCDContentGroupDTO lCDContentGroupDTO = lCDContentGroupMapper.toDto(lCDContentGroup);


        restLCDContentGroupMockMvc.perform(post("/api/lcd-content-groups").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lCDContentGroupDTO)))
            .andExpect(status().isBadRequest());

        List<LCDContentGroup> lCDContentGroupList = lCDContentGroupRepository.findAll();
        assertThat(lCDContentGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHeightIsRequired() throws Exception {
        int databaseSizeBeforeTest = lCDContentGroupRepository.findAll().size();
        // set the field null
        lCDContentGroup.setHeight(null);

        // Create the LCDContentGroup, which fails.
        LCDContentGroupDTO lCDContentGroupDTO = lCDContentGroupMapper.toDto(lCDContentGroup);


        restLCDContentGroupMockMvc.perform(post("/api/lcd-content-groups").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lCDContentGroupDTO)))
            .andExpect(status().isBadRequest());

        List<LCDContentGroup> lCDContentGroupList = lCDContentGroupRepository.findAll();
        assertThat(lCDContentGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkProjectIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = lCDContentGroupRepository.findAll().size();
        // set the field null
        lCDContentGroup.setProjectId(null);

        // Create the LCDContentGroup, which fails.
        LCDContentGroupDTO lCDContentGroupDTO = lCDContentGroupMapper.toDto(lCDContentGroup);


        restLCDContentGroupMockMvc.perform(post("/api/lcd-content-groups").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lCDContentGroupDTO)))
            .andExpect(status().isBadRequest());

        List<LCDContentGroup> lCDContentGroupList = lCDContentGroupRepository.findAll();
        assertThat(lCDContentGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = lCDContentGroupRepository.findAll().size();
        // set the field null
        lCDContentGroup.setCreatedDate(null);

        // Create the LCDContentGroup, which fails.
        LCDContentGroupDTO lCDContentGroupDTO = lCDContentGroupMapper.toDto(lCDContentGroup);


        restLCDContentGroupMockMvc.perform(post("/api/lcd-content-groups").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lCDContentGroupDTO)))
            .andExpect(status().isBadRequest());

        List<LCDContentGroup> lCDContentGroupList = lCDContentGroupRepository.findAll();
        assertThat(lCDContentGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllLCDContentGroups() throws Exception {
        // Initialize the database
        lCDContentGroupRepository.saveAndFlush(lCDContentGroup);

        // Get all the lCDContentGroupList
        restLCDContentGroupMockMvc.perform(get("/api/lcd-content-groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(lCDContentGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].width").value(hasItem(DEFAULT_WIDTH)))
            .andExpect(jsonPath("$.[*].height").value(hasItem(DEFAULT_HEIGHT)))
            .andExpect(jsonPath("$.[*].projectId").value(hasItem(DEFAULT_PROJECT_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getLCDContentGroup() throws Exception {
        // Initialize the database
        lCDContentGroupRepository.saveAndFlush(lCDContentGroup);

        // Get the lCDContentGroup
        restLCDContentGroupMockMvc.perform(get("/api/lcd-content-groups/{id}", lCDContentGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(lCDContentGroup.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.width").value(DEFAULT_WIDTH))
            .andExpect(jsonPath("$.height").value(DEFAULT_HEIGHT))
            .andExpect(jsonPath("$.projectId").value(DEFAULT_PROJECT_ID.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }
    @Test
    @Transactional
    public void getNonExistingLCDContentGroup() throws Exception {
        // Get the lCDContentGroup
        restLCDContentGroupMockMvc.perform(get("/api/lcd-content-groups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLCDContentGroup() throws Exception {
        // Initialize the database
        lCDContentGroupRepository.saveAndFlush(lCDContentGroup);

        int databaseSizeBeforeUpdate = lCDContentGroupRepository.findAll().size();

        // Update the lCDContentGroup
        LCDContentGroup updatedLCDContentGroup = lCDContentGroupRepository.findById(lCDContentGroup.getId()).get();
        // Disconnect from session so that the updates on updatedLCDContentGroup are not directly saved in db
        em.detach(updatedLCDContentGroup);
        updatedLCDContentGroup
            .name(UPDATED_NAME)
            .width(UPDATED_WIDTH)
            .height(UPDATED_HEIGHT)
            .projectId(UPDATED_PROJECT_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .description(UPDATED_DESCRIPTION);
        LCDContentGroupDTO lCDContentGroupDTO = lCDContentGroupMapper.toDto(updatedLCDContentGroup);

        restLCDContentGroupMockMvc.perform(put("/api/lcd-content-groups").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lCDContentGroupDTO)))
            .andExpect(status().isOk());

        // Validate the LCDContentGroup in the database
        List<LCDContentGroup> lCDContentGroupList = lCDContentGroupRepository.findAll();
        assertThat(lCDContentGroupList).hasSize(databaseSizeBeforeUpdate);
        LCDContentGroup testLCDContentGroup = lCDContentGroupList.get(lCDContentGroupList.size() - 1);
        assertThat(testLCDContentGroup.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testLCDContentGroup.getWidth()).isEqualTo(UPDATED_WIDTH);
        assertThat(testLCDContentGroup.getHeight()).isEqualTo(UPDATED_HEIGHT);
        assertThat(testLCDContentGroup.getProjectId()).isEqualTo(UPDATED_PROJECT_ID);
        assertThat(testLCDContentGroup.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testLCDContentGroup.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingLCDContentGroup() throws Exception {
        int databaseSizeBeforeUpdate = lCDContentGroupRepository.findAll().size();

        // Create the LCDContentGroup
        LCDContentGroupDTO lCDContentGroupDTO = lCDContentGroupMapper.toDto(lCDContentGroup);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLCDContentGroupMockMvc.perform(put("/api/lcd-content-groups").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lCDContentGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LCDContentGroup in the database
        List<LCDContentGroup> lCDContentGroupList = lCDContentGroupRepository.findAll();
        assertThat(lCDContentGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLCDContentGroup() throws Exception {
        // Initialize the database
        lCDContentGroupRepository.saveAndFlush(lCDContentGroup);

        int databaseSizeBeforeDelete = lCDContentGroupRepository.findAll().size();

        // Delete the lCDContentGroup
        restLCDContentGroupMockMvc.perform(delete("/api/lcd-content-groups/{id}", lCDContentGroup.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<LCDContentGroup> lCDContentGroupList = lCDContentGroupRepository.findAll();
        assertThat(lCDContentGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
