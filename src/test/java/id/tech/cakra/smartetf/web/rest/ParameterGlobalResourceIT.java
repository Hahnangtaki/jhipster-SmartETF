package id.tech.cakra.smartetf.web.rest;

import id.tech.cakra.smartetf.SmartEtfApp;
import id.tech.cakra.smartetf.domain.ParameterGlobal;
import id.tech.cakra.smartetf.repository.ParameterGlobalRepository;
import id.tech.cakra.smartetf.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static id.tech.cakra.smartetf.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ParameterGlobalResource} REST controller.
 */
@SpringBootTest(classes = SmartEtfApp.class)
public class ParameterGlobalResourceIT {

    private static final String DEFAULT_PRM_ID = "AAAAAAAAAAAAAAAAAAAA";
    private static final String UPDATED_PRM_ID = "BBBBBBBBBBBBBBBBBBBB";

    private static final String DEFAULT_PRM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PRM_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PRM_TY = "A";
    private static final String UPDATED_PRM_TY = "B";

    private static final String DEFAULT_APP_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_APP_TYPE = "BBBBBBBBBB";

    private static final Integer DEFAULT_INT_VAL = 1;
    private static final Integer UPDATED_INT_VAL = 2;
    private static final Integer SMALLER_INT_VAL = 1 - 1;

    private static final Float DEFAULT_FLOAT_VAL = 1F;
    private static final Float UPDATED_FLOAT_VAL = 2F;
    private static final Float SMALLER_FLOAT_VAL = 1F - 1F;

    private static final String DEFAULT_STR_VAL = "AAAAAAAAAA";
    private static final String UPDATED_STR_VAL = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_VAL = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_VAL = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_DATE_VAL = LocalDate.ofEpochDay(-1L);

    private static final Boolean DEFAULT_SHOW = false;
    private static final Boolean UPDATED_SHOW = true;

    private static final Boolean DEFAULT_EDIT = false;
    private static final Boolean UPDATED_EDIT = true;

    @Autowired
    private ParameterGlobalRepository parameterGlobalRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restParameterGlobalMockMvc;

