package program.web.rest;

import program.ProgramApp;
import program.config.SecurityBeanOverrideConfiguration;
import program.domain.RouteBusStop;
import program.repository.RouteBusStopRepository;
import program.service.RouteBusStopService;

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
 * Integration tests for the {@link RouteBusStopResource} REST controller.
 */
@SpringBootTest(classes = { SecurityBeanOverrideConfiguration.class, ProgramApp.class })
@AutoConfigureMockMvc
@WithMockUser
public class RouteBusStopResourceIT {

    private static final Long DEFAULT_ROUTE_ID = 1L;
    private static final Long UPDATED_ROUTE_ID = 2L;

    private static final Long DEFAULT_BUS_STOP_ID = 1L;
    private static final Long UPDATED_BUS_STOP_ID = 2L;

    private static final Long DEFAULT_ORDER_NO = 1L;
    private static final Long UPDATED_ORDER_NO = 2L;

    @Autowired
    private RouteBusStopRepository routeBusStopRepository;

    @Autowired
    private RouteBusStopService routeBusStopService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRouteBusStopMockMvc;

    private RouteBusStop routeBusStop;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RouteBusStop createEntity(EntityManager em) {
        RouteBusStop routeBusStop = new RouteBusStop()
            .routeId(DEFAULT_ROUTE_ID)
            .busStopId(DEFAULT_BUS_STOP_ID)
            .orderNo(DEFAULT_ORDER_NO);
        return routeBusStop;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RouteBusStop createUpdatedEntity(EntityManager em) {
        RouteBusStop routeBusStop = new RouteBusStop()
            .routeId(UPDATED_ROUTE_ID)
            .busStopId(UPDATED_BUS_STOP_ID)
            .orderNo(UPDATED_ORDER_NO);
        return routeBusStop;
    }

    @BeforeEach
    public void initTest() {
        routeBusStop = createEntity(em);
    }

    @Test
    @Transactional
    public void createRouteBusStop() throws Exception {
        int databaseSizeBeforeCreate = routeBusStopRepository.findAll().size();
        // Create the RouteBusStop
        restRouteBusStopMockMvc.perform(post("/api/route-bus-stops").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(routeBusStop)))
            .andExpect(status().isCreated());

        // Validate the RouteBusStop in the database
        List<RouteBusStop> routeBusStopList = routeBusStopRepository.findAll();
        assertThat(routeBusStopList).hasSize(databaseSizeBeforeCreate + 1);
        RouteBusStop testRouteBusStop = routeBusStopList.get(routeBusStopList.size() - 1);
        assertThat(testRouteBusStop.getRouteId()).isEqualTo(DEFAULT_ROUTE_ID);
        assertThat(testRouteBusStop.getBusStopId()).isEqualTo(DEFAULT_BUS_STOP_ID);
        assertThat(testRouteBusStop.getOrderNo()).isEqualTo(DEFAULT_ORDER_NO);
    }

    @Test
    @Transactional
    public void createRouteBusStopWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = routeBusStopRepository.findAll().size();

