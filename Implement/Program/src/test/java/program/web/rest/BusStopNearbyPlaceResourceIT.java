package program.web.rest;

import program.ProgramApp;
import program.config.SecurityBeanOverrideConfiguration;
import program.domain.BusStopNearbyPlace;
import program.repository.BusStopNearbyPlaceRepository;
import program.service.BusStopNearbyPlaceService;

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
 * Integration tests for the {@link BusStopNearbyPlaceResource} REST controller.
 */
@SpringBootTest(classes = { SecurityBeanOverrideConfiguration.class, ProgramApp.class })
@AutoConfigureMockMvc
@WithMockUser
public class BusStopNearbyPlaceResourceIT {

    private static final Long DEFAULT_BUS_STOP_ID = 1L;
    private static final Long UPDATED_BUS_STOP_ID = 2L;

    private static final Long DEFAULT_NEARBY_PLACE_ID = 1L;
    private static final Long UPDATED_NEARBY_PLACE_ID = 2L;

    @Autowired
    private BusStopNearbyPlaceRepository busStopNearbyPlaceRepository;

    @Autowired
    private BusStopNearbyPlaceService busStopNearbyPlaceService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBusStopNearbyPlaceMockMvc;

    private BusStopNearbyPlace busStopNearbyPlace;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BusStopNearbyPlace createEntity(EntityManager em) {
        BusStopNearbyPlace busStopNearbyPlace = new BusStopNearbyPlace()
            .busStopId(DEFAULT_BUS_STOP_ID)
            .nearbyPlaceId(DEFAULT_NEARBY_PLACE_ID);
        return busStopNearbyPlace;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BusStopNearbyPlace createUpdatedEntity(EntityManager em) {
        BusStopNearbyPlace busStopNearbyPlace = new BusStopNearbyPlace()
            .busStopId(UPDATED_BUS_STOP_ID)
            .nearbyPlaceId(UPDATED_NEARBY_PLACE_ID);
        return busStopNearbyPlace;
    }

    @BeforeEach
    public void initTest() {
        busStopNearbyPlace = createEntity(em);
    }

    @Test
    @Transactional
    public void createBusStopNearbyPlace() throws Exception {
        int databaseSizeBeforeCreate = busStopNearbyPlaceRepository.findAll().size();
        // Create the BusStopNearbyPlace
        restBusStopNearbyPlaceMockMvc.perform(post("/api/bus-stop-nearby-places").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(busStopNearbyPlace)))
            .andExpect(status().isCreated());

        // Validate the BusStopNearbyPlace in the database
        List<BusStopNearbyPlace> busStopNearbyPlaceList = busStopNearbyPlaceRepository.findAll();
        assertThat(busStopNearbyPlaceList).hasSize(databaseSizeBeforeCreate + 1);
        BusStopNearbyPlace testBusStopNearbyPlace = busStopNearbyPlaceList.get(busStopNearbyPlaceList.size() - 1);
        assertThat(testBusStopNearbyPlace.getBusStopId()).isEqualTo(DEFAULT_BUS_STOP_ID);
        assertThat(testBusStopNearbyPlace.getNearbyPlaceId()).isEqualTo(DEFAULT_NEARBY_PLACE_ID);
    }

    @Test
    @Transactional
    public void createBusStopNearbyPlaceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = busStopNearbyPlaceRepository.findAll().size();

