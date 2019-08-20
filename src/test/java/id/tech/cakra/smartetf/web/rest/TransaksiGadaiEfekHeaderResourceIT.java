package id.tech.cakra.smartetf.web.rest;

import id.tech.cakra.smartetf.SmartEtfApp;
import id.tech.cakra.smartetf.domain.TransaksiGadaiEfekHeader;
import id.tech.cakra.smartetf.repository.TransaksiGadaiEfekHeaderRepository;
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
 * Integration tests for the {@link TransaksiGadaiEfekHeaderResource} REST controller.
 */
@SpringBootTest(classes = SmartEtfApp.class)
public class TransaksiGadaiEfekHeaderResourceIT {

    private static final String DEFAULT_NO_KONTRAK = "AAAAAAAAAA";
    private static final String UPDATED_NO_KONTRAK = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_TGL_ENTRI = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TGL_ENTRI = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_TGL_ENTRI = LocalDate.ofEpochDay(-1L);

    private static final LocalDate DEFAULT_TGL_PENCAIRAN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TGL_PENCAIRAN = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_TGL_PENCAIRAN = LocalDate.ofEpochDay(-1L);

    private static final LocalDate DEFAULT_TGL_JATUH_TEMPO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TGL_JATUH_TEMPO = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_TGL_JATUH_TEMPO = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_NO_PENGAJUAN_GADAI = "AAAAAAAAAA";
    private static final String UPDATED_NO_PENGAJUAN_GADAI = "BBBBBBBBBB";

    private static final String DEFAULT_KODE_NASABAH = "AAAAAAAAAA";
    private static final String UPDATED_KODE_NASABAH = "BBBBBBBBBB";

    private static final Double DEFAULT_NILAI_KEWAJIBAN = 0D;
    private static final Double UPDATED_NILAI_KEWAJIBAN = 1D;
    private static final Double SMALLER_NILAI_KEWAJIBAN = 0D - 1D;

    private static final LocalDate DEFAULT_TGL_KIRIM_EFEK = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TGL_KIRIM_EFEK = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_TGL_KIRIM_EFEK = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_COUNTER_PART_INSTRUKSI = "AAAAAAAAAA";
    private static final String UPDATED_COUNTER_PART_INSTRUKSI = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    @Autowired
    private TransaksiGadaiEfekHeaderRepository transaksiGadaiEfekHeaderRepository;

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

    private MockMvc restTransaksiGadaiEfekHeaderMockMvc;