        // Create the RouteBusStop with an existing ID
        routeBusStop.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRouteBusStopMockMvc.perform(post("/api/route-bus-stops").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(routeBusStop)))
            .andExpect(status().isBadRequest());

        // Validate the RouteBusStop in the database
        List<RouteBusStop> routeBusStopList = routeBusStopRepository.findAll();
        assertThat(routeBusStopList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkRouteIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = routeBusStopRepository.findAll().size();
        // set the field null
        routeBusStop.setRouteId(null);

        // Create the RouteBusStop, which fails.


        restRouteBusStopMockMvc.perform(post("/api/route-bus-stops").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(routeBusStop)))
            .andExpect(status().isBadRequest());

        List<RouteBusStop> routeBusStopList = routeBusStopRepository.findAll();
        assertThat(routeBusStopList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBusStopIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = routeBusStopRepository.findAll().size();
        // set the field null
        routeBusStop.setBusStopId(null);

        // Create the RouteBusStop, which fails.


        restRouteBusStopMockMvc.perform(post("/api/route-bus-stops").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(routeBusStop)))
            .andExpect(status().isBadRequest());

        List<RouteBusStop> routeBusStopList = routeBusStopRepository.findAll();
        assertThat(routeBusStopList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOrderNoIsRequired() throws Exception {
        int databaseSizeBeforeTest = routeBusStopRepository.findAll().size();
        // set the field null
        routeBusStop.setOrderNo(null);

        // Create the RouteBusStop, which fails.


        restRouteBusStopMockMvc.perform(post("/api/route-bus-stops").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(routeBusStop)))
            .andExpect(status().isBadRequest());

        List<RouteBusStop> routeBusStopList = routeBusStopRepository.findAll();
        assertThat(routeBusStopList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRouteBusStops() throws Exception {
        // Initialize the database
        routeBusStopRepository.saveAndFlush(routeBusStop);

        // Get all the routeBusStopList
        restRouteBusStopMockMvc.perform(get("/api/route-bus-stops?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(routeBusStop.getId().intValue())))
            .andExpect(jsonPath("$.[*].routeId").value(hasItem(DEFAULT_ROUTE_ID.intValue())))
            .andExpect(jsonPath("$.[*].busStopId").value(hasItem(DEFAULT_BUS_STOP_ID.intValue())))
            .andExpect(jsonPath("$.[*].orderNo").value(hasItem(DEFAULT_ORDER_NO.intValue())));
    }
    
    @Test
    @Transactional
    public void getRouteBusStop() throws Exception {
        // Initialize the database
        routeBusStopRepository.saveAndFlush(routeBusStop);

        // Get the routeBusStop
        restRouteBusStopMockMvc.perform(get("/api/route-bus-stops/{id}", routeBusStop.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(routeBusStop.getId().intValue()))
            .andExpect(jsonPath("$.routeId").value(DEFAULT_ROUTE_ID.intValue()))
            .andExpect(jsonPath("$.busStopId").value(DEFAULT_BUS_STOP_ID.intValue()))
            .andExpect(jsonPath("$.orderNo").value(DEFAULT_ORDER_NO.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingRouteBusStop() throws Exception {
        // Get the routeBusStop
        restRouteBusStopMockMvc.perform(get("/api/route-bus-stops/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRouteBusStop() throws Exception {
        // Initialize the database
        routeBusStopService.save(routeBusStop);

        int databaseSizeBeforeUpdate = routeBusStopRepository.findAll().size();

        // Update the routeBusStop
        RouteBusStop updatedRouteBusStop = routeBusStopRepository.findById(routeBusStop.getId()).get();
        // Disconnect from session so that the updates on updatedRouteBusStop are not directly saved in db
        em.detach(updatedRouteBusStop);
        updatedRouteBusStop
            .routeId(UPDATED_ROUTE_ID)
            .busStopId(UPDATED_BUS_STOP_ID)
            .orderNo(UPDATED_ORDER_NO);

        restRouteBusStopMockMvc.perform(put("/api/route-bus-stops").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedRouteBusStop)))
            .andExpect(status().isOk());

        // Validate the RouteBusStop in the database
        List<RouteBusStop> routeBusStopList = routeBusStopRepository.findAll();
        assertThat(routeBusStopList).hasSize(databaseSizeBeforeUpdate);
        RouteBusStop testRouteBusStop = routeBusStopList.get(routeBusStopList.size() - 1);
        assertThat(testRouteBusStop.getRouteId()).isEqualTo(UPDATED_ROUTE_ID);
        assertThat(testRouteBusStop.getBusStopId()).isEqualTo(UPDATED_BUS_STOP_ID);
        assertThat(testRouteBusStop.getOrderNo()).isEqualTo(UPDATED_ORDER_NO);
    }

    @Test
    @Transactional
    public void updateNonExistingRouteBusStop() throws Exception {
        int databaseSizeBeforeUpdate = routeBusStopRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRouteBusStopMockMvc.perform(put("/api/route-bus-stops").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(routeBusStop)))
            .andExpect(status().isBadRequest());

        // Validate the RouteBusStop in the database
        List<RouteBusStop> routeBusStopList = routeBusStopRepository.findAll();
        assertThat(routeBusStopList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRouteBusStop() throws Exception {
        // Initialize the database
        routeBusStopService.save(routeBusStop);

        int databaseSizeBeforeDelete = routeBusStopRepository.findAll().size();

        // Delete the routeBusStop
        restRouteBusStopMockMvc.perform(delete("/api/route-bus-stops/{id}", routeBusStop.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RouteBusStop> routeBusStopList = routeBusStopRepository.findAll();
        assertThat(routeBusStopList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
