package id.tech.cakra.smartetf.web.rest;

import id.tech.cakra.smartetf.SmartEtfApp;
import id.tech.cakra.smartetf.domain.IdxHoliday;
import id.tech.cakra.smartetf.repository.IdxHolidayRepository;
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
 * Integration tests for the {@link IdxHolidayResource} REST controller.
 */
@SpringBootTest(classes = SmartEtfApp.class)
public class IdxHolidayResourceIT {

    private static final LocalDate DEFAULT_TANGGAL = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TANGGAL = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_TANGGAL = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_KETERANGAN = "AAAAAAAAAA";
    private static final String UPDATED_KETERANGAN = "BBBBBBBBBB";

    @Autowired
    private IdxHolidayRepository idxHolidayRepository;

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

    private MockMvc restIdxHolidayMockMvc;

    private IdxHoliday idxHoliday;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final IdxHolidayResource idxHolidayResource = new IdxHolidayResource(idxHolidayRepository);
        this.restIdxHolidayMockMvc = MockMvcBuilders.standaloneSetup(idxHolidayResource)
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
    public static IdxHoliday createEntity(EntityManager em) {
        IdxHoliday idxHoliday = new IdxHoliday()
            .tanggal(DEFAULT_TANGGAL)
            .keterangan(DEFAULT_KETERANGAN);
        return idxHoliday;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static IdxHoliday createUpdatedEntity(EntityManager em) {
        IdxHoliday idxHoliday = new IdxHoliday()
            .tanggal(UPDATED_TANGGAL)
            .keterangan(UPDATED_KETERANGAN);
        return idxHoliday;
    }

    @BeforeEach
    public void initTest() {
        idxHoliday = createEntity(em);
    }

    @Test
    @Transactional
    public void createIdxHoliday() throws Exception {
        int databaseSizeBeforeCreate = idxHolidayRepository.findAll().size();

        // Create the IdxHoliday
        restIdxHolidayMockMvc.perform(post("/api/idx-holidays")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(idxHoliday)))
            .andExpect(status().isCreated());

        // Validate the IdxHoliday in the database
        List<IdxHoliday> idxHolidayList = idxHolidayRepository.findAll();
        assertThat(idxHolidayList).hasSize(databaseSizeBeforeCreate + 1);
        IdxHoliday testIdxHoliday = idxHolidayList.get(idxHolidayList.size() - 1);
        assertThat(testIdxHoliday.getTanggal()).isEqualTo(DEFAULT_TANGGAL);
        assertThat(testIdxHoliday.getKeterangan()).isEqualTo(DEFAULT_KETERANGAN);
    }

    @Test
    @Transactional
    public void createIdxHolidayWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = idxHolidayRepository.findAll().size();