    private TransaksiGadaiEfekHeader transaksiGadaiEfekHeader;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TransaksiGadaiEfekHeaderResource transaksiGadaiEfekHeaderResource = new TransaksiGadaiEfekHeaderResource(transaksiGadaiEfekHeaderRepository);
        this.restTransaksiGadaiEfekHeaderMockMvc = MockMvcBuilders.standaloneSetup(transaksiGadaiEfekHeaderResource)
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
    public static TransaksiGadaiEfekHeader createEntity(EntityManager em) {
        TransaksiGadaiEfekHeader transaksiGadaiEfekHeader = new TransaksiGadaiEfekHeader()
            .noKontrak(DEFAULT_NO_KONTRAK)
            .tglEntri(DEFAULT_TGL_ENTRI)
            .tglPencairan(DEFAULT_TGL_PENCAIRAN)
            .tglJatuhTempo(DEFAULT_TGL_JATUH_TEMPO)
            .noPengajuanGadai(DEFAULT_NO_PENGAJUAN_GADAI)
            .kodeNasabah(DEFAULT_KODE_NASABAH)
            .nilaiKewajiban(DEFAULT_NILAI_KEWAJIBAN)
            .tglKirimEfek(DEFAULT_TGL_KIRIM_EFEK)
            .counterPartInstruksi(DEFAULT_COUNTER_PART_INSTRUKSI)
            .status(DEFAULT_STATUS);
        return transaksiGadaiEfekHeader;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TransaksiGadaiEfekHeader createUpdatedEntity(EntityManager em) {
        TransaksiGadaiEfekHeader transaksiGadaiEfekHeader = new TransaksiGadaiEfekHeader()
            .noKontrak(UPDATED_NO_KONTRAK)
            .tglEntri(UPDATED_TGL_ENTRI)
            .tglPencairan(UPDATED_TGL_PENCAIRAN)
            .tglJatuhTempo(UPDATED_TGL_JATUH_TEMPO)
            .noPengajuanGadai(UPDATED_NO_PENGAJUAN_GADAI)
            .kodeNasabah(UPDATED_KODE_NASABAH)
            .nilaiKewajiban(UPDATED_NILAI_KEWAJIBAN)
            .tglKirimEfek(UPDATED_TGL_KIRIM_EFEK)
            .counterPartInstruksi(UPDATED_COUNTER_PART_INSTRUKSI)
            .status(UPDATED_STATUS);
        return transaksiGadaiEfekHeader;
    }

    @BeforeEach
    public void initTest() {
        transaksiGadaiEfekHeader = createEntity(em);
    }

    @Test
    @Transactional
    public void createTransaksiGadaiEfekHeader() throws Exception {
        int databaseSizeBeforeCreate = transaksiGadaiEfekHeaderRepository.findAll().size();

        // Create the TransaksiGadaiEfekHeader
        restTransaksiGadaiEfekHeaderMockMvc.perform(post("/api/transaksi-gadai-efek-headers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transaksiGadaiEfekHeader)))
            .andExpect(status().isCreated());

        // Validate the TransaksiGadaiEfekHeader in the database
        List<TransaksiGadaiEfekHeader> transaksiGadaiEfekHeaderList = transaksiGadaiEfekHeaderRepository.findAll();
        assertThat(transaksiGadaiEfekHeaderList).hasSize(databaseSizeBeforeCreate + 1);
        TransaksiGadaiEfekHeader testTransaksiGadaiEfekHeader = transaksiGadaiEfekHeaderList.get(transaksiGadaiEfekHeaderList.size() - 1);
        assertThat(testTransaksiGadaiEfekHeader.getNoKontrak()).isEqualTo(DEFAULT_NO_KONTRAK);
        assertThat(testTransaksiGadaiEfekHeader.getTglEntri()).isEqualTo(DEFAULT_TGL_ENTRI);
        assertThat(testTransaksiGadaiEfekHeader.getTglPencairan()).isEqualTo(DEFAULT_TGL_PENCAIRAN);
        assertThat(testTransaksiGadaiEfekHeader.getTglJatuhTempo()).isEqualTo(DEFAULT_TGL_JATUH_TEMPO);
        assertThat(testTransaksiGadaiEfekHeader.getNoPengajuanGadai()).isEqualTo(DEFAULT_NO_PENGAJUAN_GADAI);
        assertThat(testTransaksiGadaiEfekHeader.getKodeNasabah()).isEqualTo(DEFAULT_KODE_NASABAH);
        assertThat(testTransaksiGadaiEfekHeader.getNilaiKewajiban()).isEqualTo(DEFAULT_NILAI_KEWAJIBAN);
        assertThat(testTransaksiGadaiEfekHeader.getTglKirimEfek()).isEqualTo(DEFAULT_TGL_KIRIM_EFEK);
        assertThat(testTransaksiGadaiEfekHeader.getCounterPartInstruksi()).isEqualTo(DEFAULT_COUNTER_PART_INSTRUKSI);
        assertThat(testTransaksiGadaiEfekHeader.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createTransaksiGadaiEfekHeaderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = transaksiGadaiEfekHeaderRepository.findAll().size();

        // Create the TransaksiGadaiEfekHeader with an existing ID
        transaksiGadaiEfekHeader.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTransaksiGadaiEfekHeaderMockMvc.perform(post("/api/transaksi-gadai-efek-headers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transaksiGadaiEfekHeader)))
            .andExpect(status().isBadRequest());

        // Validate the TransaksiGadaiEfekHeader in the database
        List<TransaksiGadaiEfekHeader> transaksiGadaiEfekHeaderList = transaksiGadaiEfekHeaderRepository.findAll();
        assertThat(transaksiGadaiEfekHeaderList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNoKontrakIsRequired() throws Exception {
        int databaseSizeBeforeTest = transaksiGadaiEfekHeaderRepository.findAll().size();
        // set the field null
        transaksiGadaiEfekHeader.setNoKontrak(null);

        // Create the TransaksiGadaiEfekHeader, which fails.

        restTransaksiGadaiEfekHeaderMockMvc.perform(post("/api/transaksi-gadai-efek-headers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transaksiGadaiEfekHeader)))
            .andExpect(status().isBadRequest());

        List<TransaksiGadaiEfekHeader> transaksiGadaiEfekHeaderList = transaksiGadaiEfekHeaderRepository.findAll();
        assertThat(transaksiGadaiEfekHeaderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTglEntriIsRequired() throws Exception {
        int databaseSizeBeforeTest = transaksiGadaiEfekHeaderRepository.findAll().size();
        // set the field null
        transaksiGadaiEfekHeader.setTglEntri(null);

        // Create the TransaksiGadaiEfekHeader, which fails.

        restTransaksiGadaiEfekHeaderMockMvc.perform(post("/api/transaksi-gadai-efek-headers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transaksiGadaiEfekHeader)))
            .andExpect(status().isBadRequest());

        List<TransaksiGadaiEfekHeader> transaksiGadaiEfekHeaderList = transaksiGadaiEfekHeaderRepository.findAll();
        assertThat(transaksiGadaiEfekHeaderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNilaiKewajibanIsRequired() throws Exception {
        int databaseSizeBeforeTest = transaksiGadaiEfekHeaderRepository.findAll().size();
        // set the field null
        transaksiGadaiEfekHeader.setNilaiKewajiban(null);

        // Create the TransaksiGadaiEfekHeader, which fails.

        restTransaksiGadaiEfekHeaderMockMvc.perform(post("/api/transaksi-gadai-efek-headers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transaksiGadaiEfekHeader)))
            .andExpect(status().isBadRequest());

        List<TransaksiGadaiEfekHeader> transaksiGadaiEfekHeaderList = transaksiGadaiEfekHeaderRepository.findAll();
        assertThat(transaksiGadaiEfekHeaderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTransaksiGadaiEfekHeaders() throws Exception {
        // Initialize the database
        transaksiGadaiEfekHeaderRepository.saveAndFlush(transaksiGadaiEfekHeader);

        // Get all the transaksiGadaiEfekHeaderList
        restTransaksiGadaiEfekHeaderMockMvc.perform(get("/api/transaksi-gadai-efek-headers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(transaksiGadaiEfekHeader.getId().intValue())))
            .andExpect(jsonPath("$.[*].noKontrak").value(hasItem(DEFAULT_NO_KONTRAK.toString())))
            .andExpect(jsonPath("$.[*].tglEntri").value(hasItem(DEFAULT_TGL_ENTRI.toString())))
            .andExpect(jsonPath("$.[*].tglPencairan").value(hasItem(DEFAULT_TGL_PENCAIRAN.toString())))
            .andExpect(jsonPath("$.[*].tglJatuhTempo").value(hasItem(DEFAULT_TGL_JATUH_TEMPO.toString())))
            .andExpect(jsonPath("$.[*].noPengajuanGadai").value(hasItem(DEFAULT_NO_PENGAJUAN_GADAI.toString())))
            .andExpect(jsonPath("$.[*].kodeNasabah").value(hasItem(DEFAULT_KODE_NASABAH.toString())))
            .andExpect(jsonPath("$.[*].nilaiKewajiban").value(hasItem(DEFAULT_NILAI_KEWAJIBAN.doubleValue())))
            .andExpect(jsonPath("$.[*].tglKirimEfek").value(hasItem(DEFAULT_TGL_KIRIM_EFEK.toString())))
            .andExpect(jsonPath("$.[*].counterPartInstruksi").value(hasItem(DEFAULT_COUNTER_PART_INSTRUKSI.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getTransaksiGadaiEfekHeader() throws Exception {
        // Initialize the database
        transaksiGadaiEfekHeaderRepository.saveAndFlush(transaksiGadaiEfekHeader);

        // Get the transaksiGadaiEfekHeader
        restTransaksiGadaiEfekHeaderMockMvc.perform(get("/api/transaksi-gadai-efek-headers/{id}", transaksiGadaiEfekHeader.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(transaksiGadaiEfekHeader.getId().intValue()))
            .andExpect(jsonPath("$.noKontrak").value(DEFAULT_NO_KONTRAK.toString()))
            .andExpect(jsonPath("$.tglEntri").value(DEFAULT_TGL_ENTRI.toString()))
            .andExpect(jsonPath("$.tglPencairan").value(DEFAULT_TGL_PENCAIRAN.toString()))
            .andExpect(jsonPath("$.tglJatuhTempo").value(DEFAULT_TGL_JATUH_TEMPO.toString()))
            .andExpect(jsonPath("$.noPengajuanGadai").value(DEFAULT_NO_PENGAJUAN_GADAI.toString()))
            .andExpect(jsonPath("$.kodeNasabah").value(DEFAULT_KODE_NASABAH.toString()))
            .andExpect(jsonPath("$.nilaiKewajiban").value(DEFAULT_NILAI_KEWAJIBAN.doubleValue()))
            .andExpect(jsonPath("$.tglKirimEfek").value(DEFAULT_TGL_KIRIM_EFEK.toString()))
            .andExpect(jsonPath("$.counterPartInstruksi").value(DEFAULT_COUNTER_PART_INSTRUKSI.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTransaksiGadaiEfekHeader() throws Exception {
        // Get the transaksiGadaiEfekHeader
        restTransaksiGadaiEfekHeaderMockMvc.perform(get("/api/transaksi-gadai-efek-headers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTransaksiGadaiEfekHeader() throws Exception {
        // Initialize the database
        transaksiGadaiEfekHeaderRepository.saveAndFlush(transaksiGadaiEfekHeader);

        int databaseSizeBeforeUpdate = transaksiGadaiEfekHeaderRepository.findAll().size();

        // Update the transaksiGadaiEfekHeader
        TransaksiGadaiEfekHeader updatedTransaksiGadaiEfekHeader = transaksiGadaiEfekHeaderRepository.findById(transaksiGadaiEfekHeader.getId()).get();
        // Disconnect from session so that the updates on updatedTransaksiGadaiEfekHeader are not directly saved in db
        em.detach(updatedTransaksiGadaiEfekHeader);
        updatedTransaksiGadaiEfekHeader
            .noKontrak(UPDATED_NO_KONTRAK)
            .tglEntri(UPDATED_TGL_ENTRI)
            .tglPencairan(UPDATED_TGL_PENCAIRAN)
            .tglJatuhTempo(UPDATED_TGL_JATUH_TEMPO)
            .noPengajuanGadai(UPDATED_NO_PENGAJUAN_GADAI)
            .kodeNasabah(UPDATED_KODE_NASABAH)
            .nilaiKewajiban(UPDATED_NILAI_KEWAJIBAN)
            .tglKirimEfek(UPDATED_TGL_KIRIM_EFEK)
            .counterPartInstruksi(UPDATED_COUNTER_PART_INSTRUKSI)
            .status(UPDATED_STATUS);

        restTransaksiGadaiEfekHeaderMockMvc.perform(put("/api/transaksi-gadai-efek-headers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTransaksiGadaiEfekHeader)))
            .andExpect(status().isOk());

        // Validate the TransaksiGadaiEfekHeader in the database
        List<TransaksiGadaiEfekHeader> transaksiGadaiEfekHeaderList = transaksiGadaiEfekHeaderRepository.findAll();
        assertThat(transaksiGadaiEfekHeaderList).hasSize(databaseSizeBeforeUpdate);
        TransaksiGadaiEfekHeader testTransaksiGadaiEfekHeader = transaksiGadaiEfekHeaderList.get(transaksiGadaiEfekHeaderList.size() - 1);
        assertThat(testTransaksiGadaiEfekHeader.getNoKontrak()).isEqualTo(UPDATED_NO_KONTRAK);
        assertThat(testTransaksiGadaiEfekHeader.getTglEntri()).isEqualTo(UPDATED_TGL_ENTRI);
        assertThat(testTransaksiGadaiEfekHeader.getTglPencairan()).isEqualTo(UPDATED_TGL_PENCAIRAN);
        assertThat(testTransaksiGadaiEfekHeader.getTglJatuhTempo()).isEqualTo(UPDATED_TGL_JATUH_TEMPO);
        assertThat(testTransaksiGadaiEfekHeader.getNoPengajuanGadai()).isEqualTo(UPDATED_NO_PENGAJUAN_GADAI);
        assertThat(testTransaksiGadaiEfekHeader.getKodeNasabah()).isEqualTo(UPDATED_KODE_NASABAH);
        assertThat(testTransaksiGadaiEfekHeader.getNilaiKewajiban()).isEqualTo(UPDATED_NILAI_KEWAJIBAN);
        assertThat(testTransaksiGadaiEfekHeader.getTglKirimEfek()).isEqualTo(UPDATED_TGL_KIRIM_EFEK);
        assertThat(testTransaksiGadaiEfekHeader.getCounterPartInstruksi()).isEqualTo(UPDATED_COUNTER_PART_INSTRUKSI);
        assertThat(testTransaksiGadaiEfekHeader.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingTransaksiGadaiEfekHeader() throws Exception {
        int databaseSizeBeforeUpdate = transaksiGadaiEfekHeaderRepository.findAll().size();

        // Create the TransaksiGadaiEfekHeader

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTransaksiGadaiEfekHeaderMockMvc.perform(put("/api/transaksi-gadai-efek-headers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transaksiGadaiEfekHeader)))
            .andExpect(status().isBadRequest());

        // Validate the TransaksiGadaiEfekHeader in the database
        List<TransaksiGadaiEfekHeader> transaksiGadaiEfekHeaderList = transaksiGadaiEfekHeaderRepository.findAll();
        assertThat(transaksiGadaiEfekHeaderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTransaksiGadaiEfekHeader() throws Exception {
        // Initialize the database
        transaksiGadaiEfekHeaderRepository.saveAndFlush(transaksiGadaiEfekHeader);

        int databaseSizeBeforeDelete = transaksiGadaiEfekHeaderRepository.findAll().size();

        // Delete the transaksiGadaiEfekHeader
        restTransaksiGadaiEfekHeaderMockMvc.perform(delete("/api/transaksi-gadai-efek-headers/{id}", transaksiGadaiEfekHeader.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TransaksiGadaiEfekHeader> transaksiGadaiEfekHeaderList = transaksiGadaiEfekHeaderRepository.findAll();
        assertThat(transaksiGadaiEfekHeaderList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TransaksiGadaiEfekHeader.class);
        TransaksiGadaiEfekHeader transaksiGadaiEfekHeader1 = new TransaksiGadaiEfekHeader();
        transaksiGadaiEfekHeader1.setId(1L);
        TransaksiGadaiEfekHeader transaksiGadaiEfekHeader2 = new TransaksiGadaiEfekHeader();
        transaksiGadaiEfekHeader2.setId(transaksiGadaiEfekHeader1.getId());
        assertThat(transaksiGadaiEfekHeader1).isEqualTo(transaksiGadaiEfekHeader2);
        transaksiGadaiEfekHeader2.setId(2L);
        assertThat(transaksiGadaiEfekHeader1).isNotEqualTo(transaksiGadaiEfekHeader2);
        transaksiGadaiEfekHeader1.setId(null);
        assertThat(transaksiGadaiEfekHeader1).isNotEqualTo(transaksiGadaiEfekHeader2);
    }
}
