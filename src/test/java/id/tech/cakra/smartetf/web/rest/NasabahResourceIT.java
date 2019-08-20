package id.tech.cakra.smartetf.web.rest;

import id.tech.cakra.smartetf.SmartEtfApp;
import id.tech.cakra.smartetf.domain.Nasabah;
import id.tech.cakra.smartetf.repository.NasabahRepository;
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
 * Integration tests for the {@link NasabahResource} REST controller.
 */
@SpringBootTest(classes = SmartEtfApp.class)
public class NasabahResourceIT {

    private static final String DEFAULT_KODE_NASABAH = "AAAAAAAAAA";
    private static final String UPDATED_KODE_NASABAH = "BBBBBBBBBB";

    private static final String DEFAULT_NAMA_NASABAH = "AAAAAAAAAA";
    private static final String UPDATED_NAMA_NASABAH = "BBBBBBBBBB";

    private static final String DEFAULT_TIPE_NASABAH = "A";
    private static final String UPDATED_TIPE_NASABAH = "B";

    private static final String DEFAULT_SID = "AAAAAAAAAA";
    private static final String UPDATED_SID = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_SUB_REK = "AAAAAAAAAA";
    private static final String UPDATED_BANK_SUB_REK = "BBBBBBBBBB";

    private static final String DEFAULT_ALAMAT_1 = "AAAAAAAAAA";
    private static final String UPDATED_ALAMAT_1 = "BBBBBBBBBB";

    private static final String DEFAULT_ALAMAT_2 = "AAAAAAAAAA";
    private static final String UPDATED_ALAMAT_2 = "BBBBBBBBBB";

    private static final String DEFAULT_ALAMAT_3 = "AAAAAAAAAA";
    private static final String UPDATED_ALAMAT_3 = "BBBBBBBBBB";

    private static final String DEFAULT_NO_TELP = "AAAAAAAAAA";
    private static final String UPDATED_NO_TELP = "BBBBBBBBBB";

    private static final String DEFAULT_NO_FAX = "AAAAAAAAAA";
    private static final String UPDATED_NO_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS_SUB_REK = "A";
    private static final String UPDATED_STATUS_SUB_REK = "B";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    @Autowired
    private NasabahRepository nasabahRepository;

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

    private MockMvc restNasabahMockMvc;

