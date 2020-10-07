package program.web.rest;

import program.ProgramApp;
import program.config.SecurityBeanOverrideConfiguration;
import program.domain.TextArea;
import program.repository.TextAreaRepository;
import program.service.TextAreaService;
import program.service.dto.TextAreaDTO;
import program.service.mapper.TextAreaMapper;

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
 * Integration tests for the {@link TextAreaResource} REST controller.
 */
@SpringBootTest(classes = { SecurityBeanOverrideConfiguration.class, ProgramApp.class })
@AutoConfigureMockMvc
@WithMockUser
public class TextAreaResourceIT {

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

    private static final String DEFAULT_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_TEXT = "BBBBBBBBBB";

    private static final String DEFAULT_FONT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FONT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FONT_SIZE = "AAAAAAAAAA";
    private static final String UPDATED_FONT_SIZE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_BOLD = false;
    private static final Boolean UPDATED_IS_BOLD = true;

    private static final Boolean DEFAULT_IS_ITALIC = false;
    private static final Boolean UPDATED_IS_ITALIC = true;

    private static final Integer DEFAULT_HORIZONTAL_ALIGNMENT = 1;
    private static final Integer UPDATED_HORIZONTAL_ALIGNMENT = 2;

    private static final Integer DEFAULT_VERTICAL_ALIGNMENT = 1;
    private static final Integer UPDATED_VERTICAL_ALIGNMENT = 2;

    private static final String DEFAULT_FONT_COLOR = "AAAAAAAAAA";
    private static final String UPDATED_FONT_COLOR = "BBBBBBBBBB";

    private static final String DEFAULT_BACKGROUND_COLOR = "AAAAAAAAAA";
    private static final String UPDATED_BACKGROUND_COLOR = "BBBBBBBBBB";

    private static final Integer DEFAULT_SCROLL_STATUS = 1;
    private static final Integer UPDATED_SCROLL_STATUS = 2;

    private static final Integer DEFAULT_SCROLL_DIRECTION = 1;
    private static final Integer UPDATED_SCROLL_DIRECTION = 2;

    private static final Integer DEFAULT_SCROLL_SPEED = 1;
    private static final Integer UPDATED_SCROLL_SPEED = 2;

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
    private TextAreaRepository textAreaRepository;

    @Autowired
    private TextAreaMapper textAreaMapper;

    @Autowired
    private TextAreaService textAreaService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTextAreaMockMvc;

