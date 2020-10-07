package program.web.rest;

import program.ProgramApp;
import program.config.SecurityBeanOverrideConfiguration;
import program.domain.LCDContent;
import program.repository.LCDContentRepository;
import program.service.LCDContentService;
import program.service.dto.LCDContentDTO;
import program.service.mapper.LCDContentMapper;

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
 * Integration tests for the {@link LCDContentResource} REST controller.
 */
@SpringBootTest(classes = { SecurityBeanOverrideConfiguration.class, ProgramApp.class })
@AutoConfigureMockMvc
@WithMockUser
public class LCDContentResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_WIDTH = 1;
    private static final Integer UPDATED_WIDTH = 2;

    private static final Integer DEFAULT_HEIGHT = 1;
    private static final Integer UPDATED_HEIGHT = 2;

    private static final Long DEFAULT_LCD_CONTENT_GROUP = 1L;
    private static final Long UPDATED_LCD_CONTENT_GROUP = 2L;

    private static final String DEFAULT_CREATED_DATE = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private LCDContentRepository lCDContentRepository;

    @Autowired
    private LCDContentMapper lCDContentMapper;

    @Autowired
    private LCDContentService lCDContentService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLCDContentMockMvc;

    private LCDContent lCDContent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LCDContent createEntity(EntityManager em) {
        LCDContent lCDContent = new LCDContent()
            .name(DEFAULT_NAME)
            .width(DEFAULT_WIDTH)
            .height(DEFAULT_HEIGHT)
            .contentGroupId(DEFAULT_LCD_CONTENT_GROUP)
            .createdDate(DEFAULT_CREATED_DATE)
            .description(DEFAULT_DESCRIPTION);
        return lCDContent;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LCDContent createUpdatedEntity(EntityManager em) {
        LCDContent lCDContent = new LCDContent()
            .name(UPDATED_NAME)
            .width(UPDATED_WIDTH)
            .height(UPDATED_HEIGHT)
            .contentGroupId(UPDATED_LCD_CONTENT_GROUP)
            .createdDate(UPDATED_CREATED_DATE)
            .description(UPDATED_DESCRIPTION);
        return lCDContent;
    }

    @BeforeEach
    public void initTest() {
        lCDContent = createEntity(em);
    }

    @Test
    @Transactional
    public void createLCDContent() throws Exception {
        int databaseSizeBeforeCreate = lCDContentRepository.findAll().size();
        // Create the LCDContent
        LCDContentDTO lCDContentDTO = lCDContentMapper.toDto(lCDContent);
        restLCDContentMockMvc.perform(post("/api/lcd-contents").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lCDContentDTO)))
            .andExpect(status().isCreated());

        // Validate the LCDContent in the database
        List<LCDContent> lCDContentList = lCDContentRepository.findAll();
        assertThat(lCDContentList).hasSize(databaseSizeBeforeCreate + 1);
        LCDContent testLCDContent = lCDContentList.get(lCDContentList.size() - 1);
        assertThat(testLCDContent.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testLCDContent.getWidth()).isEqualTo(DEFAULT_WIDTH);
        assertThat(testLCDContent.getHeight()).isEqualTo(DEFAULT_HEIGHT);
        assertThat(testLCDContent.getContentGroupId()).isEqualTo(DEFAULT_LCD_CONTENT_GROUP);
        assertThat(testLCDContent.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testLCDContent.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createLCDContentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = lCDContentRepository.findAll().size();

        // Create the LCDContent with an existing ID
        lCDContent.setId(1L);
        LCDContentDTO lCDContentDTO = lCDContentMapper.toDto(lCDContent);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLCDContentMockMvc.perform(post("/api/lcd-contents").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lCDContentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LCDContent in the database
        List<LCDContent> lCDContentList = lCDContentRepository.findAll();
        assertThat(lCDContentList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = lCDContentRepository.findAll().size();
        // set the field null
        lCDContent.setName(null);

        // Create the LCDContent, which fails.
        LCDContentDTO lCDContentDTO = lCDContentMapper.toDto(lCDContent);


        restLCDContentMockMvc.perform(post("/api/lcd-contents").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lCDContentDTO)))
            .andExpect(status().isBadRequest());

        List<LCDContent> lCDContentList = lCDContentRepository.findAll();
        assertThat(lCDContentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWidthIsRequired() throws Exception {
        int databaseSizeBeforeTest = lCDContentRepository.findAll().size();
        // set the field null
        lCDContent.setWidth(null);

        // Create the LCDContent, which fails.
        LCDContentDTO lCDContentDTO = lCDContentMapper.toDto(lCDContent);


        restLCDContentMockMvc.perform(post("/api/lcd-contents").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lCDContentDTO)))
            .andExpect(status().isBadRequest());

        List<LCDContent> lCDContentList = lCDContentRepository.findAll();
        assertThat(lCDContentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHeightIsRequired() throws Exception {
        int databaseSizeBeforeTest = lCDContentRepository.findAll().size();
        // set the field null
        lCDContent.setHeight(null);

        // Create the LCDContent, which fails.
        LCDContentDTO lCDContentDTO = lCDContentMapper.toDto(lCDContent);


        restLCDContentMockMvc.perform(post("/api/lcd-contents").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lCDContentDTO)))
            .andExpect(status().isBadRequest());

        List<LCDContent> lCDContentList = lCDContentRepository.findAll();
        assertThat(lCDContentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLcdContentGroupIsRequired() throws Exception {
        int databaseSizeBeforeTest = lCDContentRepository.findAll().size();
        // set the field null
        lCDContent.setContentGroupId(null);

        // Create the LCDContent, which fails.
        LCDContentDTO lCDContentDTO = lCDContentMapper.toDto(lCDContent);


        restLCDContentMockMvc.perform(post("/api/lcd-contents").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lCDContentDTO)))
            .andExpect(status().isBadRequest());

        List<LCDContent> lCDContentList = lCDContentRepository.findAll();
        assertThat(lCDContentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = lCDContentRepository.findAll().size();
        // set the field null
        lCDContent.setCreatedDate(null);

        // Create the LCDContent, which fails.
        LCDContentDTO lCDContentDTO = lCDContentMapper.toDto(lCDContent);


        restLCDContentMockMvc.perform(post("/api/lcd-contents").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lCDContentDTO)))
            .andExpect(status().isBadRequest());

        List<LCDContent> lCDContentList = lCDContentRepository.findAll();
        assertThat(lCDContentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllLCDContents() throws Exception {
        // Initialize the database
        lCDContentRepository.saveAndFlush(lCDContent);

        // Get all the lCDContentList
        restLCDContentMockMvc.perform(get("/api/lcd-contents?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(lCDContent.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].width").value(hasItem(DEFAULT_WIDTH)))
            .andExpect(jsonPath("$.[*].height").value(hasItem(DEFAULT_HEIGHT)))
            .andExpect(jsonPath("$.[*].lcdContentGroup").value(hasItem(DEFAULT_LCD_CONTENT_GROUP.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getLCDContent() throws Exception {
        // Initialize the database
        lCDContentRepository.saveAndFlush(lCDContent);

        // Get the lCDContent
        restLCDContentMockMvc.perform(get("/api/lcd-contents/{id}", lCDContent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(lCDContent.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.width").value(DEFAULT_WIDTH))
            .andExpect(jsonPath("$.height").value(DEFAULT_HEIGHT))
            .andExpect(jsonPath("$.lcdContentGroup").value(DEFAULT_LCD_CONTENT_GROUP.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }
    @Test
    @Transactional
    public void getNonExistingLCDContent() throws Exception {
        // Get the lCDContent
        restLCDContentMockMvc.perform(get("/api/lcd-contents/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLCDContent() throws Exception {
        // Initialize the database
        lCDContentRepository.saveAndFlush(lCDContent);

        int databaseSizeBeforeUpdate = lCDContentRepository.findAll().size();

        // Update the lCDContent
        LCDContent updatedLCDContent = lCDContentRepository.findById(lCDContent.getId()).get();
        // Disconnect from session so that the updates on updatedLCDContent are not directly saved in db
        em.detach(updatedLCDContent);
        updatedLCDContent
            .name(UPDATED_NAME)
            .width(UPDATED_WIDTH)
            .height(UPDATED_HEIGHT)
            .contentGroupId(UPDATED_LCD_CONTENT_GROUP)
            .createdDate(UPDATED_CREATED_DATE)
            .description(UPDATED_DESCRIPTION);
        LCDContentDTO lCDContentDTO = lCDContentMapper.toDto(updatedLCDContent);

        restLCDContentMockMvc.perform(put("/api/lcd-contents").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lCDContentDTO)))
            .andExpect(status().isOk());

        // Validate the LCDContent in the database
        List<LCDContent> lCDContentList = lCDContentRepository.findAll();
        assertThat(lCDContentList).hasSize(databaseSizeBeforeUpdate);
        LCDContent testLCDContent = lCDContentList.get(lCDContentList.size() - 1);
        assertThat(testLCDContent.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testLCDContent.getWidth()).isEqualTo(UPDATED_WIDTH);
        assertThat(testLCDContent.getHeight()).isEqualTo(UPDATED_HEIGHT);
        assertThat(testLCDContent.getContentGroupId()).isEqualTo(UPDATED_LCD_CONTENT_GROUP);
        assertThat(testLCDContent.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testLCDContent.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingLCDContent() throws Exception {
        int databaseSizeBeforeUpdate = lCDContentRepository.findAll().size();

        // Create the LCDContent
        LCDContentDTO lCDContentDTO = lCDContentMapper.toDto(lCDContent);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLCDContentMockMvc.perform(put("/api/lcd-contents").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lCDContentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LCDContent in the database
        List<LCDContent> lCDContentList = lCDContentRepository.findAll();
        assertThat(lCDContentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLCDContent() throws Exception {
        // Initialize the database
        lCDContentRepository.saveAndFlush(lCDContent);

        int databaseSizeBeforeDelete = lCDContentRepository.findAll().size();

        // Delete the lCDContent
        restLCDContentMockMvc.perform(delete("/api/lcd-contents/{id}", lCDContent.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<LCDContent> lCDContentList = lCDContentRepository.findAll();
        assertThat(lCDContentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
