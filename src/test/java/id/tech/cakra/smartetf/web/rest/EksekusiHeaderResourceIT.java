package id.tech.cakra.smartetf.web.rest;

import id.tech.cakra.smartetf.SmartEtfApp;
import id.tech.cakra.smartetf.domain.EksekusiHeader;
import id.tech.cakra.smartetf.repository.EksekusiHeaderRepository;
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
 * Integration tests for the {@link EksekusiHeaderResource} REST controller.
 */
@SpringBootTest(classes = SmartEtfApp.class)
public class EksekusiHeaderResourceIT {

    private static final String DEFAULT_NO_EKSEKUSI = "AAAAAAAAAA";
    private static final String UPDATED_NO_EKSEKUSI = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_TANGGAL_ENTRI = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TANGGAL_ENTRI = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_TANGGAL_ENTRI = LocalDate.ofEpochDay(-1L);

    private static final LocalDate DEFAULT_TANGGAL_TRADE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TANGGAL_TRADE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_TANGGAL_TRADE = LocalDate.ofEpochDay(-1L);

    private static final LocalDate DEFAULT_TANGGAL_SETTLE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TANGGAL_SETTLE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_TANGGAL_SETTLE = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_KODE_BROKER = "AAAAAAAAAA";
    private static final String UPDATED_KODE_BROKER = "BBBBBBBBBB";

    @Autowired
    private EksekusiHeaderRepository eksekusiHeaderRepository;

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

    private MockMvc restEksekusiHeaderMockMvc;