    private Nasabah nasabah;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NasabahResource nasabahResource = new NasabahResource(nasabahRepository);
        this.restNasabahMockMvc = MockMvcBuilders.standaloneSetup(nasabahResource)
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
    public static Nasabah createEntity(EntityManager em) {
        Nasabah nasabah = new Nasabah()
            .kodeNasabah(DEFAULT_KODE_NASABAH)
            .namaNasabah(DEFAULT_NAMA_NASABAH)
            .tipeNasabah(DEFAULT_TIPE_NASABAH)
            .sid(DEFAULT_SID)
            .bankSubRek(DEFAULT_BANK_SUB_REK)
            .alamat1(DEFAULT_ALAMAT_1)
            .alamat2(DEFAULT_ALAMAT_2)
            .alamat3(DEFAULT_ALAMAT_3)
            .noTelp(DEFAULT_NO_TELP)
            .noFax(DEFAULT_NO_FAX)
            .statusSubRek(DEFAULT_STATUS_SUB_REK)
            .status(DEFAULT_STATUS);
        return nasabah;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Nasabah createUpdatedEntity(EntityManager em) {
        Nasabah nasabah = new Nasabah()
            .kodeNasabah(UPDATED_KODE_NASABAH)
            .namaNasabah(UPDATED_NAMA_NASABAH)
            .tipeNasabah(UPDATED_TIPE_NASABAH)
            .sid(UPDATED_SID)
            .bankSubRek(UPDATED_BANK_SUB_REK)
            .alamat1(UPDATED_ALAMAT_1)
            .alamat2(UPDATED_ALAMAT_2)
            .alamat3(UPDATED_ALAMAT_3)
            .noTelp(UPDATED_NO_TELP)
            .noFax(UPDATED_NO_FAX)
            .statusSubRek(UPDATED_STATUS_SUB_REK)
            .status(UPDATED_STATUS);
        return nasabah;
    }

    @BeforeEach
    public void initTest() {
        nasabah = createEntity(em);
    }

    @Test
    @Transactional
    public void createNasabah() throws Exception {
        int databaseSizeBeforeCreate = nasabahRepository.findAll().size();

        // Create the Nasabah
        restNasabahMockMvc.perform(post("/api/nasabahs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nasabah)))
            .andExpect(status().isCreated());

        // Validate the Nasabah in the database
        List<Nasabah> nasabahList = nasabahRepository.findAll();
        assertThat(nasabahList).hasSize(databaseSizeBeforeCreate + 1);
        Nasabah testNasabah = nasabahList.get(nasabahList.size() - 1);
        assertThat(testNasabah.getKodeNasabah()).isEqualTo(DEFAULT_KODE_NASABAH);
        assertThat(testNasabah.getNamaNasabah()).isEqualTo(DEFAULT_NAMA_NASABAH);
        assertThat(testNasabah.getTipeNasabah()).isEqualTo(DEFAULT_TIPE_NASABAH);
        assertThat(testNasabah.getSid()).isEqualTo(DEFAULT_SID);
        assertThat(testNasabah.getBankSubRek()).isEqualTo(DEFAULT_BANK_SUB_REK);
        assertThat(testNasabah.getAlamat1()).isEqualTo(DEFAULT_ALAMAT_1);
        assertThat(testNasabah.getAlamat2()).isEqualTo(DEFAULT_ALAMAT_2);
        assertThat(testNasabah.getAlamat3()).isEqualTo(DEFAULT_ALAMAT_3);
        assertThat(testNasabah.getNoTelp()).isEqualTo(DEFAULT_NO_TELP);
        assertThat(testNasabah.getNoFax()).isEqualTo(DEFAULT_NO_FAX);
        assertThat(testNasabah.getStatusSubRek()).isEqualTo(DEFAULT_STATUS_SUB_REK);
        assertThat(testNasabah.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createNasabahWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = nasabahRepository.findAll().size();

        // Create the Nasabah with an existing ID
        nasabah.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNasabahMockMvc.perform(post("/api/nasabahs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nasabah)))
            .andExpect(status().isBadRequest());

        // Validate the Nasabah in the database
        List<Nasabah> nasabahList = nasabahRepository.findAll();
        assertThat(nasabahList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkKodeNasabahIsRequired() throws Exception {
        int databaseSizeBeforeTest = nasabahRepository.findAll().size();
        // set the field null
        nasabah.setKodeNasabah(null);

        // Create the Nasabah, which fails.

        restNasabahMockMvc.perform(post("/api/nasabahs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nasabah)))
            .andExpect(status().isBadRequest());

        List<Nasabah> nasabahList = nasabahRepository.findAll();
        assertThat(nasabahList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNamaNasabahIsRequired() throws Exception {
        int databaseSizeBeforeTest = nasabahRepository.findAll().size();
        // set the field null
        nasabah.setNamaNasabah(null);

        // Create the Nasabah, which fails.

        restNasabahMockMvc.perform(post("/api/nasabahs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nasabah)))
            .andExpect(status().isBadRequest());

        List<Nasabah> nasabahList = nasabahRepository.findAll();
        assertThat(nasabahList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTipeNasabahIsRequired() throws Exception {
        int databaseSizeBeforeTest = nasabahRepository.findAll().size();
        // set the field null
        nasabah.setTipeNasabah(null);

        // Create the Nasabah, which fails.

        restNasabahMockMvc.perform(post("/api/nasabahs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nasabah)))
            .andExpect(status().isBadRequest());

        List<Nasabah> nasabahList = nasabahRepository.findAll();
        assertThat(nasabahList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllNasabahs() throws Exception {
        // Initialize the database
        nasabahRepository.saveAndFlush(nasabah);

        // Get all the nasabahList
        restNasabahMockMvc.perform(get("/api/nasabahs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nasabah.getId().intValue())))
            .andExpect(jsonPath("$.[*].kodeNasabah").value(hasItem(DEFAULT_KODE_NASABAH.toString())))
            .andExpect(jsonPath("$.[*].namaNasabah").value(hasItem(DEFAULT_NAMA_NASABAH.toString())))
            .andExpect(jsonPath("$.[*].tipeNasabah").value(hasItem(DEFAULT_TIPE_NASABAH.toString())))
            .andExpect(jsonPath("$.[*].sid").value(hasItem(DEFAULT_SID.toString())))
            .andExpect(jsonPath("$.[*].bankSubRek").value(hasItem(DEFAULT_BANK_SUB_REK.toString())))
            .andExpect(jsonPath("$.[*].alamat1").value(hasItem(DEFAULT_ALAMAT_1.toString())))
            .andExpect(jsonPath("$.[*].alamat2").value(hasItem(DEFAULT_ALAMAT_2.toString())))
            .andExpect(jsonPath("$.[*].alamat3").value(hasItem(DEFAULT_ALAMAT_3.toString())))
            .andExpect(jsonPath("$.[*].noTelp").value(hasItem(DEFAULT_NO_TELP.toString())))
            .andExpect(jsonPath("$.[*].noFax").value(hasItem(DEFAULT_NO_FAX.toString())))
            .andExpect(jsonPath("$.[*].statusSubRek").value(hasItem(DEFAULT_STATUS_SUB_REK.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getNasabah() throws Exception {
        // Initialize the database
        nasabahRepository.saveAndFlush(nasabah);

        // Get the nasabah
        restNasabahMockMvc.perform(get("/api/nasabahs/{id}", nasabah.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(nasabah.getId().intValue()))
            .andExpect(jsonPath("$.kodeNasabah").value(DEFAULT_KODE_NASABAH.toString()))
            .andExpect(jsonPath("$.namaNasabah").value(DEFAULT_NAMA_NASABAH.toString()))
            .andExpect(jsonPath("$.tipeNasabah").value(DEFAULT_TIPE_NASABAH.toString()))
            .andExpect(jsonPath("$.sid").value(DEFAULT_SID.toString()))
            .andExpect(jsonPath("$.bankSubRek").value(DEFAULT_BANK_SUB_REK.toString()))
            .andExpect(jsonPath("$.alamat1").value(DEFAULT_ALAMAT_1.toString()))
            .andExpect(jsonPath("$.alamat2").value(DEFAULT_ALAMAT_2.toString()))
            .andExpect(jsonPath("$.alamat3").value(DEFAULT_ALAMAT_3.toString()))
            .andExpect(jsonPath("$.noTelp").value(DEFAULT_NO_TELP.toString()))
            .andExpect(jsonPath("$.noFax").value(DEFAULT_NO_FAX.toString()))
            .andExpect(jsonPath("$.statusSubRek").value(DEFAULT_STATUS_SUB_REK.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingNasabah() throws Exception {
        // Get the nasabah
        restNasabahMockMvc.perform(get("/api/nasabahs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNasabah() throws Exception {
        // Initialize the database
        nasabahRepository.saveAndFlush(nasabah);

        int databaseSizeBeforeUpdate = nasabahRepository.findAll().size();

        // Update the nasabah
        Nasabah updatedNasabah = nasabahRepository.findById(nasabah.getId()).get();
        // Disconnect from session so that the updates on updatedNasabah are not directly saved in db
        em.detach(updatedNasabah);
        updatedNasabah
            .kodeNasabah(UPDATED_KODE_NASABAH)
            .namaNasabah(UPDATED_NAMA_NASABAH)
            .tipeNasabah(UPDATED_TIPE_NASABAH)
            .sid(UPDATED_SID)
            .bankSubRek(UPDATED_BANK_SUB_REK)
            .alamat1(UPDATED_ALAMAT_1)
            .alamat2(UPDATED_ALAMAT_2)
            .alamat3(UPDATED_ALAMAT_3)
            .noTelp(UPDATED_NO_TELP)
            .noFax(UPDATED_NO_FAX)
            .statusSubRek(UPDATED_STATUS_SUB_REK)
            .status(UPDATED_STATUS);

        restNasabahMockMvc.perform(put("/api/nasabahs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedNasabah)))
            .andExpect(status().isOk());

        // Validate the Nasabah in the database
        List<Nasabah> nasabahList = nasabahRepository.findAll();
        assertThat(nasabahList).hasSize(databaseSizeBeforeUpdate);
        Nasabah testNasabah = nasabahList.get(nasabahList.size() - 1);
        assertThat(testNasabah.getKodeNasabah()).isEqualTo(UPDATED_KODE_NASABAH);
        assertThat(testNasabah.getNamaNasabah()).isEqualTo(UPDATED_NAMA_NASABAH);
        assertThat(testNasabah.getTipeNasabah()).isEqualTo(UPDATED_TIPE_NASABAH);
        assertThat(testNasabah.getSid()).isEqualTo(UPDATED_SID);
        assertThat(testNasabah.getBankSubRek()).isEqualTo(UPDATED_BANK_SUB_REK);
        assertThat(testNasabah.getAlamat1()).isEqualTo(UPDATED_ALAMAT_1);
        assertThat(testNasabah.getAlamat2()).isEqualTo(UPDATED_ALAMAT_2);
        assertThat(testNasabah.getAlamat3()).isEqualTo(UPDATED_ALAMAT_3);
        assertThat(testNasabah.getNoTelp()).isEqualTo(UPDATED_NO_TELP);
        assertThat(testNasabah.getNoFax()).isEqualTo(UPDATED_NO_FAX);
        assertThat(testNasabah.getStatusSubRek()).isEqualTo(UPDATED_STATUS_SUB_REK);
        assertThat(testNasabah.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingNasabah() throws Exception {
        int databaseSizeBeforeUpdate = nasabahRepository.findAll().size();

        // Create the Nasabah

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNasabahMockMvc.perform(put("/api/nasabahs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nasabah)))
            .andExpect(status().isBadRequest());

        // Validate the Nasabah in the database
        List<Nasabah> nasabahList = nasabahRepository.findAll();
        assertThat(nasabahList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNasabah() throws Exception {
        // Initialize the database
        nasabahRepository.saveAndFlush(nasabah);

        int databaseSizeBeforeDelete = nasabahRepository.findAll().size();

        // Delete the nasabah
        restNasabahMockMvc.perform(delete("/api/nasabahs/{id}", nasabah.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Nasabah> nasabahList = nasabahRepository.findAll();
        assertThat(nasabahList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Nasabah.class);
        Nasabah nasabah1 = new Nasabah();
        nasabah1.setId(1L);
        Nasabah nasabah2 = new Nasabah();
        nasabah2.setId(nasabah1.getId());
        assertThat(nasabah1).isEqualTo(nasabah2);
        nasabah2.setId(2L);
        assertThat(nasabah1).isNotEqualTo(nasabah2);
        nasabah1.setId(null);
        assertThat(nasabah1).isNotEqualTo(nasabah2);
    }
}