        // Create the IdxHoliday with an existing ID
        idxHoliday.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restIdxHolidayMockMvc.perform(post("/api/idx-holidays")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(idxHoliday)))
            .andExpect(status().isBadRequest());

        // Validate the IdxHoliday in the database
        List<IdxHoliday> idxHolidayList = idxHolidayRepository.findAll();
        assertThat(idxHolidayList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTanggalIsRequired() throws Exception {
        int databaseSizeBeforeTest = idxHolidayRepository.findAll().size();
        // set the field null
        idxHoliday.setTanggal(null);

        // Create the IdxHoliday, which fails.

        restIdxHolidayMockMvc.perform(post("/api/idx-holidays")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(idxHoliday)))
            .andExpect(status().isBadRequest());

        List<IdxHoliday> idxHolidayList = idxHolidayRepository.findAll();
        assertThat(idxHolidayList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkKeteranganIsRequired() throws Exception {
        int databaseSizeBeforeTest = idxHolidayRepository.findAll().size();
        // set the field null
        idxHoliday.setKeterangan(null);

        // Create the IdxHoliday, which fails.

        restIdxHolidayMockMvc.perform(post("/api/idx-holidays")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(idxHoliday)))
            .andExpect(status().isBadRequest());

        List<IdxHoliday> idxHolidayList = idxHolidayRepository.findAll();
        assertThat(idxHolidayList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllIdxHolidays() throws Exception {
        // Initialize the database
        idxHolidayRepository.saveAndFlush(idxHoliday);

        // Get all the idxHolidayList
        restIdxHolidayMockMvc.perform(get("/api/idx-holidays?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(idxHoliday.getId().intValue())))
            .andExpect(jsonPath("$.[*].tanggal").value(hasItem(DEFAULT_TANGGAL.toString())))
            .andExpect(jsonPath("$.[*].keterangan").value(hasItem(DEFAULT_KETERANGAN.toString())));
    }
    
    @Test
    @Transactional
    public void getIdxHoliday() throws Exception {
        // Initialize the database
        idxHolidayRepository.saveAndFlush(idxHoliday);

        // Get the idxHoliday
        restIdxHolidayMockMvc.perform(get("/api/idx-holidays/{id}", idxHoliday.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(idxHoliday.getId().intValue()))
            .andExpect(jsonPath("$.tanggal").value(DEFAULT_TANGGAL.toString()))
            .andExpect(jsonPath("$.keterangan").value(DEFAULT_KETERANGAN.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingIdxHoliday() throws Exception {
        // Get the idxHoliday
        restIdxHolidayMockMvc.perform(get("/api/idx-holidays/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateIdxHoliday() throws Exception {
        // Initialize the database
        idxHolidayRepository.saveAndFlush(idxHoliday);

        int databaseSizeBeforeUpdate = idxHolidayRepository.findAll().size();

        // Update the idxHoliday
        IdxHoliday updatedIdxHoliday = idxHolidayRepository.findById(idxHoliday.getId()).get();
        // Disconnect from session so that the updates on updatedIdxHoliday are not directly saved in db
        em.detach(updatedIdxHoliday);
        updatedIdxHoliday
            .tanggal(UPDATED_TANGGAL)
            .keterangan(UPDATED_KETERANGAN);

        restIdxHolidayMockMvc.perform(put("/api/idx-holidays")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedIdxHoliday)))
            .andExpect(status().isOk());

        // Validate the IdxHoliday in the database
        List<IdxHoliday> idxHolidayList = idxHolidayRepository.findAll();
        assertThat(idxHolidayList).hasSize(databaseSizeBeforeUpdate);
        IdxHoliday testIdxHoliday = idxHolidayList.get(idxHolidayList.size() - 1);
        assertThat(testIdxHoliday.getTanggal()).isEqualTo(UPDATED_TANGGAL);
        assertThat(testIdxHoliday.getKeterangan()).isEqualTo(UPDATED_KETERANGAN);
    }

    @Test
    @Transactional
    public void updateNonExistingIdxHoliday() throws Exception {
        int databaseSizeBeforeUpdate = idxHolidayRepository.findAll().size();

        // Create the IdxHoliday

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restIdxHolidayMockMvc.perform(put("/api/idx-holidays")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(idxHoliday)))
            .andExpect(status().isBadRequest());

        // Validate the IdxHoliday in the database
        List<IdxHoliday> idxHolidayList = idxHolidayRepository.findAll();
        assertThat(idxHolidayList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteIdxHoliday() throws Exception {
        // Initialize the database
        idxHolidayRepository.saveAndFlush(idxHoliday);

        int databaseSizeBeforeDelete = idxHolidayRepository.findAll().size();

        // Delete the idxHoliday
        restIdxHolidayMockMvc.perform(delete("/api/idx-holidays/{id}", idxHoliday.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<IdxHoliday> idxHolidayList = idxHolidayRepository.findAll();
        assertThat(idxHolidayList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(IdxHoliday.class);
        IdxHoliday idxHoliday1 = new IdxHoliday();
        idxHoliday1.setId(1L);
        IdxHoliday idxHoliday2 = new IdxHoliday();
        idxHoliday2.setId(idxHoliday1.getId());
        assertThat(idxHoliday1).isEqualTo(idxHoliday2);
        idxHoliday2.setId(2L);
        assertThat(idxHoliday1).isNotEqualTo(idxHoliday2);
        idxHoliday1.setId(null);
        assertThat(idxHoliday1).isNotEqualTo(idxHoliday2);
    }
}