    private EksekusiHeader eksekusiHeader;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EksekusiHeaderResource eksekusiHeaderResource = new EksekusiHeaderResource(eksekusiHeaderRepository);
        this.restEksekusiHeaderMockMvc = MockMvcBuilders.standaloneSetup(eksekusiHeaderResource)
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
    public static EksekusiHeader createEntity(EntityManager em) {
        EksekusiHeader eksekusiHeader = new EksekusiHeader()
            .noEksekusi(DEFAULT_NO_EKSEKUSI)
            .tanggalEntri(DEFAULT_TANGGAL_ENTRI)
            .tanggalTrade(DEFAULT_TANGGAL_TRADE)
            .tanggalSettle(DEFAULT_TANGGAL_SETTLE)
            .kodeBroker(DEFAULT_KODE_BROKER);
        return eksekusiHeader;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EksekusiHeader createUpdatedEntity(EntityManager em) {
        EksekusiHeader eksekusiHeader = new EksekusiHeader()
            .noEksekusi(UPDATED_NO_EKSEKUSI)
            .tanggalEntri(UPDATED_TANGGAL_ENTRI)
            .tanggalTrade(UPDATED_TANGGAL_TRADE)
            .tanggalSettle(UPDATED_TANGGAL_SETTLE)
            .kodeBroker(UPDATED_KODE_BROKER);
        return eksekusiHeader;
    }

    @BeforeEach
    public void initTest() {
        eksekusiHeader = createEntity(em);
    }

    @Test
    @Transactional
    public void createEksekusiHeader() throws Exception {
        int databaseSizeBeforeCreate = eksekusiHeaderRepository.findAll().size();

        // Create the EksekusiHeader
        restEksekusiHeaderMockMvc.perform(post("/api/eksekusi-headers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eksekusiHeader)))
            .andExpect(status().isCreated());

        // Validate the EksekusiHeader in the database
        List<EksekusiHeader> eksekusiHeaderList = eksekusiHeaderRepository.findAll();
        assertThat(eksekusiHeaderList).hasSize(databaseSizeBeforeCreate + 1);
        EksekusiHeader testEksekusiHeader = eksekusiHeaderList.get(eksekusiHeaderList.size() - 1);
        assertThat(testEksekusiHeader.getNoEksekusi()).isEqualTo(DEFAULT_NO_EKSEKUSI);
        assertThat(testEksekusiHeader.getTanggalEntri()).isEqualTo(DEFAULT_TANGGAL_ENTRI);
        assertThat(testEksekusiHeader.getTanggalTrade()).isEqualTo(DEFAULT_TANGGAL_TRADE);
        assertThat(testEksekusiHeader.getTanggalSettle()).isEqualTo(DEFAULT_TANGGAL_SETTLE);
        assertThat(testEksekusiHeader.getKodeBroker()).isEqualTo(DEFAULT_KODE_BROKER);
    }

    @Test
    @Transactional
    public void createEksekusiHeaderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = eksekusiHeaderRepository.findAll().size();

        // Create the EksekusiHeader with an existing ID
        eksekusiHeader.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEksekusiHeaderMockMvc.perform(post("/api/eksekusi-headers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eksekusiHeader)))
            .andExpect(status().isBadRequest());

        // Validate the EksekusiHeader in the database
        List<EksekusiHeader> eksekusiHeaderList = eksekusiHeaderRepository.findAll();
        assertThat(eksekusiHeaderList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNoEksekusiIsRequired() throws Exception {
        int databaseSizeBeforeTest = eksekusiHeaderRepository.findAll().size();
        // set the field null
        eksekusiHeader.setNoEksekusi(null);

        // Create the EksekusiHeader, which fails.

        restEksekusiHeaderMockMvc.perform(post("/api/eksekusi-headers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eksekusiHeader)))
            .andExpect(status().isBadRequest());

        List<EksekusiHeader> eksekusiHeaderList = eksekusiHeaderRepository.findAll();
        assertThat(eksekusiHeaderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEksekusiHeaders() throws Exception {
        // Initialize the database
        eksekusiHeaderRepository.saveAndFlush(eksekusiHeader);

        // Get all the eksekusiHeaderList
        restEksekusiHeaderMockMvc.perform(get("/api/eksekusi-headers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(eksekusiHeader.getId().intValue())))
            .andExpect(jsonPath("$.[*].noEksekusi").value(hasItem(DEFAULT_NO_EKSEKUSI.toString())))
            .andExpect(jsonPath("$.[*].tanggalEntri").value(hasItem(DEFAULT_TANGGAL_ENTRI.toString())))
            .andExpect(jsonPath("$.[*].tanggalTrade").value(hasItem(DEFAULT_TANGGAL_TRADE.toString())))
            .andExpect(jsonPath("$.[*].tanggalSettle").value(hasItem(DEFAULT_TANGGAL_SETTLE.toString())))
            .andExpect(jsonPath("$.[*].kodeBroker").value(hasItem(DEFAULT_KODE_BROKER.toString())));
    }
    
    @Test
    @Transactional
    public void getEksekusiHeader() throws Exception {
        // Initialize the database
        eksekusiHeaderRepository.saveAndFlush(eksekusiHeader);

        // Get the eksekusiHeader
        restEksekusiHeaderMockMvc.perform(get("/api/eksekusi-headers/{id}", eksekusiHeader.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(eksekusiHeader.getId().intValue()))
            .andExpect(jsonPath("$.noEksekusi").value(DEFAULT_NO_EKSEKUSI.toString()))
            .andExpect(jsonPath("$.tanggalEntri").value(DEFAULT_TANGGAL_ENTRI.toString()))
            .andExpect(jsonPath("$.tanggalTrade").value(DEFAULT_TANGGAL_TRADE.toString()))
            .andExpect(jsonPath("$.tanggalSettle").value(DEFAULT_TANGGAL_SETTLE.toString()))
            .andExpect(jsonPath("$.kodeBroker").value(DEFAULT_KODE_BROKER.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEksekusiHeader() throws Exception {
        // Get the eksekusiHeader
        restEksekusiHeaderMockMvc.perform(get("/api/eksekusi-headers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEksekusiHeader() throws Exception {
        // Initialize the database
        eksekusiHeaderRepository.saveAndFlush(eksekusiHeader);

        int databaseSizeBeforeUpdate = eksekusiHeaderRepository.findAll().size();

        // Update the eksekusiHeader
        EksekusiHeader updatedEksekusiHeader = eksekusiHeaderRepository.findById(eksekusiHeader.getId()).get();
        // Disconnect from session so that the updates on updatedEksekusiHeader are not directly saved in db
        em.detach(updatedEksekusiHeader);
        updatedEksekusiHeader
            .noEksekusi(UPDATED_NO_EKSEKUSI)
            .tanggalEntri(UPDATED_TANGGAL_ENTRI)
            .tanggalTrade(UPDATED_TANGGAL_TRADE)
            .tanggalSettle(UPDATED_TANGGAL_SETTLE)
            .kodeBroker(UPDATED_KODE_BROKER);

        restEksekusiHeaderMockMvc.perform(put("/api/eksekusi-headers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedEksekusiHeader)))
            .andExpect(status().isOk());

        // Validate the EksekusiHeader in the database
        List<EksekusiHeader> eksekusiHeaderList = eksekusiHeaderRepository.findAll();
        assertThat(eksekusiHeaderList).hasSize(databaseSizeBeforeUpdate);
        EksekusiHeader testEksekusiHeader = eksekusiHeaderList.get(eksekusiHeaderList.size() - 1);
        assertThat(testEksekusiHeader.getNoEksekusi()).isEqualTo(UPDATED_NO_EKSEKUSI);
        assertThat(testEksekusiHeader.getTanggalEntri()).isEqualTo(UPDATED_TANGGAL_ENTRI);
        assertThat(testEksekusiHeader.getTanggalTrade()).isEqualTo(UPDATED_TANGGAL_TRADE);
        assertThat(testEksekusiHeader.getTanggalSettle()).isEqualTo(UPDATED_TANGGAL_SETTLE);
        assertThat(testEksekusiHeader.getKodeBroker()).isEqualTo(UPDATED_KODE_BROKER);
    }

    @Test
    @Transactional
    public void updateNonExistingEksekusiHeader() throws Exception {
        int databaseSizeBeforeUpdate = eksekusiHeaderRepository.findAll().size();

        // Create the EksekusiHeader

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEksekusiHeaderMockMvc.perform(put("/api/eksekusi-headers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eksekusiHeader)))
            .andExpect(status().isBadRequest());

        // Validate the EksekusiHeader in the database
        List<EksekusiHeader> eksekusiHeaderList = eksekusiHeaderRepository.findAll();
        assertThat(eksekusiHeaderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEksekusiHeader() throws Exception {
        // Initialize the database
        eksekusiHeaderRepository.saveAndFlush(eksekusiHeader);

        int databaseSizeBeforeDelete = eksekusiHeaderRepository.findAll().size();

        // Delete the eksekusiHeader
        restEksekusiHeaderMockMvc.perform(delete("/api/eksekusi-headers/{id}", eksekusiHeader.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EksekusiHeader> eksekusiHeaderList = eksekusiHeaderRepository.findAll();
        assertThat(eksekusiHeaderList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EksekusiHeader.class);
        EksekusiHeader eksekusiHeader1 = new EksekusiHeader();
        eksekusiHeader1.setId(1L);
        EksekusiHeader eksekusiHeader2 = new EksekusiHeader();
        eksekusiHeader2.setId(eksekusiHeader1.getId());
        assertThat(eksekusiHeader1).isEqualTo(eksekusiHeader2);
        eksekusiHeader2.setId(2L);
        assertThat(eksekusiHeader1).isNotEqualTo(eksekusiHeader2);
        eksekusiHeader1.setId(null);
        assertThat(eksekusiHeader1).isNotEqualTo(eksekusiHeader2);
    }
}
