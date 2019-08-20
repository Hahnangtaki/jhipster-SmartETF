package id.tech.cakra.smartetf.web.rest;

import id.tech.cakra.smartetf.SmartEtfApp;
import id.tech.cakra.smartetf.domain.PengajuanGadaiEfekHeader;
import id.tech.cakra.smartetf.repository.PengajuanGadaiEfekHeaderRepository;
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
 * Integration tests for the {@link PengajuanGadaiEfekHeaderResource} REST controller.
 */
@SpringBootTest(classes = SmartEtfApp.class)
public class PengajuanGadaiEfekHeaderResourceIT {

    private static final String DEFAULT_NO_PENGAJUAN_GADAI = "AAAAAAAAAA";
    private static final String UPDATED_NO_PENGAJUAN_GADAI = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_TGL_ENTRI = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TGL_ENTRI = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_TGL_ENTRI = LocalDate.ofEpochDay(-1L);

    private static final LocalDate DEFAULT_TGL_EFEK_TERIMA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TGL_EFEK_TERIMA = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_TGL_EFEK_TERIMA = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_KODE_NASABAH = "AAAAAAAAAA";
    private static final String UPDATED_KODE_NASABAH = "BBBBBBBBBB";

    private static final Double DEFAULT_NILAI_PINJAMAN = 0D;
    private static final Double UPDATED_NILAI_PINJAMAN = 1D;
    private static final Double SMALLER_NILAI_PINJAMAN = 0D - 1D;

    private static final String DEFAULT_COUNTER_PART_INSTRUKSI = "AAAAAAAAAA";
    private static final String UPDATED_COUNTER_PART_INSTRUKSI = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "A";
    private static final String UPDATED_STATUS = "B";

    @Autowired
    private PengajuanGadaiEfekHeaderRepository pengajuanGadaiEfekHeaderRepository;

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

    private MockMvc restPengajuanGadaiEfekHeaderMockMvc;