    private TextArea textArea;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TextArea createEntity(EntityManager em) {
        TextArea textArea = new TextArea()
            .name(DEFAULT_NAME)
            .posX(DEFAULT_POS_X)
            .posY(DEFAULT_POS_Y)
            .width(DEFAULT_WIDTH)
            .height(DEFAULT_HEIGHT)
            .index(DEFAULT_INDEX)
            .isFix(DEFAULT_IS_FIX)
            .text(DEFAULT_TEXT)
            .fontName(DEFAULT_FONT_NAME)
            .fontSize(DEFAULT_FONT_SIZE)
            .isBold(DEFAULT_IS_BOLD)
            .isItalic(DEFAULT_IS_ITALIC)
            .horizontalAlignment(DEFAULT_HORIZONTAL_ALIGNMENT)
            .verticalAlignment(DEFAULT_VERTICAL_ALIGNMENT)
            .fontColor(DEFAULT_FONT_COLOR)
            .backgroundColor(DEFAULT_BACKGROUND_COLOR)
            .scrollStatus(DEFAULT_SCROLL_STATUS)
            .scrollDirection(DEFAULT_SCROLL_DIRECTION)
            .scrollSpeed(DEFAULT_SCROLL_SPEED)
            .linkData(DEFAULT_LINK_DATA)
            .on(DEFAULT_ON)
            .off(DEFAULT_OFF)
            .type(DEFAULT_TYPE)
            .contentId(DEFAULT_CONTENT_ID);
        return textArea;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TextArea createUpdatedEntity(EntityManager em) {
        TextArea textArea = new TextArea()
            .name(UPDATED_NAME)
            .posX(UPDATED_POS_X)
            .posY(UPDATED_POS_Y)
            .width(UPDATED_WIDTH)
            .height(UPDATED_HEIGHT)
            .index(UPDATED_INDEX)
            .isFix(UPDATED_IS_FIX)
            .text(UPDATED_TEXT)
            .fontName(UPDATED_FONT_NAME)
            .fontSize(UPDATED_FONT_SIZE)
            .isBold(UPDATED_IS_BOLD)
            .isItalic(UPDATED_IS_ITALIC)
            .horizontalAlignment(UPDATED_HORIZONTAL_ALIGNMENT)
            .verticalAlignment(UPDATED_VERTICAL_ALIGNMENT)
            .fontColor(UPDATED_FONT_COLOR)
            .backgroundColor(UPDATED_BACKGROUND_COLOR)
            .scrollStatus(UPDATED_SCROLL_STATUS)
            .scrollDirection(UPDATED_SCROLL_DIRECTION)
            .scrollSpeed(UPDATED_SCROLL_SPEED)
            .linkData(UPDATED_LINK_DATA)
            .on(UPDATED_ON)
            .off(UPDATED_OFF)
            .type(UPDATED_TYPE)
            .contentId(UPDATED_CONTENT_ID);
        return textArea;
    }

    @BeforeEach
    public void initTest() {
        textArea = createEntity(em);
    }

    @Test
    @Transactional
    public void createTextArea() throws Exception {
        int databaseSizeBeforeCreate = textAreaRepository.findAll().size();
        // Create the TextArea
        TextAreaDTO textAreaDTO = textAreaMapper.toDto(textArea);
        restTextAreaMockMvc.perform(post("/api/text-areas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(textAreaDTO)))
            .andExpect(status().isCreated());

        // Validate the TextArea in the database
        List<TextArea> textAreaList = textAreaRepository.findAll();
        assertThat(textAreaList).hasSize(databaseSizeBeforeCreate + 1);
        TextArea testTextArea = textAreaList.get(textAreaList.size() - 1);
        assertThat(testTextArea.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testTextArea.getPosX()).isEqualTo(DEFAULT_POS_X);
        assertThat(testTextArea.getPosY()).isEqualTo(DEFAULT_POS_Y);
        assertThat(testTextArea.getWidth()).isEqualTo(DEFAULT_WIDTH);
        assertThat(testTextArea.getHeight()).isEqualTo(DEFAULT_HEIGHT);
        assertThat(testTextArea.getIndex()).isEqualTo(DEFAULT_INDEX);
        assertThat(testTextArea.isIsFix()).isEqualTo(DEFAULT_IS_FIX);
        assertThat(testTextArea.getText()).isEqualTo(DEFAULT_TEXT);
        assertThat(testTextArea.getFontName()).isEqualTo(DEFAULT_FONT_NAME);
        assertThat(testTextArea.getFontSize()).isEqualTo(DEFAULT_FONT_SIZE);
        assertThat(testTextArea.isIsBold()).isEqualTo(DEFAULT_IS_BOLD);
        assertThat(testTextArea.isIsItalic()).isEqualTo(DEFAULT_IS_ITALIC);
        assertThat(testTextArea.getHorizontalAlignment()).isEqualTo(DEFAULT_HORIZONTAL_ALIGNMENT);
        assertThat(testTextArea.getVerticalAlignment()).isEqualTo(DEFAULT_VERTICAL_ALIGNMENT);
        assertThat(testTextArea.getFontColor()).isEqualTo(DEFAULT_FONT_COLOR);
        assertThat(testTextArea.getBackgroundColor()).isEqualTo(DEFAULT_BACKGROUND_COLOR);
        assertThat(testTextArea.getScrollStatus()).isEqualTo(DEFAULT_SCROLL_STATUS);
        assertThat(testTextArea.getScrollDirection()).isEqualTo(DEFAULT_SCROLL_DIRECTION);
        assertThat(testTextArea.getScrollSpeed()).isEqualTo(DEFAULT_SCROLL_SPEED);
        assertThat(testTextArea.getLinkData()).isEqualTo(DEFAULT_LINK_DATA);
        assertThat(testTextArea.getOn()).isEqualTo(DEFAULT_ON);
        assertThat(testTextArea.getOff()).isEqualTo(DEFAULT_OFF);
        assertThat(testTextArea.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testTextArea.getContentId()).isEqualTo(DEFAULT_CONTENT_ID);
    }

    @Test
    @Transactional
    public void createTextAreaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = textAreaRepository.findAll().size();

        // Create the TextArea with an existing ID
        textArea.setId(1L);
        TextAreaDTO textAreaDTO = textAreaMapper.toDto(textArea);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTextAreaMockMvc.perform(post("/api/text-areas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(textAreaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TextArea in the database
        List<TextArea> textAreaList = textAreaRepository.findAll();
        assertThat(textAreaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = textAreaRepository.findAll().size();
        // set the field null
        textArea.setName(null);

        // Create the TextArea, which fails.
        TextAreaDTO textAreaDTO = textAreaMapper.toDto(textArea);


        restTextAreaMockMvc.perform(post("/api/text-areas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(textAreaDTO)))
            .andExpect(status().isBadRequest());

        List<TextArea> textAreaList = textAreaRepository.findAll();
        assertThat(textAreaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPosXIsRequired() throws Exception {
        int databaseSizeBeforeTest = textAreaRepository.findAll().size();
        // set the field null
        textArea.setPosX(null);

        // Create the TextArea, which fails.
        TextAreaDTO textAreaDTO = textAreaMapper.toDto(textArea);


        restTextAreaMockMvc.perform(post("/api/text-areas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(textAreaDTO)))
            .andExpect(status().isBadRequest());

        List<TextArea> textAreaList = textAreaRepository.findAll();
        assertThat(textAreaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPosYIsRequired() throws Exception {
        int databaseSizeBeforeTest = textAreaRepository.findAll().size();
        // set the field null
        textArea.setPosY(null);

        // Create the TextArea, which fails.
        TextAreaDTO textAreaDTO = textAreaMapper.toDto(textArea);


        restTextAreaMockMvc.perform(post("/api/text-areas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(textAreaDTO)))
            .andExpect(status().isBadRequest());

        List<TextArea> textAreaList = textAreaRepository.findAll();
        assertThat(textAreaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWidthIsRequired() throws Exception {
        int databaseSizeBeforeTest = textAreaRepository.findAll().size();
        // set the field null
        textArea.setWidth(null);

        // Create the TextArea, which fails.
        TextAreaDTO textAreaDTO = textAreaMapper.toDto(textArea);


        restTextAreaMockMvc.perform(post("/api/text-areas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(textAreaDTO)))
            .andExpect(status().isBadRequest());

        List<TextArea> textAreaList = textAreaRepository.findAll();
        assertThat(textAreaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHeightIsRequired() throws Exception {
        int databaseSizeBeforeTest = textAreaRepository.findAll().size();
        // set the field null
        textArea.setHeight(null);

        // Create the TextArea, which fails.
        TextAreaDTO textAreaDTO = textAreaMapper.toDto(textArea);


        restTextAreaMockMvc.perform(post("/api/text-areas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(textAreaDTO)))
            .andExpect(status().isBadRequest());

        List<TextArea> textAreaList = textAreaRepository.findAll();
        assertThat(textAreaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIndexIsRequired() throws Exception {
        int databaseSizeBeforeTest = textAreaRepository.findAll().size();
        // set the field null
        textArea.setIndex(null);

        // Create the TextArea, which fails.
        TextAreaDTO textAreaDTO = textAreaMapper.toDto(textArea);


        restTextAreaMockMvc.perform(post("/api/text-areas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(textAreaDTO)))
            .andExpect(status().isBadRequest());

        List<TextArea> textAreaList = textAreaRepository.findAll();
        assertThat(textAreaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsFixIsRequired() throws Exception {
        int databaseSizeBeforeTest = textAreaRepository.findAll().size();
        // set the field null
        textArea.setIsFix(null);

        // Create the TextArea, which fails.
        TextAreaDTO textAreaDTO = textAreaMapper.toDto(textArea);


        restTextAreaMockMvc.perform(post("/api/text-areas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(textAreaDTO)))
            .andExpect(status().isBadRequest());

        List<TextArea> textAreaList = textAreaRepository.findAll();
        assertThat(textAreaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTextIsRequired() throws Exception {
        int databaseSizeBeforeTest = textAreaRepository.findAll().size();
        // set the field null
        textArea.setText(null);

        // Create the TextArea, which fails.
        TextAreaDTO textAreaDTO = textAreaMapper.toDto(textArea);


        restTextAreaMockMvc.perform(post("/api/text-areas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(textAreaDTO)))
            .andExpect(status().isBadRequest());

        List<TextArea> textAreaList = textAreaRepository.findAll();
        assertThat(textAreaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFontNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = textAreaRepository.findAll().size();
        // set the field null
        textArea.setFontName(null);

        // Create the TextArea, which fails.
        TextAreaDTO textAreaDTO = textAreaMapper.toDto(textArea);


        restTextAreaMockMvc.perform(post("/api/text-areas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(textAreaDTO)))
            .andExpect(status().isBadRequest());

        List<TextArea> textAreaList = textAreaRepository.findAll();
        assertThat(textAreaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFontSizeIsRequired() throws Exception {
        int databaseSizeBeforeTest = textAreaRepository.findAll().size();
        // set the field null
        textArea.setFontSize(null);

        // Create the TextArea, which fails.
        TextAreaDTO textAreaDTO = textAreaMapper.toDto(textArea);


        restTextAreaMockMvc.perform(post("/api/text-areas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(textAreaDTO)))
            .andExpect(status().isBadRequest());

        List<TextArea> textAreaList = textAreaRepository.findAll();
        assertThat(textAreaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHorizontalAlignmentIsRequired() throws Exception {
        int databaseSizeBeforeTest = textAreaRepository.findAll().size();
        // set the field null
        textArea.setHorizontalAlignment(null);

        // Create the TextArea, which fails.
        TextAreaDTO textAreaDTO = textAreaMapper.toDto(textArea);


        restTextAreaMockMvc.perform(post("/api/text-areas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(textAreaDTO)))
            .andExpect(status().isBadRequest());

        List<TextArea> textAreaList = textAreaRepository.findAll();
        assertThat(textAreaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkVerticalAlignmentIsRequired() throws Exception {
        int databaseSizeBeforeTest = textAreaRepository.findAll().size();
        // set the field null
        textArea.setVerticalAlignment(null);

        // Create the TextArea, which fails.
        TextAreaDTO textAreaDTO = textAreaMapper.toDto(textArea);


        restTextAreaMockMvc.perform(post("/api/text-areas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(textAreaDTO)))
            .andExpect(status().isBadRequest());

        List<TextArea> textAreaList = textAreaRepository.findAll();
        assertThat(textAreaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFontColorIsRequired() throws Exception {
        int databaseSizeBeforeTest = textAreaRepository.findAll().size();
        // set the field null
        textArea.setFontColor(null);

        // Create the TextArea, which fails.
        TextAreaDTO textAreaDTO = textAreaMapper.toDto(textArea);


        restTextAreaMockMvc.perform(post("/api/text-areas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(textAreaDTO)))
            .andExpect(status().isBadRequest());

        List<TextArea> textAreaList = textAreaRepository.findAll();
        assertThat(textAreaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBackgroundColorIsRequired() throws Exception {
        int databaseSizeBeforeTest = textAreaRepository.findAll().size();
        // set the field null
        textArea.setBackgroundColor(null);

        // Create the TextArea, which fails.
        TextAreaDTO textAreaDTO = textAreaMapper.toDto(textArea);


        restTextAreaMockMvc.perform(post("/api/text-areas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(textAreaDTO)))
            .andExpect(status().isBadRequest());

        List<TextArea> textAreaList = textAreaRepository.findAll();
        assertThat(textAreaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkScrollStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = textAreaRepository.findAll().size();
        // set the field null
        textArea.setScrollStatus(null);

        // Create the TextArea, which fails.
        TextAreaDTO textAreaDTO = textAreaMapper.toDto(textArea);


        restTextAreaMockMvc.perform(post("/api/text-areas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(textAreaDTO)))
            .andExpect(status().isBadRequest());

        List<TextArea> textAreaList = textAreaRepository.findAll();
        assertThat(textAreaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkScrollDirectionIsRequired() throws Exception {
        int databaseSizeBeforeTest = textAreaRepository.findAll().size();
        // set the field null
        textArea.setScrollDirection(null);

        // Create the TextArea, which fails.
        TextAreaDTO textAreaDTO = textAreaMapper.toDto(textArea);


        restTextAreaMockMvc.perform(post("/api/text-areas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(textAreaDTO)))
            .andExpect(status().isBadRequest());

        List<TextArea> textAreaList = textAreaRepository.findAll();
        assertThat(textAreaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkScrollSpeedIsRequired() throws Exception {
        int databaseSizeBeforeTest = textAreaRepository.findAll().size();
        // set the field null
        textArea.setScrollSpeed(null);

        // Create the TextArea, which fails.
        TextAreaDTO textAreaDTO = textAreaMapper.toDto(textArea);


        restTextAreaMockMvc.perform(post("/api/text-areas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(textAreaDTO)))
            .andExpect(status().isBadRequest());

        List<TextArea> textAreaList = textAreaRepository.findAll();
        assertThat(textAreaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOnIsRequired() throws Exception {
        int databaseSizeBeforeTest = textAreaRepository.findAll().size();
        // set the field null
        textArea.setOn(null);

        // Create the TextArea, which fails.
        TextAreaDTO textAreaDTO = textAreaMapper.toDto(textArea);


        restTextAreaMockMvc.perform(post("/api/text-areas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(textAreaDTO)))
            .andExpect(status().isBadRequest());

        List<TextArea> textAreaList = textAreaRepository.findAll();
        assertThat(textAreaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOffIsRequired() throws Exception {
        int databaseSizeBeforeTest = textAreaRepository.findAll().size();
        // set the field null
        textArea.setOff(null);

        // Create the TextArea, which fails.
        TextAreaDTO textAreaDTO = textAreaMapper.toDto(textArea);


        restTextAreaMockMvc.perform(post("/api/text-areas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(textAreaDTO)))
            .andExpect(status().isBadRequest());

        List<TextArea> textAreaList = textAreaRepository.findAll();
        assertThat(textAreaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = textAreaRepository.findAll().size();
        // set the field null
        textArea.setType(null);

        // Create the TextArea, which fails.
        TextAreaDTO textAreaDTO = textAreaMapper.toDto(textArea);


        restTextAreaMockMvc.perform(post("/api/text-areas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(textAreaDTO)))
            .andExpect(status().isBadRequest());

        List<TextArea> textAreaList = textAreaRepository.findAll();
        assertThat(textAreaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = textAreaRepository.findAll().size();
        // set the field null
        textArea.setContentId(null);

        // Create the TextArea, which fails.
        TextAreaDTO textAreaDTO = textAreaMapper.toDto(textArea);


        restTextAreaMockMvc.perform(post("/api/text-areas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(textAreaDTO)))
            .andExpect(status().isBadRequest());

        List<TextArea> textAreaList = textAreaRepository.findAll();
        assertThat(textAreaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTextAreas() throws Exception {
        // Initialize the database
        textAreaRepository.saveAndFlush(textArea);

        // Get all the textAreaList
        restTextAreaMockMvc.perform(get("/api/text-areas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(textArea.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].posX").value(hasItem(DEFAULT_POS_X)))
            .andExpect(jsonPath("$.[*].posY").value(hasItem(DEFAULT_POS_Y)))
            .andExpect(jsonPath("$.[*].width").value(hasItem(DEFAULT_WIDTH)))
            .andExpect(jsonPath("$.[*].height").value(hasItem(DEFAULT_HEIGHT)))
            .andExpect(jsonPath("$.[*].index").value(hasItem(DEFAULT_INDEX)))
            .andExpect(jsonPath("$.[*].isFix").value(hasItem(DEFAULT_IS_FIX.booleanValue())))
            .andExpect(jsonPath("$.[*].text").value(hasItem(DEFAULT_TEXT)))
            .andExpect(jsonPath("$.[*].fontName").value(hasItem(DEFAULT_FONT_NAME)))
            .andExpect(jsonPath("$.[*].fontSize").value(hasItem(DEFAULT_FONT_SIZE)))
            .andExpect(jsonPath("$.[*].isBold").value(hasItem(DEFAULT_IS_BOLD.booleanValue())))
            .andExpect(jsonPath("$.[*].isItalic").value(hasItem(DEFAULT_IS_ITALIC.booleanValue())))
            .andExpect(jsonPath("$.[*].horizontalAlignment").value(hasItem(DEFAULT_HORIZONTAL_ALIGNMENT)))
            .andExpect(jsonPath("$.[*].verticalAlignment").value(hasItem(DEFAULT_VERTICAL_ALIGNMENT)))
            .andExpect(jsonPath("$.[*].fontColor").value(hasItem(DEFAULT_FONT_COLOR)))
            .andExpect(jsonPath("$.[*].backgroundColor").value(hasItem(DEFAULT_BACKGROUND_COLOR)))
            .andExpect(jsonPath("$.[*].scrollStatus").value(hasItem(DEFAULT_SCROLL_STATUS)))
            .andExpect(jsonPath("$.[*].scrollDirection").value(hasItem(DEFAULT_SCROLL_DIRECTION)))
            .andExpect(jsonPath("$.[*].scrollSpeed").value(hasItem(DEFAULT_SCROLL_SPEED)))
            .andExpect(jsonPath("$.[*].linkData").value(hasItem(DEFAULT_LINK_DATA)))
            .andExpect(jsonPath("$.[*].on").value(hasItem(DEFAULT_ON)))
            .andExpect(jsonPath("$.[*].off").value(hasItem(DEFAULT_OFF)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].contentId").value(hasItem(DEFAULT_CONTENT_ID.intValue())));
    }
    
    @Test
    @Transactional
    public void getTextArea() throws Exception {
        // Initialize the database
        textAreaRepository.saveAndFlush(textArea);

        // Get the textArea
        restTextAreaMockMvc.perform(get("/api/text-areas/{id}", textArea.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(textArea.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.posX").value(DEFAULT_POS_X))
            .andExpect(jsonPath("$.posY").value(DEFAULT_POS_Y))
            .andExpect(jsonPath("$.width").value(DEFAULT_WIDTH))
            .andExpect(jsonPath("$.height").value(DEFAULT_HEIGHT))
            .andExpect(jsonPath("$.index").value(DEFAULT_INDEX))
            .andExpect(jsonPath("$.isFix").value(DEFAULT_IS_FIX.booleanValue()))
            .andExpect(jsonPath("$.text").value(DEFAULT_TEXT))
            .andExpect(jsonPath("$.fontName").value(DEFAULT_FONT_NAME))
            .andExpect(jsonPath("$.fontSize").value(DEFAULT_FONT_SIZE))
            .andExpect(jsonPath("$.isBold").value(DEFAULT_IS_BOLD.booleanValue()))
            .andExpect(jsonPath("$.isItalic").value(DEFAULT_IS_ITALIC.booleanValue()))
            .andExpect(jsonPath("$.horizontalAlignment").value(DEFAULT_HORIZONTAL_ALIGNMENT))
            .andExpect(jsonPath("$.verticalAlignment").value(DEFAULT_VERTICAL_ALIGNMENT))
            .andExpect(jsonPath("$.fontColor").value(DEFAULT_FONT_COLOR))
            .andExpect(jsonPath("$.backgroundColor").value(DEFAULT_BACKGROUND_COLOR))
            .andExpect(jsonPath("$.scrollStatus").value(DEFAULT_SCROLL_STATUS))
            .andExpect(jsonPath("$.scrollDirection").value(DEFAULT_SCROLL_DIRECTION))
            .andExpect(jsonPath("$.scrollSpeed").value(DEFAULT_SCROLL_SPEED))
            .andExpect(jsonPath("$.linkData").value(DEFAULT_LINK_DATA))
            .andExpect(jsonPath("$.on").value(DEFAULT_ON))
            .andExpect(jsonPath("$.off").value(DEFAULT_OFF))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.contentId").value(DEFAULT_CONTENT_ID.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingTextArea() throws Exception {
        // Get the textArea
        restTextAreaMockMvc.perform(get("/api/text-areas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTextArea() throws Exception {
        // Initialize the database
        textAreaRepository.saveAndFlush(textArea);

        int databaseSizeBeforeUpdate = textAreaRepository.findAll().size();

        // Update the textArea
        TextArea updatedTextArea = textAreaRepository.findById(textArea.getId()).get();
        // Disconnect from session so that the updates on updatedTextArea are not directly saved in db
        em.detach(updatedTextArea);
        updatedTextArea
            .name(UPDATED_NAME)
            .posX(UPDATED_POS_X)
            .posY(UPDATED_POS_Y)
            .width(UPDATED_WIDTH)
            .height(UPDATED_HEIGHT)
            .index(UPDATED_INDEX)
            .isFix(UPDATED_IS_FIX)
            .text(UPDATED_TEXT)
            .fontName(UPDATED_FONT_NAME)
            .fontSize(UPDATED_FONT_SIZE)
            .isBold(UPDATED_IS_BOLD)
            .isItalic(UPDATED_IS_ITALIC)
            .horizontalAlignment(UPDATED_HORIZONTAL_ALIGNMENT)
            .verticalAlignment(UPDATED_VERTICAL_ALIGNMENT)
            .fontColor(UPDATED_FONT_COLOR)
            .backgroundColor(UPDATED_BACKGROUND_COLOR)
            .scrollStatus(UPDATED_SCROLL_STATUS)
            .scrollDirection(UPDATED_SCROLL_DIRECTION)
            .scrollSpeed(UPDATED_SCROLL_SPEED)
            .linkData(UPDATED_LINK_DATA)
            .on(UPDATED_ON)
            .off(UPDATED_OFF)
            .type(UPDATED_TYPE)
            .contentId(UPDATED_CONTENT_ID);
        TextAreaDTO textAreaDTO = textAreaMapper.toDto(updatedTextArea);

        restTextAreaMockMvc.perform(put("/api/text-areas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(textAreaDTO)))
            .andExpect(status().isOk());

        // Validate the TextArea in the database
        List<TextArea> textAreaList = textAreaRepository.findAll();
        assertThat(textAreaList).hasSize(databaseSizeBeforeUpdate);
        TextArea testTextArea = textAreaList.get(textAreaList.size() - 1);
        assertThat(testTextArea.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTextArea.getPosX()).isEqualTo(UPDATED_POS_X);
        assertThat(testTextArea.getPosY()).isEqualTo(UPDATED_POS_Y);
        assertThat(testTextArea.getWidth()).isEqualTo(UPDATED_WIDTH);
        assertThat(testTextArea.getHeight()).isEqualTo(UPDATED_HEIGHT);
        assertThat(testTextArea.getIndex()).isEqualTo(UPDATED_INDEX);
        assertThat(testTextArea.isIsFix()).isEqualTo(UPDATED_IS_FIX);
        assertThat(testTextArea.getText()).isEqualTo(UPDATED_TEXT);
        assertThat(testTextArea.getFontName()).isEqualTo(UPDATED_FONT_NAME);
        assertThat(testTextArea.getFontSize()).isEqualTo(UPDATED_FONT_SIZE);
        assertThat(testTextArea.isIsBold()).isEqualTo(UPDATED_IS_BOLD);
        assertThat(testTextArea.isIsItalic()).isEqualTo(UPDATED_IS_ITALIC);
        assertThat(testTextArea.getHorizontalAlignment()).isEqualTo(UPDATED_HORIZONTAL_ALIGNMENT);
        assertThat(testTextArea.getVerticalAlignment()).isEqualTo(UPDATED_VERTICAL_ALIGNMENT);
        assertThat(testTextArea.getFontColor()).isEqualTo(UPDATED_FONT_COLOR);
        assertThat(testTextArea.getBackgroundColor()).isEqualTo(UPDATED_BACKGROUND_COLOR);
        assertThat(testTextArea.getScrollStatus()).isEqualTo(UPDATED_SCROLL_STATUS);
        assertThat(testTextArea.getScrollDirection()).isEqualTo(UPDATED_SCROLL_DIRECTION);
        assertThat(testTextArea.getScrollSpeed()).isEqualTo(UPDATED_SCROLL_SPEED);
        assertThat(testTextArea.getLinkData()).isEqualTo(UPDATED_LINK_DATA);
        assertThat(testTextArea.getOn()).isEqualTo(UPDATED_ON);
        assertThat(testTextArea.getOff()).isEqualTo(UPDATED_OFF);
        assertThat(testTextArea.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testTextArea.getContentId()).isEqualTo(UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingTextArea() throws Exception {
        int databaseSizeBeforeUpdate = textAreaRepository.findAll().size();

        // Create the TextArea
        TextAreaDTO textAreaDTO = textAreaMapper.toDto(textArea);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTextAreaMockMvc.perform(put("/api/text-areas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(textAreaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TextArea in the database
        List<TextArea> textAreaList = textAreaRepository.findAll();
        assertThat(textAreaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTextArea() throws Exception {
        // Initialize the database
        textAreaRepository.saveAndFlush(textArea);

        int databaseSizeBeforeDelete = textAreaRepository.findAll().size();

        // Delete the textArea
        restTextAreaMockMvc.perform(delete("/api/text-areas/{id}", textArea.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TextArea> textAreaList = textAreaRepository.findAll();
        assertThat(textAreaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
