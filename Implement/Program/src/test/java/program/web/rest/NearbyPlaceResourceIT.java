package program.web.rest;

import program.ProgramApp;
import program.config.SecurityBeanOverrideConfiguration;
import program.domain.NearbyPlace;
import program.repository.NearbyPlaceRepository;
import program.service.NearbyPlaceService;
import program.service.dto.NearbyPlaceDTO;
import program.service.mapper.NearbyPlaceMapper;

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
 * Integration tests for the {@link NearbyPlaceResource} REST controller.
 */
@SpringBootTest(classes = { SecurityBeanOverrideConfiguration.class, ProgramApp.class })
@AutoConfigureMockMvc
@WithMockUser
public class NearbyPlaceResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_MEDIA_ID = 1L;
    private static final Long UPDATED_MEDIA_ID = 2L;

    private static final String DEFAULT_CREATED_DATE = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private NearbyPlaceRepository nearbyPlaceRepository;

    @Autowired
    private NearbyPlaceMapper nearbyPlaceMapper;

    @Autowired
    private NearbyPlaceService nearbyPlaceService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNearbyPlaceMockMvc;

    private NearbyPlace nearbyPlace;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NearbyPlace createEntity(EntityManager em) {
        NearbyPlace nearbyPlace = new NearbyPlace()
            .name(DEFAULT_NAME)
            .mediaId(DEFAULT_MEDIA_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .description(DEFAULT_DESCRIPTION);
        return nearbyPlace;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NearbyPlace createUpdatedEntity(EntityManager em) {
        NearbyPlace nearbyPlace = new NearbyPlace()
            .name(UPDATED_NAME)
            .mediaId(UPDATED_MEDIA_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .description(UPDATED_DESCRIPTION);
        return nearbyPlace;
    }

    @BeforeEach
    public void initTest() {
        nearbyPlace = createEntity(em);
    }

    @Test
    @Transactional
    public void createNearbyPlace() throws Exception {
        int databaseSizeBeforeCreate = nearbyPlaceRepository.findAll().size();
        // Create the NearbyPlace
        NearbyPlaceDTO nearbyPlaceDTO = nearbyPlaceMapper.toDto(nearbyPlace);
        restNearbyPlaceMockMvc.perform(post("/api/nearby-places").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(nearbyPlaceDTO)))
            .andExpect(status().isCreated());

        // Validate the NearbyPlace in the database
        List<NearbyPlace> nearbyPlaceList = nearbyPlaceRepository.findAll();
        assertThat(nearbyPlaceList).hasSize(databaseSizeBeforeCreate + 1);
        NearbyPlace testNearbyPlace = nearbyPlaceList.get(nearbyPlaceList.size() - 1);
        assertThat(testNearbyPlace.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testNearbyPlace.getMediaId()).isEqualTo(DEFAULT_MEDIA_ID);
        assertThat(testNearbyPlace.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testNearbyPlace.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createNearbyPlaceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = nearbyPlaceRepository.findAll().size();

        // Create the NearbyPlace with an existing ID
        nearbyPlace.setId(1L);
        NearbyPlaceDTO nearbyPlaceDTO = nearbyPlaceMapper.toDto(nearbyPlace);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNearbyPlaceMockMvc.perform(post("/api/nearby-places").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(nearbyPlaceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NearbyPlace in the database
        List<NearbyPlace> nearbyPlaceList = nearbyPlaceRepository.findAll();
        assertThat(nearbyPlaceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = nearbyPlaceRepository.findAll().size();
        // set the field null
        nearbyPlace.setName(null);

        // Create the NearbyPlace, which fails.
        NearbyPlaceDTO nearbyPlaceDTO = nearbyPlaceMapper.toDto(nearbyPlace);


        restNearbyPlaceMockMvc.perform(post("/api/nearby-places").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(nearbyPlaceDTO)))
            .andExpect(status().isBadRequest());

        List<NearbyPlace> nearbyPlaceList = nearbyPlaceRepository.findAll();
        assertThat(nearbyPlaceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = nearbyPlaceRepository.findAll().size();
        // set the field null
        nearbyPlace.setCreatedDate(null);

        // Create the NearbyPlace, which fails.
        NearbyPlaceDTO nearbyPlaceDTO = nearbyPlaceMapper.toDto(nearbyPlace);


        restNearbyPlaceMockMvc.perform(post("/api/nearby-places").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(nearbyPlaceDTO)))
            .andExpect(status().isBadRequest());

        List<NearbyPlace> nearbyPlaceList = nearbyPlaceRepository.findAll();
        assertThat(nearbyPlaceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllNearbyPlaces() throws Exception {
        // Initialize the database
        nearbyPlaceRepository.saveAndFlush(nearbyPlace);

        // Get all the nearbyPlaceList
        restNearbyPlaceMockMvc.perform(get("/api/nearby-places?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nearbyPlace.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].mediaId").value(hasItem(DEFAULT_MEDIA_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getNearbyPlace() throws Exception {
        // Initialize the database
        nearbyPlaceRepository.saveAndFlush(nearbyPlace);

        // Get the nearbyPlace
        restNearbyPlaceMockMvc.perform(get("/api/nearby-places/{id}", nearbyPlace.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(nearbyPlace.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.mediaId").value(DEFAULT_MEDIA_ID.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }
    @Test
    @Transactional
    public void getNonExistingNearbyPlace() throws Exception {
        // Get the nearbyPlace
        restNearbyPlaceMockMvc.perform(get("/api/nearby-places/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNearbyPlace() throws Exception {
        // Initialize the database
        nearbyPlaceRepository.saveAndFlush(nearbyPlace);

        int databaseSizeBeforeUpdate = nearbyPlaceRepository.findAll().size();

        // Update the nearbyPlace
        NearbyPlace updatedNearbyPlace = nearbyPlaceRepository.findById(nearbyPlace.getId()).get();
        // Disconnect from session so that the updates on updatedNearbyPlace are not directly saved in db
        em.detach(updatedNearbyPlace);
        updatedNearbyPlace
            .name(UPDATED_NAME)
            .mediaId(UPDATED_MEDIA_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .description(UPDATED_DESCRIPTION);
        NearbyPlaceDTO nearbyPlaceDTO = nearbyPlaceMapper.toDto(updatedNearbyPlace);

        restNearbyPlaceMockMvc.perform(put("/api/nearby-places").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(nearbyPlaceDTO)))
            .andExpect(status().isOk());

        // Validate the NearbyPlace in the database
        List<NearbyPlace> nearbyPlaceList = nearbyPlaceRepository.findAll();
        assertThat(nearbyPlaceList).hasSize(databaseSizeBeforeUpdate);
        NearbyPlace testNearbyPlace = nearbyPlaceList.get(nearbyPlaceList.size() - 1);
        assertThat(testNearbyPlace.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testNearbyPlace.getMediaId()).isEqualTo(UPDATED_MEDIA_ID);
        assertThat(testNearbyPlace.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testNearbyPlace.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingNearbyPlace() throws Exception {
        int databaseSizeBeforeUpdate = nearbyPlaceRepository.findAll().size();

        // Create the NearbyPlace
        NearbyPlaceDTO nearbyPlaceDTO = nearbyPlaceMapper.toDto(nearbyPlace);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNearbyPlaceMockMvc.perform(put("/api/nearby-places").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(nearbyPlaceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NearbyPlace in the database
        List<NearbyPlace> nearbyPlaceList = nearbyPlaceRepository.findAll();
        assertThat(nearbyPlaceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNearbyPlace() throws Exception {
        // Initialize the database
        nearbyPlaceRepository.saveAndFlush(nearbyPlace);

        int databaseSizeBeforeDelete = nearbyPlaceRepository.findAll().size();

        // Delete the nearbyPlace
        restNearbyPlaceMockMvc.perform(delete("/api/nearby-places/{id}", nearbyPlace.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NearbyPlace> nearbyPlaceList = nearbyPlaceRepository.findAll();
        assertThat(nearbyPlaceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