    private ParameterGlobal parameterGlobal;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ParameterGlobalResource parameterGlobalResource = new ParameterGlobalResource(parameterGlobalRepository);
        this.restParameterGlobalMockMvc = MockMvcBuilders.standaloneSetup(parameterGlobalResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ParameterGlobal createEntity(EntityManager em) {
        ParameterGlobal parameterGlobal = new ParameterGlobal()
            .prmId(DEFAULT_PRM_ID)
            .prmName(DEFAULT_PRM_NAME)
            .prmTy(DEFAULT_PRM_TY)
            .appType(DEFAULT_APP_TYPE)
            .intVal(DEFAULT_INT_VAL)
            .floatVal(DEFAULT_FLOAT_VAL)
            .strVal(DEFAULT_STR_VAL)
            .dateVal(DEFAULT_DATE_VAL)
            .show(DEFAULT_SHOW)
            .edit(DEFAULT_EDIT);
        return parameterGlobal;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ParameterGlobal createUpdatedEntity(EntityManager em) {
        ParameterGlobal parameterGlobal = new ParameterGlobal()
            .prmId(UPDATED_PRM_ID)
            .prmName(UPDATED_PRM_NAME)
            .prmTy(UPDATED_PRM_TY)
            .appType(UPDATED_APP_TYPE)
            .intVal(UPDATED_INT_VAL)
            .floatVal(UPDATED_FLOAT_VAL)
            .strVal(UPDATED_STR_VAL)
            .dateVal(UPDATED_DATE_VAL)
            .show(UPDATED_SHOW)
            .edit(UPDATED_EDIT);
        return parameterGlobal;
    }

    @BeforeEach
    public void initTest() {
        parameterGlobal = createEntity(em);
    }

    @Test
    @Transactional
    public void createParameterGlobal() throws Exception {
        int databaseSizeBeforeCreate = parameterGlobalRepository.findAll().size();

        // Create the ParameterGlobal
        restParameterGlobalMockMvc.perform(post("/api/parameter-globals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(parameterGlobal)))
            .andExpect(status().isCreated());

        // Validate the ParameterGlobal in the database
        List<ParameterGlobal> parameterGlobalList = parameterGlobalRepository.findAll();
        assertThat(parameterGlobalList).hasSize(databaseSizeBeforeCreate + 1);
        ParameterGlobal testParameterGlobal = parameterGlobalList.get(parameterGlobalList.size() - 1);
        assertThat(testParameterGlobal.getPrmId()).isEqualTo(DEFAULT_PRM_ID);
        assertThat(testParameterGlobal.getPrmName()).isEqualTo(DEFAULT_PRM_NAME);
        assertThat(testParameterGlobal.getPrmTy()).isEqualTo(DEFAULT_PRM_TY);
        assertThat(testParameterGlobal.getAppType()).isEqualTo(DEFAULT_APP_TYPE);
        assertThat(testParameterGlobal.getIntVal()).isEqualTo(DEFAULT_INT_VAL);
        assertThat(testParameterGlobal.getFloatVal()).isEqualTo(DEFAULT_FLOAT_VAL);
        assertThat(testParameterGlobal.getStrVal()).isEqualTo(DEFAULT_STR_VAL);
        assertThat(testParameterGlobal.getDateVal()).isEqualTo(DEFAULT_DATE_VAL);
        assertThat(testParameterGlobal.isShow()).isEqualTo(DEFAULT_SHOW);
        assertThat(testParameterGlobal.isEdit()).isEqualTo(DEFAULT_EDIT);
    }

    @Test
    @Transactional
    public void createParameterGlobalWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = parameterGlobalRepository.findAll().size();

        // Create the ParameterGlobal with an existing ID
        parameterGlobal.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restParameterGlobalMockMvc.perform(post("/api/parameter-globals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(parameterGlobal)))
            .andExpect(status().isBadRequest());

        // Validate the ParameterGlobal in the database
        List<ParameterGlobal> parameterGlobalList = parameterGlobalRepository.findAll();
        assertThat(parameterGlobalList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkPrmIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = parameterGlobalRepository.findAll().size();
        // set the field null
        parameterGlobal.setPrmId(null);

        // Create the ParameterGlobal, which fails.

        restParameterGlobalMockMvc.perform(post("/api/parameter-globals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(parameterGlobal)))
            .andExpect(status().isBadRequest());

        List<ParameterGlobal> parameterGlobalList = parameterGlobalRepository.findAll();
        assertThat(parameterGlobalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPrmNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = parameterGlobalRepository.findAll().size();
        // set the field null
        parameterGlobal.setPrmName(null);

        // Create the ParameterGlobal, which fails.

        restParameterGlobalMockMvc.perform(post("/api/parameter-globals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(parameterGlobal)))
            .andExpect(status().isBadRequest());

        List<ParameterGlobal> parameterGlobalList = parameterGlobalRepository.findAll();
        assertThat(parameterGlobalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPrmTyIsRequired() throws Exception {
        int databaseSizeBeforeTest = parameterGlobalRepository.findAll().size();
        // set the field null
        parameterGlobal.setPrmTy(null);

        // Create the ParameterGlobal, which fails.

        restParameterGlobalMockMvc.perform(post("/api/parameter-globals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(parameterGlobal)))
            .andExpect(status().isBadRequest());

        List<ParameterGlobal> parameterGlobalList = parameterGlobalRepository.findAll();
        assertThat(parameterGlobalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllParameterGlobals() throws Exception {
        // Initialize the database
        parameterGlobalRepository.saveAndFlush(parameterGlobal);

        // Get all the parameterGlobalList
        restParameterGlobalMockMvc.perform(get("/api/parameter-globals?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(parameterGlobal.getId().intValue())))
            .andExpect(jsonPath("$.[*].prmId").value(hasItem(DEFAULT_PRM_ID.toString())))
            .andExpect(jsonPath("$.[*].prmName").value(hasItem(DEFAULT_PRM_NAME.toString())))
            .andExpect(jsonPath("$.[*].prmTy").value(hasItem(DEFAULT_PRM_TY.toString())))
            .andExpect(jsonPath("$.[*].appType").value(hasItem(DEFAULT_APP_TYPE.toString())))
            .andExpect(jsonPath("$.[*].intVal").value(hasItem(DEFAULT_INT_VAL)))
            .andExpect(jsonPath("$.[*].floatVal").value(hasItem(DEFAULT_FLOAT_VAL.doubleValue())))
            .andExpect(jsonPath("$.[*].strVal").value(hasItem(DEFAULT_STR_VAL.toString())))
            .andExpect(jsonPath("$.[*].dateVal").value(hasItem(DEFAULT_DATE_VAL.toString())))
            .andExpect(jsonPath("$.[*].show").value(hasItem(DEFAULT_SHOW.booleanValue())))
            .andExpect(jsonPath("$.[*].edit").value(hasItem(DEFAULT_EDIT.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getParameterGlobal() throws Exception {
        // Initialize the database
        parameterGlobalRepository.saveAndFlush(parameterGlobal);

        // Get the parameterGlobal
        restParameterGlobalMockMvc.perform(get("/api/parameter-globals/{id}", parameterGlobal.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(parameterGlobal.getId().intValue()))
            .andExpect(jsonPath("$.prmId").value(DEFAULT_PRM_ID.toString()))
            .andExpect(jsonPath("$.prmName").value(DEFAULT_PRM_NAME.toString()))
            .andExpect(jsonPath("$.prmTy").value(DEFAULT_PRM_TY.toString()))
            .andExpect(jsonPath("$.appType").value(DEFAULT_APP_TYPE.toString()))
            .andExpect(jsonPath("$.intVal").value(DEFAULT_INT_VAL))
            .andExpect(jsonPath("$.floatVal").value(DEFAULT_FLOAT_VAL.doubleValue()))
            .andExpect(jsonPath("$.strVal").value(DEFAULT_STR_VAL.toString()))
            .andExpect(jsonPath("$.dateVal").value(DEFAULT_DATE_VAL.toString()))
            .andExpect(jsonPath("$.show").value(DEFAULT_SHOW.booleanValue()))
            .andExpect(jsonPath("$.edit").value(DEFAULT_EDIT.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingParameterGlobal() throws Exception {
        // Get the parameterGlobal
        restParameterGlobalMockMvc.perform(get("/api/parameter-globals/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateParameterGlobal() throws Exception {
        // Initialize the database
        parameterGlobalRepository.saveAndFlush(parameterGlobal);

        int databaseSizeBeforeUpdate = parameterGlobalRepository.findAll().size();

        // Update the parameterGlobal
        ParameterGlobal updatedParameterGlobal = parameterGlobalRepository.findById(parameterGlobal.getId()).get();
        // Disconnect from session so that the updates on updatedParameterGlobal are not directly saved in db
        em.detach(updatedParameterGlobal);
        updatedParameterGlobal
            .prmId(UPDATED_PRM_ID)
            .prmName(UPDATED_PRM_NAME)
            .prmTy(UPDATED_PRM_TY)
            .appType(UPDATED_APP_TYPE)
            .intVal(UPDATED_INT_VAL)
            .floatVal(UPDATED_FLOAT_VAL)
            .strVal(UPDATED_STR_VAL)
            .dateVal(UPDATED_DATE_VAL)
            .show(UPDATED_SHOW)
            .edit(UPDATED_EDIT);

        restParameterGlobalMockMvc.perform(put("/api/parameter-globals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedParameterGlobal)))
            .andExpect(status().isOk());

        // Validate the ParameterGlobal in the database
        List<ParameterGlobal> parameterGlobalList = parameterGlobalRepository.findAll();
        assertThat(parameterGlobalList).hasSize(databaseSizeBeforeUpdate);
        ParameterGlobal testParameterGlobal = parameterGlobalList.get(parameterGlobalList.size() - 1);
        assertThat(testParameterGlobal.getPrmId()).isEqualTo(UPDATED_PRM_ID);
        assertThat(testParameterGlobal.getPrmName()).isEqualTo(UPDATED_PRM_NAME);
        assertThat(testParameterGlobal.getPrmTy()).isEqualTo(UPDATED_PRM_TY);
        assertThat(testParameterGlobal.getAppType()).isEqualTo(UPDATED_APP_TYPE);
        assertThat(testParameterGlobal.getIntVal()).isEqualTo(UPDATED_INT_VAL);
        assertThat(testParameterGlobal.getFloatVal()).isEqualTo(UPDATED_FLOAT_VAL);
        assertThat(testParameterGlobal.getStrVal()).isEqualTo(UPDATED_STR_VAL);
        assertThat(testParameterGlobal.getDateVal()).isEqualTo(UPDATED_DATE_VAL);
        assertThat(testParameterGlobal.isShow()).isEqualTo(UPDATED_SHOW);
        assertThat(testParameterGlobal.isEdit()).isEqualTo(UPDATED_EDIT);
    }

    @Test
    @Transactional
    public void updateNonExistingParameterGlobal() throws Exception {
        int databaseSizeBeforeUpdate = parameterGlobalRepository.findAll().size();

        // Create the ParameterGlobal

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restParameterGlobalMockMvc.perform(put("/api/parameter-globals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(parameterGlobal)))
            .andExpect(status().isBadRequest());

        // Validate the ParameterGlobal in the database
        List<ParameterGlobal> parameterGlobalList = parameterGlobalRepository.findAll();
        assertThat(parameterGlobalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteParameterGlobal() throws Exception {
        // Initialize the database
        parameterGlobalRepository.saveAndFlush(parameterGlobal);

        int databaseSizeBeforeDelete = parameterGlobalRepository.findAll().size();

        // Delete the parameterGlobal
        restParameterGlobalMockMvc.perform(delete("/api/parameter-globals/{id}", parameterGlobal.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ParameterGlobal> parameterGlobalList = parameterGlobalRepository.findAll();
        assertThat(parameterGlobalList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ParameterGlobal.class);
        ParameterGlobal parameterGlobal1 = new ParameterGlobal();
        parameterGlobal1.setId(1L);
        ParameterGlobal parameterGlobal2 = new ParameterGlobal();
        parameterGlobal2.setId(parameterGlobal1.getId());
        assertThat(parameterGlobal1).isEqualTo(parameterGlobal2);
        parameterGlobal2.setId(2L);
        assertThat(parameterGlobal1).isNotEqualTo(parameterGlobal2);
        parameterGlobal1.setId(null);
        assertThat(parameterGlobal1).isNotEqualTo(parameterGlobal2);
    }
}
