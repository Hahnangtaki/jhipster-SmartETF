package id.tech.cakra.smartetf.web.rest;

import id.tech.cakra.smartetf.SmartEtfApp;
import id.tech.cakra.smartetf.domain.TipeEfek;
import id.tech.cakra.smartetf.repository.TipeEfekRepository;
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
import java.util.List;

import static id.tech.cakra.smartetf.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link TipeEfekResource} REST controller.
 */
@SpringBootTest(classes = SmartEtfApp.class)
public class TipeEfekResourceIT {

    private static final String DEFAULT_TIPE_EFEK = "A";
    private static final String UPDATED_TIPE_EFEK = "B";

    private static final String DEFAULT_KETERANGAN = "AAAAAAAAAA";
    private static final String UPDATED_KETERANGAN = "BBBBBBBBBB";

    @Autowired
    private TipeEfekRepository tipeEfekRepository;

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

    private MockMvc restTipeEfekMockMvc;

    private TipeEfek tipeEfek;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TipeEfekResource tipeEfekResource = new TipeEfekResource(tipeEfekRepository);
        this.restTipeEfekMockMvc = MockMvcBuilders.standaloneSetup(tipeEfekResource)
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
    public static TipeEfek createEntity(EntityManager em) {
        TipeEfek tipeEfek = new TipeEfek()
            .tipeEfek(DEFAULT_TIPE_EFEK)
            .keterangan(DEFAULT_KETERANGAN);
        return tipeEfek;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TipeEfek createUpdatedEntity(EntityManager em) {
        TipeEfek tipeEfek = new TipeEfek()
            .tipeEfek(UPDATED_TIPE_EFEK)
            .keterangan(UPDATED_KETERANGAN);
        return tipeEfek;
    }

    @BeforeEach
    public void initTest() {
        tipeEfek = createEntity(em);
    }

    @Test
    @Transactional
    public void createTipeEfek() throws Exception {
        int databaseSizeBeforeCreate = tipeEfekRepository.findAll().size();

        // Create the TipeEfek
        restTipeEfekMockMvc.perform(post("/api/tipe-efeks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipeEfek)))
            .andExpect(status().isCreated());

        // Validate the TipeEfek in the database
        List<TipeEfek> tipeEfekList = tipeEfekRepository.findAll();
        assertThat(tipeEfekList).hasSize(databaseSizeBeforeCreate + 1);
        TipeEfek testTipeEfek = tipeEfekList.get(tipeEfekList.size() - 1);
        assertThat(testTipeEfek.getTipeEfek()).isEqualTo(DEFAULT_TIPE_EFEK);
        assertThat(testTipeEfek.getKeterangan()).isEqualTo(DEFAULT_KETERANGAN);
    }

    @Test
    @Transactional
    public void createTipeEfekWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tipeEfekRepository.findAll().size();

        // Create the TipeEfek with an existing ID
        tipeEfek.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTipeEfekMockMvc.perform(post("/api/tipe-efeks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipeEfek)))
            .andExpect(status().isBadRequest());

        // Validate the TipeEfek in the database
        List<TipeEfek> tipeEfekList = tipeEfekRepository.findAll();
        assertThat(tipeEfekList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTipeEfekIsRequired() throws Exception {
        int databaseSizeBeforeTest = tipeEfekRepository.findAll().size();
        // set the field null
        tipeEfek.setTipeEfek(null);

        // Create the TipeEfek, which fails.

        restTipeEfekMockMvc.perform(post("/api/tipe-efeks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipeEfek)))
            .andExpect(status().isBadRequest());

        List<TipeEfek> tipeEfekList = tipeEfekRepository.findAll();
        assertThat(tipeEfekList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTipeEfeks() throws Exception {
        // Initialize the database
        tipeEfekRepository.saveAndFlush(tipeEfek);

        // Get all the tipeEfekList
        restTipeEfekMockMvc.perform(get("/api/tipe-efeks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipeEfek.getId().intValue())))
            .andExpect(jsonPath("$.[*].tipeEfek").value(hasItem(DEFAULT_TIPE_EFEK.toString())))
            .andExpect(jsonPath("$.[*].keterangan").value(hasItem(DEFAULT_KETERANGAN.toString())));
    }
    
    @Test
    @Transactional
    public void getTipeEfek() throws Exception {
        // Initialize the database
        tipeEfekRepository.saveAndFlush(tipeEfek);

        // Get the tipeEfek
        restTipeEfekMockMvc.perform(get("/api/tipe-efeks/{id}", tipeEfek.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tipeEfek.getId().intValue()))
            .andExpect(jsonPath("$.tipeEfek").value(DEFAULT_TIPE_EFEK.toString()))
            .andExpect(jsonPath("$.keterangan").value(DEFAULT_KETERANGAN.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTipeEfek() throws Exception {
        // Get the tipeEfek
        restTipeEfekMockMvc.perform(get("/api/tipe-efeks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTipeEfek() throws Exception {
        // Initialize the database
        tipeEfekRepository.saveAndFlush(tipeEfek);

        int databaseSizeBeforeUpdate = tipeEfekRepository.findAll().size();

        // Update the tipeEfek
        TipeEfek updatedTipeEfek = tipeEfekRepository.findById(tipeEfek.getId()).get();
        // Disconnect from session so that the updates on updatedTipeEfek are not directly saved in db
        em.detach(updatedTipeEfek);
        updatedTipeEfek
            .tipeEfek(UPDATED_TIPE_EFEK)
            .keterangan(UPDATED_KETERANGAN);

        restTipeEfekMockMvc.perform(put("/api/tipe-efeks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTipeEfek)))
            .andExpect(status().isOk());

        // Validate the TipeEfek in the database
        List<TipeEfek> tipeEfekList = tipeEfekRepository.findAll();
        assertThat(tipeEfekList).hasSize(databaseSizeBeforeUpdate);
        TipeEfek testTipeEfek = tipeEfekList.get(tipeEfekList.size() - 1);
        assertThat(testTipeEfek.getTipeEfek()).isEqualTo(UPDATED_TIPE_EFEK);
        assertThat(testTipeEfek.getKeterangan()).isEqualTo(UPDATED_KETERANGAN);
    }

    @Test
    @Transactional
    public void updateNonExistingTipeEfek() throws Exception {
        int databaseSizeBeforeUpdate = tipeEfekRepository.findAll().size();

        // Create the TipeEfek

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTipeEfekMockMvc.perform(put("/api/tipe-efeks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipeEfek)))
            .andExpect(status().isBadRequest());

        // Validate the TipeEfek in the database
        List<TipeEfek> tipeEfekList = tipeEfekRepository.findAll();
        assertThat(tipeEfekList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTipeEfek() throws Exception {
        // Initialize the database
        tipeEfekRepository.saveAndFlush(tipeEfek);

        int databaseSizeBeforeDelete = tipeEfekRepository.findAll().size();

        // Delete the tipeEfek
        restTipeEfekMockMvc.perform(delete("/api/tipe-efeks/{id}", tipeEfek.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TipeEfek> tipeEfekList = tipeEfekRepository.findAll();
        assertThat(tipeEfekList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipeEfek.class);
        TipeEfek tipeEfek1 = new TipeEfek();
        tipeEfek1.setId(1L);
        TipeEfek tipeEfek2 = new TipeEfek();
        tipeEfek2.setId(tipeEfek1.getId());
        assertThat(tipeEfek1).isEqualTo(tipeEfek2);
        tipeEfek2.setId(2L);
        assertThat(tipeEfek1).isNotEqualTo(tipeEfek2);
        tipeEfek1.setId(null);
        assertThat(tipeEfek1).isNotEqualTo(tipeEfek2);
    }
}