        // Create the BusStopNearbyPlace with an existing ID
        busStopNearbyPlace.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBusStopNearbyPlaceMockMvc.perform(post("/api/bus-stop-nearby-places").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(busStopNearbyPlace)))
            .andExpect(status().isBadRequest());

        // Validate the BusStopNearbyPlace in the database
        List<BusStopNearbyPlace> busStopNearbyPlaceList = busStopNearbyPlaceRepository.findAll();
        assertThat(busStopNearbyPlaceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkBusStopIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = busStopNearbyPlaceRepository.findAll().size();
        // set the field null
        busStopNearbyPlace.setBusStopId(null);

        // Create the BusStopNearbyPlace, which fails.


        restBusStopNearbyPlaceMockMvc.perform(post("/api/bus-stop-nearby-places").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(busStopNearbyPlace)))
            .andExpect(status().isBadRequest());

        List<BusStopNearbyPlace> busStopNearbyPlaceList = busStopNearbyPlaceRepository.findAll();
        assertThat(busStopNearbyPlaceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNearbyPlaceIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = busStopNearbyPlaceRepository.findAll().size();
        // set the field null
        busStopNearbyPlace.setNearbyPlaceId(null);

        // Create the BusStopNearbyPlace, which fails.


        restBusStopNearbyPlaceMockMvc.perform(post("/api/bus-stop-nearby-places").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(busStopNearbyPlace)))
            .andExpect(status().isBadRequest());

        List<BusStopNearbyPlace> busStopNearbyPlaceList = busStopNearbyPlaceRepository.findAll();
        assertThat(busStopNearbyPlaceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBusStopNearbyPlaces() throws Exception {
        // Initialize the database
        busStopNearbyPlaceRepository.saveAndFlush(busStopNearbyPlace);

        // Get all the busStopNearbyPlaceList
        restBusStopNearbyPlaceMockMvc.perform(get("/api/bus-stop-nearby-places?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(busStopNearbyPlace.getId().intValue())))
            .andExpect(jsonPath("$.[*].busStopId").value(hasItem(DEFAULT_BUS_STOP_ID.intValue())))
            .andExpect(jsonPath("$.[*].nearbyPlaceId").value(hasItem(DEFAULT_NEARBY_PLACE_ID.intValue())));
    }
    
    @Test
    @Transactional
    public void getBusStopNearbyPlace() throws Exception {
        // Initialize the database
        busStopNearbyPlaceRepository.saveAndFlush(busStopNearbyPlace);

        // Get the busStopNearbyPlace
        restBusStopNearbyPlaceMockMvc.perform(get("/api/bus-stop-nearby-places/{id}", busStopNearbyPlace.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(busStopNearbyPlace.getId().intValue()))
            .andExpect(jsonPath("$.busStopId").value(DEFAULT_BUS_STOP_ID.intValue()))
            .andExpect(jsonPath("$.nearbyPlaceId").value(DEFAULT_NEARBY_PLACE_ID.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingBusStopNearbyPlace() throws Exception {
        // Get the busStopNearbyPlace
        restBusStopNearbyPlaceMockMvc.perform(get("/api/bus-stop-nearby-places/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBusStopNearbyPlace() throws Exception {
        // Initialize the database
        busStopNearbyPlaceService.save(busStopNearbyPlace);

        int databaseSizeBeforeUpdate = busStopNearbyPlaceRepository.findAll().size();

        // Update the busStopNearbyPlace
        BusStopNearbyPlace updatedBusStopNearbyPlace = busStopNearbyPlaceRepository.findById(busStopNearbyPlace.getId()).get();
        // Disconnect from session so that the updates on updatedBusStopNearbyPlace are not directly saved in db
        em.detach(updatedBusStopNearbyPlace);
        updatedBusStopNearbyPlace
            .busStopId(UPDATED_BUS_STOP_ID)
            .nearbyPlaceId(UPDATED_NEARBY_PLACE_ID);

        restBusStopNearbyPlaceMockMvc.perform(put("/api/bus-stop-nearby-places").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedBusStopNearbyPlace)))
            .andExpect(status().isOk());

        // Validate the BusStopNearbyPlace in the database
        List<BusStopNearbyPlace> busStopNearbyPlaceList = busStopNearbyPlaceRepository.findAll();
        assertThat(busStopNearbyPlaceList).hasSize(databaseSizeBeforeUpdate);
        BusStopNearbyPlace testBusStopNearbyPlace = busStopNearbyPlaceList.get(busStopNearbyPlaceList.size() - 1);
        assertThat(testBusStopNearbyPlace.getBusStopId()).isEqualTo(UPDATED_BUS_STOP_ID);
        assertThat(testBusStopNearbyPlace.getNearbyPlaceId()).isEqualTo(UPDATED_NEARBY_PLACE_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingBusStopNearbyPlace() throws Exception {
        int databaseSizeBeforeUpdate = busStopNearbyPlaceRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBusStopNearbyPlaceMockMvc.perform(put("/api/bus-stop-nearby-places").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(busStopNearbyPlace)))
            .andExpect(status().isBadRequest());

        // Validate the BusStopNearbyPlace in the database
        List<BusStopNearbyPlace> busStopNearbyPlaceList = busStopNearbyPlaceRepository.findAll();
        assertThat(busStopNearbyPlaceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBusStopNearbyPlace() throws Exception {
        // Initialize the database
        busStopNearbyPlaceService.save(busStopNearbyPlace);

        int databaseSizeBeforeDelete = busStopNearbyPlaceRepository.findAll().size();

        // Delete the busStopNearbyPlace
        restBusStopNearbyPlaceMockMvc.perform(delete("/api/bus-stop-nearby-places/{id}", busStopNearbyPlace.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BusStopNearbyPlace> busStopNearbyPlaceList = busStopNearbyPlaceRepository.findAll();
        assertThat(busStopNearbyPlaceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
