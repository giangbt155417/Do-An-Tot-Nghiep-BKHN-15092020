package program.web.rest;

import program.ProgramApp;
import program.config.SecurityBeanOverrideConfiguration;
import program.domain.BusStop;
import program.repository.BusStopRepository;
import program.service.BusStopService;
import program.service.dto.BusStopDTO;
import program.service.mapper.BusStopMapper;

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
 * Integration tests for the {@link BusStopResource} REST controller.
 */
@SpringBootTest(classes = { SecurityBeanOverrideConfiguration.class, ProgramApp.class })
@AutoConfigureMockMvc
@WithMockUser
public class BusStopResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BUS_STOP_NO = "AAAAAAAAAA";
    private static final String UPDATED_BUS_STOP_NO = "BBBBBBBBBB";

    private static final String DEFAULT_SUFFIX = "AAAAAAAAAA";
    private static final String UPDATED_SUFFIX = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_DATE = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private BusStopRepository busStopRepository;

    @Autowired
    private BusStopMapper busStopMapper;

    @Autowired
    private BusStopService busStopService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBusStopMockMvc;

    private BusStop busStop;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BusStop createEntity(EntityManager em) {
        BusStop busStop = new BusStop()
            .name(DEFAULT_NAME)
            .busStopNo(DEFAULT_BUS_STOP_NO)
            .suffix(DEFAULT_SUFFIX)
            .createdDate(DEFAULT_CREATED_DATE)
            .description(DEFAULT_DESCRIPTION);
        return busStop;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BusStop createUpdatedEntity(EntityManager em) {
        BusStop busStop = new BusStop()
            .name(UPDATED_NAME)
            .busStopNo(UPDATED_BUS_STOP_NO)
            .suffix(UPDATED_SUFFIX)
            .createdDate(UPDATED_CREATED_DATE)
            .description(UPDATED_DESCRIPTION);
        return busStop;
    }

    @BeforeEach
    public void initTest() {
        busStop = createEntity(em);
    }

    @Test
    @Transactional
    public void createBusStop() throws Exception {
        int databaseSizeBeforeCreate = busStopRepository.findAll().size();
        // Create the BusStop
        BusStopDTO busStopDTO = busStopMapper.toDto(busStop);
        restBusStopMockMvc.perform(post("/api/bus-stops").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(busStopDTO)))
            .andExpect(status().isCreated());

        // Validate the BusStop in the database
        List<BusStop> busStopList = busStopRepository.findAll();
        assertThat(busStopList).hasSize(databaseSizeBeforeCreate + 1);
        BusStop testBusStop = busStopList.get(busStopList.size() - 1);
        assertThat(testBusStop.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testBusStop.getBusStopNo()).isEqualTo(DEFAULT_BUS_STOP_NO);
        assertThat(testBusStop.getSuffix()).isEqualTo(DEFAULT_SUFFIX);
        assertThat(testBusStop.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testBusStop.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createBusStopWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = busStopRepository.findAll().size();

        // Create the BusStop with an existing ID
        busStop.setId(1L);
        BusStopDTO busStopDTO = busStopMapper.toDto(busStop);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBusStopMockMvc.perform(post("/api/bus-stops").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(busStopDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BusStop in the database
        List<BusStop> busStopList = busStopRepository.findAll();
        assertThat(busStopList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = busStopRepository.findAll().size();
        // set the field null
        busStop.setName(null);

        // Create the BusStop, which fails.
        BusStopDTO busStopDTO = busStopMapper.toDto(busStop);


        restBusStopMockMvc.perform(post("/api/bus-stops").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(busStopDTO)))
            .andExpect(status().isBadRequest());

        List<BusStop> busStopList = busStopRepository.findAll();
        assertThat(busStopList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBusStopNoIsRequired() throws Exception {
        int databaseSizeBeforeTest = busStopRepository.findAll().size();
        // set the field null
        busStop.setBusStopNo(null);

        // Create the BusStop, which fails.
        BusStopDTO busStopDTO = busStopMapper.toDto(busStop);


        restBusStopMockMvc.perform(post("/api/bus-stops").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(busStopDTO)))
            .andExpect(status().isBadRequest());

        List<BusStop> busStopList = busStopRepository.findAll();
        assertThat(busStopList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSuffixIsRequired() throws Exception {
        int databaseSizeBeforeTest = busStopRepository.findAll().size();
        // set the field null
        busStop.setSuffix(null);

        // Create the BusStop, which fails.
        BusStopDTO busStopDTO = busStopMapper.toDto(busStop);


        restBusStopMockMvc.perform(post("/api/bus-stops").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(busStopDTO)))
            .andExpect(status().isBadRequest());

        List<BusStop> busStopList = busStopRepository.findAll();
        assertThat(busStopList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = busStopRepository.findAll().size();
        // set the field null
        busStop.setCreatedDate(null);

        // Create the BusStop, which fails.
        BusStopDTO busStopDTO = busStopMapper.toDto(busStop);


        restBusStopMockMvc.perform(post("/api/bus-stops").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(busStopDTO)))
            .andExpect(status().isBadRequest());

        List<BusStop> busStopList = busStopRepository.findAll();
        assertThat(busStopList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBusStops() throws Exception {
        // Initialize the database
        busStopRepository.saveAndFlush(busStop);

        // Get all the busStopList
        restBusStopMockMvc.perform(get("/api/bus-stops?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(busStop.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].busStopNo").value(hasItem(DEFAULT_BUS_STOP_NO)))
            .andExpect(jsonPath("$.[*].suffix").value(hasItem(DEFAULT_SUFFIX)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getBusStop() throws Exception {
        // Initialize the database
        busStopRepository.saveAndFlush(busStop);

        // Get the busStop
        restBusStopMockMvc.perform(get("/api/bus-stops/{id}", busStop.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(busStop.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.busStopNo").value(DEFAULT_BUS_STOP_NO))
            .andExpect(jsonPath("$.suffix").value(DEFAULT_SUFFIX))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }
    @Test
    @Transactional
    public void getNonExistingBusStop() throws Exception {
        // Get the busStop
        restBusStopMockMvc.perform(get("/api/bus-stops/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBusStop() throws Exception {
        // Initialize the database
        busStopRepository.saveAndFlush(busStop);

        int databaseSizeBeforeUpdate = busStopRepository.findAll().size();

        // Update the busStop
        BusStop updatedBusStop = busStopRepository.findById(busStop.getId()).get();
        // Disconnect from session so that the updates on updatedBusStop are not directly saved in db
        em.detach(updatedBusStop);
        updatedBusStop
            .name(UPDATED_NAME)
            .busStopNo(UPDATED_BUS_STOP_NO)
            .suffix(UPDATED_SUFFIX)
            .createdDate(UPDATED_CREATED_DATE)
            .description(UPDATED_DESCRIPTION);
        BusStopDTO busStopDTO = busStopMapper.toDto(updatedBusStop);

        restBusStopMockMvc.perform(put("/api/bus-stops").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(busStopDTO)))
            .andExpect(status().isOk());

        // Validate the BusStop in the database
        List<BusStop> busStopList = busStopRepository.findAll();
        assertThat(busStopList).hasSize(databaseSizeBeforeUpdate);
        BusStop testBusStop = busStopList.get(busStopList.size() - 1);
        assertThat(testBusStop.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testBusStop.getBusStopNo()).isEqualTo(UPDATED_BUS_STOP_NO);
        assertThat(testBusStop.getSuffix()).isEqualTo(UPDATED_SUFFIX);
        assertThat(testBusStop.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testBusStop.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingBusStop() throws Exception {
        int databaseSizeBeforeUpdate = busStopRepository.findAll().size();

        // Create the BusStop
        BusStopDTO busStopDTO = busStopMapper.toDto(busStop);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBusStopMockMvc.perform(put("/api/bus-stops").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(busStopDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BusStop in the database
        List<BusStop> busStopList = busStopRepository.findAll();
        assertThat(busStopList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBusStop() throws Exception {
        // Initialize the database
        busStopRepository.saveAndFlush(busStop);

        int databaseSizeBeforeDelete = busStopRepository.findAll().size();

        // Delete the busStop
        restBusStopMockMvc.perform(delete("/api/bus-stops/{id}", busStop.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BusStop> busStopList = busStopRepository.findAll();
        assertThat(busStopList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
