package program.web.rest;

import program.ProgramApp;
import program.config.SecurityBeanOverrideConfiguration;
import program.domain.PictureArea;
import program.repository.PictureAreaRepository;
import program.service.PictureAreaService;
import program.service.dto.PictureAreaDTO;
import program.service.mapper.PictureAreaMapper;

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
 * Integration tests for the {@link PictureAreaResource} REST controller.
 */
@SpringBootTest(classes = { SecurityBeanOverrideConfiguration.class, ProgramApp.class })
@AutoConfigureMockMvc
@WithMockUser
public class PictureAreaResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_POS_X = 1;
    private static final Integer UPDATED_POS_X = 2;

    private static final Integer DEFAULT_POS_Y = 1;
    private static final Integer UPDATED_POS_Y = 2;

    private static final Integer DEFAULT_WIDTH = 1;
    private static final Integer UPDATED_WIDTH = 2;

    private static final Integer DEFAULT_HEIGHT = 1;
    private static final Integer UPDATED_HEIGHT = 2;

    private static final Integer DEFAULT_INDEX = 1;
    private static final Integer UPDATED_INDEX = 2;

    private static final Boolean DEFAULT_IS_FIX = false;
    private static final Boolean UPDATED_IS_FIX = true;

    private static final Integer DEFAULT_OBJECT_FIT = 1;
    private static final Integer UPDATED_OBJECT_FIT = 2;

    private static final Long DEFAULT_MEDIA_ID = 1L;
    private static final Long UPDATED_MEDIA_ID = 2L;

    private static final Integer DEFAULT_LINK_DATA = 1;
    private static final Integer UPDATED_LINK_DATA = 2;

    private static final Integer DEFAULT_ON = 1;
    private static final Integer UPDATED_ON = 2;

    private static final Integer DEFAULT_OFF = 1;
    private static final Integer UPDATED_OFF = 2;

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final Long DEFAULT_CONTENT_ID = 1L;
    private static final Long UPDATED_CONTENT_ID = 2L;

    @Autowired
    private PictureAreaRepository pictureAreaRepository;

    @Autowired
    private PictureAreaMapper pictureAreaMapper;

    @Autowired
    private PictureAreaService pictureAreaService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPictureAreaMockMvc;

    private PictureArea pictureArea;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PictureArea createEntity(EntityManager em) {
        PictureArea pictureArea = new PictureArea()
            .name(DEFAULT_NAME)
            .posX(DEFAULT_POS_X)
            .posY(DEFAULT_POS_Y)
            .width(DEFAULT_WIDTH)
            .height(DEFAULT_HEIGHT)
            .index(DEFAULT_INDEX)
            .isFix(DEFAULT_IS_FIX)
            .objectFit(DEFAULT_OBJECT_FIT)
            .mediaId(DEFAULT_MEDIA_ID)
            .linkData(DEFAULT_LINK_DATA)
            .on(DEFAULT_ON)
            .off(DEFAULT_OFF)
            .type(DEFAULT_TYPE)
            .contentId(DEFAULT_CONTENT_ID);
        return pictureArea;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PictureArea createUpdatedEntity(EntityManager em) {
        PictureArea pictureArea = new PictureArea()
            .name(UPDATED_NAME)
            .posX(UPDATED_POS_X)
            .posY(UPDATED_POS_Y)
            .width(UPDATED_WIDTH)
            .height(UPDATED_HEIGHT)
            .index(UPDATED_INDEX)
            .isFix(UPDATED_IS_FIX)
            .objectFit(UPDATED_OBJECT_FIT)
            .mediaId(UPDATED_MEDIA_ID)
            .linkData(UPDATED_LINK_DATA)
            .on(UPDATED_ON)
            .off(UPDATED_OFF)
            .type(UPDATED_TYPE)
            .contentId(UPDATED_CONTENT_ID);
        return pictureArea;
    }

    @BeforeEach
    public void initTest() {
        pictureArea = createEntity(em);
    }

    @Test
    @Transactional
    public void createPictureArea() throws Exception {
        int databaseSizeBeforeCreate = pictureAreaRepository.findAll().size();
        // Create the PictureArea
        PictureAreaDTO pictureAreaDTO = pictureAreaMapper.toDto(pictureArea);
        restPictureAreaMockMvc.perform(post("/api/picture-areas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pictureAreaDTO)))
            .andExpect(status().isCreated());

        // Validate the PictureArea in the database
        List<PictureArea> pictureAreaList = pictureAreaRepository.findAll();
        assertThat(pictureAreaList).hasSize(databaseSizeBeforeCreate + 1);
        PictureArea testPictureArea = pictureAreaList.get(pictureAreaList.size() - 1);
        assertThat(testPictureArea.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPictureArea.getPosX()).isEqualTo(DEFAULT_POS_X);
        assertThat(testPictureArea.getPosY()).isEqualTo(DEFAULT_POS_Y);
        assertThat(testPictureArea.getWidth()).isEqualTo(DEFAULT_WIDTH);
        assertThat(testPictureArea.getHeight()).isEqualTo(DEFAULT_HEIGHT);
        assertThat(testPictureArea.getIndex()).isEqualTo(DEFAULT_INDEX);
        assertThat(testPictureArea.isIsFix()).isEqualTo(DEFAULT_IS_FIX);
        assertThat(testPictureArea.getObjectFit()).isEqualTo(DEFAULT_OBJECT_FIT);
        assertThat(testPictureArea.getMediaId()).isEqualTo(DEFAULT_MEDIA_ID);
        assertThat(testPictureArea.getLinkData()).isEqualTo(DEFAULT_LINK_DATA);
        assertThat(testPictureArea.getOn()).isEqualTo(DEFAULT_ON);
        assertThat(testPictureArea.getOff()).isEqualTo(DEFAULT_OFF);
        assertThat(testPictureArea.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testPictureArea.getContentId()).isEqualTo(DEFAULT_CONTENT_ID);
    }

    @Test
    @Transactional
    public void createPictureAreaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pictureAreaRepository.findAll().size();

        // Create the PictureArea with an existing ID
        pictureArea.setId(1L);
        PictureAreaDTO pictureAreaDTO = pictureAreaMapper.toDto(pictureArea);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPictureAreaMockMvc.perform(post("/api/picture-areas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pictureAreaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PictureArea in the database
        List<PictureArea> pictureAreaList = pictureAreaRepository.findAll();
        assertThat(pictureAreaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = pictureAreaRepository.findAll().size();
        // set the field null
        pictureArea.setName(null);

        // Create the PictureArea, which fails.
        PictureAreaDTO pictureAreaDTO = pictureAreaMapper.toDto(pictureArea);


        restPictureAreaMockMvc.perform(post("/api/picture-areas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pictureAreaDTO)))
            .andExpect(status().isBadRequest());

        List<PictureArea> pictureAreaList = pictureAreaRepository.findAll();
        assertThat(pictureAreaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPosXIsRequired() throws Exception {
        int databaseSizeBeforeTest = pictureAreaRepository.findAll().size();
        // set the field null
        pictureArea.setPosX(null);

        // Create the PictureArea, which fails.
        PictureAreaDTO pictureAreaDTO = pictureAreaMapper.toDto(pictureArea);


        restPictureAreaMockMvc.perform(post("/api/picture-areas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pictureAreaDTO)))
            .andExpect(status().isBadRequest());

        List<PictureArea> pictureAreaList = pictureAreaRepository.findAll();
        assertThat(pictureAreaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPosYIsRequired() throws Exception {
        int databaseSizeBeforeTest = pictureAreaRepository.findAll().size();
        // set the field null
        pictureArea.setPosY(null);

        // Create the PictureArea, which fails.
        PictureAreaDTO pictureAreaDTO = pictureAreaMapper.toDto(pictureArea);


        restPictureAreaMockMvc.perform(post("/api/picture-areas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pictureAreaDTO)))
            .andExpect(status().isBadRequest());

        List<PictureArea> pictureAreaList = pictureAreaRepository.findAll();
        assertThat(pictureAreaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWidthIsRequired() throws Exception {
        int databaseSizeBeforeTest = pictureAreaRepository.findAll().size();
        // set the field null
        pictureArea.setWidth(null);

        // Create the PictureArea, which fails.
        PictureAreaDTO pictureAreaDTO = pictureAreaMapper.toDto(pictureArea);


        restPictureAreaMockMvc.perform(post("/api/picture-areas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pictureAreaDTO)))
            .andExpect(status().isBadRequest());

        List<PictureArea> pictureAreaList = pictureAreaRepository.findAll();
        assertThat(pictureAreaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHeightIsRequired() throws Exception {
        int databaseSizeBeforeTest = pictureAreaRepository.findAll().size();
        // set the field null
        pictureArea.setHeight(null);

        // Create the PictureArea, which fails.
        PictureAreaDTO pictureAreaDTO = pictureAreaMapper.toDto(pictureArea);


        restPictureAreaMockMvc.perform(post("/api/picture-areas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pictureAreaDTO)))
            .andExpect(status().isBadRequest());

        List<PictureArea> pictureAreaList = pictureAreaRepository.findAll();
        assertThat(pictureAreaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIndexIsRequired() throws Exception {
        int databaseSizeBeforeTest = pictureAreaRepository.findAll().size();
        // set the field null
        pictureArea.setIndex(null);

        // Create the PictureArea, which fails.
        PictureAreaDTO pictureAreaDTO = pictureAreaMapper.toDto(pictureArea);


        restPictureAreaMockMvc.perform(post("/api/picture-areas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pictureAreaDTO)))
            .andExpect(status().isBadRequest());

        List<PictureArea> pictureAreaList = pictureAreaRepository.findAll();
        assertThat(pictureAreaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsFixIsRequired() throws Exception {
        int databaseSizeBeforeTest = pictureAreaRepository.findAll().size();
        // set the field null
        pictureArea.setIsFix(null);

        // Create the PictureArea, which fails.
        PictureAreaDTO pictureAreaDTO = pictureAreaMapper.toDto(pictureArea);


        restPictureAreaMockMvc.perform(post("/api/picture-areas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pictureAreaDTO)))
            .andExpect(status().isBadRequest());

        List<PictureArea> pictureAreaList = pictureAreaRepository.findAll();
        assertThat(pictureAreaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkObjectFitIsRequired() throws Exception {
        int databaseSizeBeforeTest = pictureAreaRepository.findAll().size();
        // set the field null
        pictureArea.setObjectFit(null);

        // Create the PictureArea, which fails.
        PictureAreaDTO pictureAreaDTO = pictureAreaMapper.toDto(pictureArea);


        restPictureAreaMockMvc.perform(post("/api/picture-areas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pictureAreaDTO)))
            .andExpect(status().isBadRequest());

        List<PictureArea> pictureAreaList = pictureAreaRepository.findAll();
        assertThat(pictureAreaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOnIsRequired() throws Exception {
        int databaseSizeBeforeTest = pictureAreaRepository.findAll().size();
        // set the field null
        pictureArea.setOn(null);

        // Create the PictureArea, which fails.
        PictureAreaDTO pictureAreaDTO = pictureAreaMapper.toDto(pictureArea);


        restPictureAreaMockMvc.perform(post("/api/picture-areas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pictureAreaDTO)))
            .andExpect(status().isBadRequest());

        List<PictureArea> pictureAreaList = pictureAreaRepository.findAll();
        assertThat(pictureAreaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOffIsRequired() throws Exception {
        int databaseSizeBeforeTest = pictureAreaRepository.findAll().size();
        // set the field null
        pictureArea.setOff(null);

        // Create the PictureArea, which fails.
        PictureAreaDTO pictureAreaDTO = pictureAreaMapper.toDto(pictureArea);


        restPictureAreaMockMvc.perform(post("/api/picture-areas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pictureAreaDTO)))
            .andExpect(status().isBadRequest());

        List<PictureArea> pictureAreaList = pictureAreaRepository.findAll();
        assertThat(pictureAreaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = pictureAreaRepository.findAll().size();
        // set the field null
        pictureArea.setType(null);

        // Create the PictureArea, which fails.
        PictureAreaDTO pictureAreaDTO = pictureAreaMapper.toDto(pictureArea);


        restPictureAreaMockMvc.perform(post("/api/picture-areas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pictureAreaDTO)))
            .andExpect(status().isBadRequest());

        List<PictureArea> pictureAreaList = pictureAreaRepository.findAll();
        assertThat(pictureAreaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = pictureAreaRepository.findAll().size();
        // set the field null
        pictureArea.setContentId(null);

        // Create the PictureArea, which fails.
        PictureAreaDTO pictureAreaDTO = pictureAreaMapper.toDto(pictureArea);


        restPictureAreaMockMvc.perform(post("/api/picture-areas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pictureAreaDTO)))
            .andExpect(status().isBadRequest());

        List<PictureArea> pictureAreaList = pictureAreaRepository.findAll();
        assertThat(pictureAreaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPictureAreas() throws Exception {
        // Initialize the database
        pictureAreaRepository.saveAndFlush(pictureArea);

        // Get all the pictureAreaList
        restPictureAreaMockMvc.perform(get("/api/picture-areas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pictureArea.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].posX").value(hasItem(DEFAULT_POS_X)))
            .andExpect(jsonPath("$.[*].posY").value(hasItem(DEFAULT_POS_Y)))
            .andExpect(jsonPath("$.[*].width").value(hasItem(DEFAULT_WIDTH)))
            .andExpect(jsonPath("$.[*].height").value(hasItem(DEFAULT_HEIGHT)))
            .andExpect(jsonPath("$.[*].index").value(hasItem(DEFAULT_INDEX)))
            .andExpect(jsonPath("$.[*].isFix").value(hasItem(DEFAULT_IS_FIX.booleanValue())))
            .andExpect(jsonPath("$.[*].objectFit").value(hasItem(DEFAULT_OBJECT_FIT)))
            .andExpect(jsonPath("$.[*].mediaId").value(hasItem(DEFAULT_MEDIA_ID.intValue())))
            .andExpect(jsonPath("$.[*].linkData").value(hasItem(DEFAULT_LINK_DATA)))
            .andExpect(jsonPath("$.[*].on").value(hasItem(DEFAULT_ON)))
            .andExpect(jsonPath("$.[*].off").value(hasItem(DEFAULT_OFF)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].contentId").value(hasItem(DEFAULT_CONTENT_ID.intValue())));
    }
    
    @Test
    @Transactional
    public void getPictureArea() throws Exception {
        // Initialize the database
        pictureAreaRepository.saveAndFlush(pictureArea);

        // Get the pictureArea
        restPictureAreaMockMvc.perform(get("/api/picture-areas/{id}", pictureArea.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(pictureArea.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.posX").value(DEFAULT_POS_X))
            .andExpect(jsonPath("$.posY").value(DEFAULT_POS_Y))
            .andExpect(jsonPath("$.width").value(DEFAULT_WIDTH))
            .andExpect(jsonPath("$.height").value(DEFAULT_HEIGHT))
            .andExpect(jsonPath("$.index").value(DEFAULT_INDEX))
            .andExpect(jsonPath("$.isFix").value(DEFAULT_IS_FIX.booleanValue()))
            .andExpect(jsonPath("$.objectFit").value(DEFAULT_OBJECT_FIT))
            .andExpect(jsonPath("$.mediaId").value(DEFAULT_MEDIA_ID.intValue()))
            .andExpect(jsonPath("$.linkData").value(DEFAULT_LINK_DATA))
            .andExpect(jsonPath("$.on").value(DEFAULT_ON))
            .andExpect(jsonPath("$.off").value(DEFAULT_OFF))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.contentId").value(DEFAULT_CONTENT_ID.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingPictureArea() throws Exception {
        // Get the pictureArea
        restPictureAreaMockMvc.perform(get("/api/picture-areas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePictureArea() throws Exception {
        // Initialize the database
        pictureAreaRepository.saveAndFlush(pictureArea);

        int databaseSizeBeforeUpdate = pictureAreaRepository.findAll().size();

        // Update the pictureArea
        PictureArea updatedPictureArea = pictureAreaRepository.findById(pictureArea.getId()).get();
        // Disconnect from session so that the updates on updatedPictureArea are not directly saved in db
        em.detach(updatedPictureArea);
        updatedPictureArea
            .name(UPDATED_NAME)
            .posX(UPDATED_POS_X)
            .posY(UPDATED_POS_Y)
            .width(UPDATED_WIDTH)
            .height(UPDATED_HEIGHT)
            .index(UPDATED_INDEX)
            .isFix(UPDATED_IS_FIX)
            .objectFit(UPDATED_OBJECT_FIT)
            .mediaId(UPDATED_MEDIA_ID)
            .linkData(UPDATED_LINK_DATA)
            .on(UPDATED_ON)
            .off(UPDATED_OFF)
            .type(UPDATED_TYPE)
            .contentId(UPDATED_CONTENT_ID);
        PictureAreaDTO pictureAreaDTO = pictureAreaMapper.toDto(updatedPictureArea);

        restPictureAreaMockMvc.perform(put("/api/picture-areas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pictureAreaDTO)))
            .andExpect(status().isOk());

        // Validate the PictureArea in the database
        List<PictureArea> pictureAreaList = pictureAreaRepository.findAll();
        assertThat(pictureAreaList).hasSize(databaseSizeBeforeUpdate);
        PictureArea testPictureArea = pictureAreaList.get(pictureAreaList.size() - 1);
        assertThat(testPictureArea.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPictureArea.getPosX()).isEqualTo(UPDATED_POS_X);
        assertThat(testPictureArea.getPosY()).isEqualTo(UPDATED_POS_Y);
        assertThat(testPictureArea.getWidth()).isEqualTo(UPDATED_WIDTH);
        assertThat(testPictureArea.getHeight()).isEqualTo(UPDATED_HEIGHT);
        assertThat(testPictureArea.getIndex()).isEqualTo(UPDATED_INDEX);
        assertThat(testPictureArea.isIsFix()).isEqualTo(UPDATED_IS_FIX);
        assertThat(testPictureArea.getObjectFit()).isEqualTo(UPDATED_OBJECT_FIT);
        assertThat(testPictureArea.getMediaId()).isEqualTo(UPDATED_MEDIA_ID);
        assertThat(testPictureArea.getLinkData()).isEqualTo(UPDATED_LINK_DATA);
        assertThat(testPictureArea.getOn()).isEqualTo(UPDATED_ON);
        assertThat(testPictureArea.getOff()).isEqualTo(UPDATED_OFF);
        assertThat(testPictureArea.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testPictureArea.getContentId()).isEqualTo(UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingPictureArea() throws Exception {
        int databaseSizeBeforeUpdate = pictureAreaRepository.findAll().size();

        // Create the PictureArea
        PictureAreaDTO pictureAreaDTO = pictureAreaMapper.toDto(pictureArea);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPictureAreaMockMvc.perform(put("/api/picture-areas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pictureAreaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PictureArea in the database
        List<PictureArea> pictureAreaList = pictureAreaRepository.findAll();
        assertThat(pictureAreaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePictureArea() throws Exception {
        // Initialize the database
        pictureAreaRepository.saveAndFlush(pictureArea);

        int databaseSizeBeforeDelete = pictureAreaRepository.findAll().size();

        // Delete the pictureArea
        restPictureAreaMockMvc.perform(delete("/api/picture-areas/{id}", pictureArea.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PictureArea> pictureAreaList = pictureAreaRepository.findAll();
        assertThat(pictureAreaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