    private PengajuanGadaiEfekHeader pengajuanGadaiEfekHeader;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PengajuanGadaiEfekHeaderResource pengajuanGadaiEfekHeaderResource = new PengajuanGadaiEfekHeaderResource(pengajuanGadaiEfekHeaderRepository);
        this.restPengajuanGadaiEfekHeaderMockMvc = MockMvcBuilders.standaloneSetup(pengajuanGadaiEfekHeaderResource)
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
    public static PengajuanGadaiEfekHeader createEntity(EntityManager em) {
        PengajuanGadaiEfekHeader pengajuanGadaiEfekHeader = new PengajuanGadaiEfekHeader()
            .noPengajuanGadai(DEFAULT_NO_PENGAJUAN_GADAI)
            .tglEntri(DEFAULT_TGL_ENTRI)
            .tglEfekTerima(DEFAULT_TGL_EFEK_TERIMA)
            .kodeNasabah(DEFAULT_KODE_NASABAH)
            .nilaiPinjaman(DEFAULT_NILAI_PINJAMAN)
            .counterPartInstruksi(DEFAULT_COUNTER_PART_INSTRUKSI)
            .status(DEFAULT_STATUS);
        return pengajuanGadaiEfekHeader;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PengajuanGadaiEfekHeader createUpdatedEntity(EntityManager em) {
        PengajuanGadaiEfekHeader pengajuanGadaiEfekHeader = new PengajuanGadaiEfekHeader()
            .noPengajuanGadai(UPDATED_NO_PENGAJUAN_GADAI)
            .tglEntri(UPDATED_TGL_ENTRI)
            .tglEfekTerima(UPDATED_TGL_EFEK_TERIMA)
            .kodeNasabah(UPDATED_KODE_NASABAH)
            .nilaiPinjaman(UPDATED_NILAI_PINJAMAN)
            .counterPartInstruksi(UPDATED_COUNTER_PART_INSTRUKSI)
            .status(UPDATED_STATUS);
        return pengajuanGadaiEfekHeader;
    }

    @BeforeEach
    public void initTest() {
        pengajuanGadaiEfekHeader = createEntity(em);
    }

    @Test
    @Transactional
    public void createPengajuanGadaiEfekHeader() throws Exception {
        int databaseSizeBeforeCreate = pengajuanGadaiEfekHeaderRepository.findAll().size();

        // Create the PengajuanGadaiEfekHeader
        restPengajuanGadaiEfekHeaderMockMvc.perform(post("/api/pengajuan-gadai-efek-headers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pengajuanGadaiEfekHeader)))
            .andExpect(status().isCreated());

        // Validate the PengajuanGadaiEfekHeader in the database
        List<PengajuanGadaiEfekHeader> pengajuanGadaiEfekHeaderList = pengajuanGadaiEfekHeaderRepository.findAll();
        assertThat(pengajuanGadaiEfekHeaderList).hasSize(databaseSizeBeforeCreate + 1);
        PengajuanGadaiEfekHeader testPengajuanGadaiEfekHeader = pengajuanGadaiEfekHeaderList.get(pengajuanGadaiEfekHeaderList.size() - 1);
        assertThat(testPengajuanGadaiEfekHeader.getNoPengajuanGadai()).isEqualTo(DEFAULT_NO_PENGAJUAN_GADAI);
        assertThat(testPengajuanGadaiEfekHeader.getTglEntri()).isEqualTo(DEFAULT_TGL_ENTRI);
        assertThat(testPengajuanGadaiEfekHeader.getTglEfekTerima()).isEqualTo(DEFAULT_TGL_EFEK_TERIMA);
        assertThat(testPengajuanGadaiEfekHeader.getKodeNasabah()).isEqualTo(DEFAULT_KODE_NASABAH);
        assertThat(testPengajuanGadaiEfekHeader.getNilaiPinjaman()).isEqualTo(DEFAULT_NILAI_PINJAMAN);
        assertThat(testPengajuanGadaiEfekHeader.getCounterPartInstruksi()).isEqualTo(DEFAULT_COUNTER_PART_INSTRUKSI);
        assertThat(testPengajuanGadaiEfekHeader.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createPengajuanGadaiEfekHeaderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pengajuanGadaiEfekHeaderRepository.findAll().size();

        // Create the PengajuanGadaiEfekHeader with an existing ID
        pengajuanGadaiEfekHeader.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPengajuanGadaiEfekHeaderMockMvc.perform(post("/api/pengajuan-gadai-efek-headers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pengajuanGadaiEfekHeader)))
            .andExpect(status().isBadRequest());

        // Validate the PengajuanGadaiEfekHeader in the database
        List<PengajuanGadaiEfekHeader> pengajuanGadaiEfekHeaderList = pengajuanGadaiEfekHeaderRepository.findAll();
        assertThat(pengajuanGadaiEfekHeaderList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNoPengajuanGadaiIsRequired() throws Exception {
        int databaseSizeBeforeTest = pengajuanGadaiEfekHeaderRepository.findAll().size();
        // set the field null
        pengajuanGadaiEfekHeader.setNoPengajuanGadai(null);

        // Create the PengajuanGadaiEfekHeader, which fails.

        restPengajuanGadaiEfekHeaderMockMvc.perform(post("/api/pengajuan-gadai-efek-headers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pengajuanGadaiEfekHeader)))
            .andExpect(status().isBadRequest());

        List<PengajuanGadaiEfekHeader> pengajuanGadaiEfekHeaderList = pengajuanGadaiEfekHeaderRepository.findAll();
        assertThat(pengajuanGadaiEfekHeaderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = pengajuanGadaiEfekHeaderRepository.findAll().size();
        // set the field null
        pengajuanGadaiEfekHeader.setStatus(null);

        // Create the PengajuanGadaiEfekHeader, which fails.

        restPengajuanGadaiEfekHeaderMockMvc.perform(post("/api/pengajuan-gadai-efek-headers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pengajuanGadaiEfekHeader)))
            .andExpect(status().isBadRequest());

        List<PengajuanGadaiEfekHeader> pengajuanGadaiEfekHeaderList = pengajuanGadaiEfekHeaderRepository.findAll();
        assertThat(pengajuanGadaiEfekHeaderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPengajuanGadaiEfekHeaders() throws Exception {
        // Initialize the database
        pengajuanGadaiEfekHeaderRepository.saveAndFlush(pengajuanGadaiEfekHeader);

        // Get all the pengajuanGadaiEfekHeaderList
        restPengajuanGadaiEfekHeaderMockMvc.perform(get("/api/pengajuan-gadai-efek-headers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pengajuanGadaiEfekHeader.getId().intValue())))
            .andExpect(jsonPath("$.[*].noPengajuanGadai").value(hasItem(DEFAULT_NO_PENGAJUAN_GADAI.toString())))
            .andExpect(jsonPath("$.[*].tglEntri").value(hasItem(DEFAULT_TGL_ENTRI.toString())))
            .andExpect(jsonPath("$.[*].tglEfekTerima").value(hasItem(DEFAULT_TGL_EFEK_TERIMA.toString())))
            .andExpect(jsonPath("$.[*].kodeNasabah").value(hasItem(DEFAULT_KODE_NASABAH.toString())))
            .andExpect(jsonPath("$.[*].nilaiPinjaman").value(hasItem(DEFAULT_NILAI_PINJAMAN.doubleValue())))
            .andExpect(jsonPath("$.[*].counterPartInstruksi").value(hasItem(DEFAULT_COUNTER_PART_INSTRUKSI.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getPengajuanGadaiEfekHeader() throws Exception {
        // Initialize the database
        pengajuanGadaiEfekHeaderRepository.saveAndFlush(pengajuanGadaiEfekHeader);

        // Get the pengajuanGadaiEfekHeader
        restPengajuanGadaiEfekHeaderMockMvc.perform(get("/api/pengajuan-gadai-efek-headers/{id}", pengajuanGadaiEfekHeader.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pengajuanGadaiEfekHeader.getId().intValue()))
            .andExpect(jsonPath("$.noPengajuanGadai").value(DEFAULT_NO_PENGAJUAN_GADAI.toString()))
            .andExpect(jsonPath("$.tglEntri").value(DEFAULT_TGL_ENTRI.toString()))
            .andExpect(jsonPath("$.tglEfekTerima").value(DEFAULT_TGL_EFEK_TERIMA.toString()))
            .andExpect(jsonPath("$.kodeNasabah").value(DEFAULT_KODE_NASABAH.toString()))
            .andExpect(jsonPath("$.nilaiPinjaman").value(DEFAULT_NILAI_PINJAMAN.doubleValue()))
            .andExpect(jsonPath("$.counterPartInstruksi").value(DEFAULT_COUNTER_PART_INSTRUKSI.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPengajuanGadaiEfekHeader() throws Exception {
        // Get the pengajuanGadaiEfekHeader
        restPengajuanGadaiEfekHeaderMockMvc.perform(get("/api/pengajuan-gadai-efek-headers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePengajuanGadaiEfekHeader() throws Exception {
        // Initialize the database
        pengajuanGadaiEfekHeaderRepository.saveAndFlush(pengajuanGadaiEfekHeader);

        int databaseSizeBeforeUpdate = pengajuanGadaiEfekHeaderRepository.findAll().size();

        // Update the pengajuanGadaiEfekHeader
        PengajuanGadaiEfekHeader updatedPengajuanGadaiEfekHeader = pengajuanGadaiEfekHeaderRepository.findById(pengajuanGadaiEfekHeader.getId()).get();
        // Disconnect from session so that the updates on updatedPengajuanGadaiEfekHeader are not directly saved in db
        em.detach(updatedPengajuanGadaiEfekHeader);
        updatedPengajuanGadaiEfekHeader
            .noPengajuanGadai(UPDATED_NO_PENGAJUAN_GADAI)
            .tglEntri(UPDATED_TGL_ENTRI)
            .tglEfekTerima(UPDATED_TGL_EFEK_TERIMA)
            .kodeNasabah(UPDATED_KODE_NASABAH)
            .nilaiPinjaman(UPDATED_NILAI_PINJAMAN)
            .counterPartInstruksi(UPDATED_COUNTER_PART_INSTRUKSI)
            .status(UPDATED_STATUS);

        restPengajuanGadaiEfekHeaderMockMvc.perform(put("/api/pengajuan-gadai-efek-headers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPengajuanGadaiEfekHeader)))
            .andExpect(status().isOk());

        // Validate the PengajuanGadaiEfekHeader in the database
        List<PengajuanGadaiEfekHeader> pengajuanGadaiEfekHeaderList = pengajuanGadaiEfekHeaderRepository.findAll();
        assertThat(pengajuanGadaiEfekHeaderList).hasSize(databaseSizeBeforeUpdate);
        PengajuanGadaiEfekHeader testPengajuanGadaiEfekHeader = pengajuanGadaiEfekHeaderList.get(pengajuanGadaiEfekHeaderList.size() - 1);
        assertThat(testPengajuanGadaiEfekHeader.getNoPengajuanGadai()).isEqualTo(UPDATED_NO_PENGAJUAN_GADAI);
        assertThat(testPengajuanGadaiEfekHeader.getTglEntri()).isEqualTo(UPDATED_TGL_ENTRI);
        assertThat(testPengajuanGadaiEfekHeader.getTglEfekTerima()).isEqualTo(UPDATED_TGL_EFEK_TERIMA);
        assertThat(testPengajuanGadaiEfekHeader.getKodeNasabah()).isEqualTo(UPDATED_KODE_NASABAH);
        assertThat(testPengajuanGadaiEfekHeader.getNilaiPinjaman()).isEqualTo(UPDATED_NILAI_PINJAMAN);
        assertThat(testPengajuanGadaiEfekHeader.getCounterPartInstruksi()).isEqualTo(UPDATED_COUNTER_PART_INSTRUKSI);
        assertThat(testPengajuanGadaiEfekHeader.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingPengajuanGadaiEfekHeader() throws Exception {
        int databaseSizeBeforeUpdate = pengajuanGadaiEfekHeaderRepository.findAll().size();

        // Create the PengajuanGadaiEfekHeader

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPengajuanGadaiEfekHeaderMockMvc.perform(put("/api/pengajuan-gadai-efek-headers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pengajuanGadaiEfekHeader)))
            .andExpect(status().isBadRequest());

        // Validate the PengajuanGadaiEfekHeader in the database
        List<PengajuanGadaiEfekHeader> pengajuanGadaiEfekHeaderList = pengajuanGadaiEfekHeaderRepository.findAll();
        assertThat(pengajuanGadaiEfekHeaderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePengajuanGadaiEfekHeader() throws Exception {
        // Initialize the database
        pengajuanGadaiEfekHeaderRepository.saveAndFlush(pengajuanGadaiEfekHeader);

        int databaseSizeBeforeDelete = pengajuanGadaiEfekHeaderRepository.findAll().size();

        // Delete the pengajuanGadaiEfekHeader
        restPengajuanGadaiEfekHeaderMockMvc.perform(delete("/api/pengajuan-gadai-efek-headers/{id}", pengajuanGadaiEfekHeader.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PengajuanGadaiEfekHeader> pengajuanGadaiEfekHeaderList = pengajuanGadaiEfekHeaderRepository.findAll();
        assertThat(pengajuanGadaiEfekHeaderList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PengajuanGadaiEfekHeader.class);
        PengajuanGadaiEfekHeader pengajuanGadaiEfekHeader1 = new PengajuanGadaiEfekHeader();
        pengajuanGadaiEfekHeader1.setId(1L);
        PengajuanGadaiEfekHeader pengajuanGadaiEfekHeader2 = new PengajuanGadaiEfekHeader();
        pengajuanGadaiEfekHeader2.setId(pengajuanGadaiEfekHeader1.getId());
        assertThat(pengajuanGadaiEfekHeader1).isEqualTo(pengajuanGadaiEfekHeader2);
        pengajuanGadaiEfekHeader2.setId(2L);
        assertThat(pengajuanGadaiEfekHeader1).isNotEqualTo(pengajuanGadaiEfekHeader2);
        pengajuanGadaiEfekHeader1.setId(null);
        assertThat(pengajuanGadaiEfekHeader1).isNotEqualTo(pengajuanGadaiEfekHeader2);
    }
}
