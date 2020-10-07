package program.web.rest;

import program.ProgramApp;
import program.config.SecurityBeanOverrideConfiguration;
import program.domain.RouteContent;
import program.repository.RouteContentRepository;
import program.service.RouteContentService;

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
 * Integration tests for the {@link RouteContentResource} REST controller.
 */
@SpringBootTest(classes = { SecurityBeanOverrideConfiguration.class, ProgramApp.class })
@AutoConfigureMockMvc
@WithMockUser
public class RouteContentResourceIT {

    private static final Long DEFAULT_ROUTE_ID = 1L;
    private static final Long UPDATED_ROUTE_ID = 2L;

    private static final Long DEFAULT_CONTENT = 1L;
    private static final Long UPDATED_CONTENT = 2L;

    private static final Integer DEFAULT_DISPLAY = 1;
    private static final Integer UPDATED_DISPLAY = 2;

    @Autowired
    private RouteContentRepository routeContentRepository;

    @Autowired
    private RouteContentService routeContentService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRouteContentMockMvc;

    private RouteContent routeContent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RouteContent createEntity(EntityManager em) {
        RouteContent routeContent = new RouteContent()
            .routeId(DEFAULT_ROUTE_ID)
            .content(DEFAULT_CONTENT)
            .display(DEFAULT_DISPLAY);
        return routeContent;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RouteContent createUpdatedEntity(EntityManager em) {
        RouteContent routeContent = new RouteContent()
            .routeId(UPDATED_ROUTE_ID)
            .content(UPDATED_CONTENT)
            .display(UPDATED_DISPLAY);
        return routeContent;
    }

    @BeforeEach
    public void initTest() {
        routeContent = createEntity(em);
    }

    @Test
    @Transactional
    public void createRouteContent() throws Exception {
        int databaseSizeBeforeCreate = routeContentRepository.findAll().size();
        // Create the RouteContent
        restRouteContentMockMvc.perform(post("/api/route-contents").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(routeContent)))
            .andExpect(status().isCreated());

        // Validate the RouteContent in the database
        List<RouteContent> routeContentList = routeContentRepository.findAll();
        assertThat(routeContentList).hasSize(databaseSizeBeforeCreate + 1);
        RouteContent testRouteContent = routeContentList.get(routeContentList.size() - 1);
        assertThat(testRouteContent.getRouteId()).isEqualTo(DEFAULT_ROUTE_ID);
        assertThat(testRouteContent.getContent()).isEqualTo(DEFAULT_CONTENT);
        assertThat(testRouteContent.getDisplay()).isEqualTo(DEFAULT_DISPLAY);
    }

    @Test
    @Transactional
    public void createRouteContentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = routeContentRepository.findAll().size();

        // Create the RouteContent with an existing ID
        routeContent.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRouteContentMockMvc.perform(post("/api/route-contents").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(routeContent)))
            .andExpect(status().isBadRequest());

        // Validate the RouteContent in the database
        List<RouteContent> routeContentList = routeContentRepository.findAll();
        assertThat(routeContentList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkRouteIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = routeContentRepository.findAll().size();
        // set the field null
        routeContent.setRouteId(null);

        // Create the RouteContent, which fails.


        restRouteContentMockMvc.perform(post("/api/route-contents").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(routeContent)))
            .andExpect(status().isBadRequest());

        List<RouteContent> routeContentList = routeContentRepository.findAll();
        assertThat(routeContentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentIsRequired() throws Exception {
        int databaseSizeBeforeTest = routeContentRepository.findAll().size();
        // set the field null
        routeContent.setContent(null);

        // Create the RouteContent, which fails.


        restRouteContentMockMvc.perform(post("/api/route-contents").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(routeContent)))
            .andExpect(status().isBadRequest());

        List<RouteContent> routeContentList = routeContentRepository.findAll();
        assertThat(routeContentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDisplayIsRequired() throws Exception {
        int databaseSizeBeforeTest = routeContentRepository.findAll().size();
        // set the field null
        routeContent.setDisplay(null);

        // Create the RouteContent, which fails.


        restRouteContentMockMvc.perform(post("/api/route-contents").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(routeContent)))
            .andExpect(status().isBadRequest());

        List<RouteContent> routeContentList = routeContentRepository.findAll();
        assertThat(routeContentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRouteContents() throws Exception {
        // Initialize the database
        routeContentRepository.saveAndFlush(routeContent);

        // Get all the routeContentList
        restRouteContentMockMvc.perform(get("/api/route-contents?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(routeContent.getId().intValue())))
            .andExpect(jsonPath("$.[*].routeId").value(hasItem(DEFAULT_ROUTE_ID.intValue())))
            .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT.intValue())))
            .andExpect(jsonPath("$.[*].display").value(hasItem(DEFAULT_DISPLAY)));
    }
    
    @Test
    @Transactional
    public void getRouteContent() throws Exception {
        // Initialize the database
        routeContentRepository.saveAndFlush(routeContent);

        // Get the routeContent
        restRouteContentMockMvc.perform(get("/api/route-contents/{id}", routeContent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(routeContent.getId().intValue()))
            .andExpect(jsonPath("$.routeId").value(DEFAULT_ROUTE_ID.intValue()))
            .andExpect(jsonPath("$.content").value(DEFAULT_CONTENT.intValue()))
            .andExpect(jsonPath("$.display").value(DEFAULT_DISPLAY));
    }
    @Test
    @Transactional
    public void getNonExistingRouteContent() throws Exception {
        // Get the routeContent
        restRouteContentMockMvc.perform(get("/api/route-contents/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRouteContent() throws Exception {
        // Initialize the database
        routeContentService.save(routeContent);

        int databaseSizeBeforeUpdate = routeContentRepository.findAll().size();

        // Update the routeContent
        RouteContent updatedRouteContent = routeContentRepository.findById(routeContent.getId()).get();
        // Disconnect from session so that the updates on updatedRouteContent are not directly saved in db
        em.detach(updatedRouteContent);
        updatedRouteContent
            .routeId(UPDATED_ROUTE_ID)
            .content(UPDATED_CONTENT)
            .display(UPDATED_DISPLAY);

        restRouteContentMockMvc.perform(put("/api/route-contents").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedRouteContent)))
            .andExpect(status().isOk());

        // Validate the RouteContent in the database
        List<RouteContent> routeContentList = routeContentRepository.findAll();
        assertThat(routeContentList).hasSize(databaseSizeBeforeUpdate);
        RouteContent testRouteContent = routeContentList.get(routeContentList.size() - 1);
        assertThat(testRouteContent.getRouteId()).isEqualTo(UPDATED_ROUTE_ID);
        assertThat(testRouteContent.getContent()).isEqualTo(UPDATED_CONTENT);
        assertThat(testRouteContent.getDisplay()).isEqualTo(UPDATED_DISPLAY);
    }

    @Test
    @Transactional
    public void updateNonExistingRouteContent() throws Exception {
        int databaseSizeBeforeUpdate = routeContentRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRouteContentMockMvc.perform(put("/api/route-contents").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(routeContent)))
            .andExpect(status().isBadRequest());

        // Validate the RouteContent in the database
        List<RouteContent> routeContentList = routeContentRepository.findAll();
        assertThat(routeContentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRouteContent() throws Exception {
        // Initialize the database
        routeContentService.save(routeContent);

        int databaseSizeBeforeDelete = routeContentRepository.findAll().size();

        // Delete the routeContent
        restRouteContentMockMvc.perform(delete("/api/route-contents/{id}", routeContent.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RouteContent> routeContentList = routeContentRepository.findAll();
        assertThat(routeContentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
