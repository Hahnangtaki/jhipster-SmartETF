package id.tech.cakra.smartetf.web.rest;

import id.tech.cakra.smartetf.SmartEtfApp;
import id.tech.cakra.smartetf.domain.Efek;
import id.tech.cakra.smartetf.repository.EfekRepository;
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
 * Integration tests for the {@link EfekResource} REST controller.
 */
@SpringBootTest(classes = SmartEtfApp.class)
public class EfekResourceIT {

    private static final String DEFAULT_KODE_EFEK = "AAAAAAAAAA";
    private static final String UPDATED_KODE_EFEK = "BBBBBBBBBB";

    private static final String DEFAULT_NAMA_EFEK = "AAAAAAAAAA";
    private static final String UPDATED_NAMA_EFEK = "BBBBBBBBBB";

    private static final String DEFAULT_TIPE_EFEK = "AAAAAAAAAA";
    private static final String UPDATED_TIPE_EFEK = "BBBBBBBBBB";

    private static final Double DEFAULT_LAST_CLOSING_PRICE = 0D;
    private static final Double UPDATED_LAST_CLOSING_PRICE = 1D;
    private static final Double SMALLER_LAST_CLOSING_PRICE = 0D - 1D;

    private static final LocalDate DEFAULT_LAST_CLOSING_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LAST_CLOSING_DATE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_LAST_CLOSING_DATE = LocalDate.ofEpochDay(-1L);

    private static final Double DEFAULT_LAST_HAIR_CUT = 0D;
    private static final Double UPDATED_LAST_HAIR_CUT = 1D;
    private static final Double SMALLER_LAST_HAIR_CUT = 0D - 1D;

    private static final LocalDate DEFAULT_LAST_HAIR_CUT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LAST_HAIR_CUT_DATE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_LAST_HAIR_CUT_DATE = LocalDate.ofEpochDay(-1L);

    private static final Boolean DEFAULT_STATUS_GADAI = false;
    private static final Boolean UPDATED_STATUS_GADAI = true;

    private static final Long DEFAULT_JLH_EFEK_BEREDAR = 0L;
    private static final Long UPDATED_JLH_EFEK_BEREDAR = 1L;
    private static final Long SMALLER_JLH_EFEK_BEREDAR = 0L - 1L;

    private static final Double DEFAULT_BMPK = 0D;
    private static final Double UPDATED_BMPK = 1D;
    private static final Double SMALLER_BMPK = 0D - 1D;

    private static final String DEFAULT_BOND_RATING = "AAAAAAAAAA";
    private static final String UPDATED_BOND_RATING = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_BOND_MATURITY_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_BOND_MATURITY_DATE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_BOND_MATURITY_DATE = LocalDate.ofEpochDay(-1L);

    private static final Long DEFAULT_SATUAN = 1L;
    private static final Long UPDATED_SATUAN = 2L;
    private static final Long SMALLER_SATUAN = 1L - 1L;

    private static final String DEFAULT_STATUS = "A";
    private static final String UPDATED_STATUS = "B";

    @Autowired
    private EfekRepository efekRepository;

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

    private MockMvc restEfekMockMvc;

    private Efek efek;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EfekResource efekResource = new EfekResource(efekRepository);
        this.restEfekMockMvc = MockMvcBuilders.standaloneSetup(efekResource)
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
    public static Efek createEntity(EntityManager em) {
        Efek efek = new Efek()
            .kodeEfek(DEFAULT_KODE_EFEK)
            .namaEfek(DEFAULT_NAMA_EFEK)
            .tipeEfek(DEFAULT_TIPE_EFEK)
            .lastClosingPrice(DEFAULT_LAST_CLOSING_PRICE)
            .lastClosingDate(DEFAULT_LAST_CLOSING_DATE)
            .lastHairCut(DEFAULT_LAST_HAIR_CUT)
            .lastHairCutDate(DEFAULT_LAST_HAIR_CUT_DATE)
            .statusGadai(DEFAULT_STATUS_GADAI)
            .jlhEfekBeredar(DEFAULT_JLH_EFEK_BEREDAR)
            .bmpk(DEFAULT_BMPK)
            .bondRating(DEFAULT_BOND_RATING)
            .bondMaturityDate(DEFAULT_BOND_MATURITY_DATE)
            .satuan(DEFAULT_SATUAN)
            .status(DEFAULT_STATUS);
        return efek;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Efek createUpdatedEntity(EntityManager em) {
        Efek efek = new Efek()
            .kodeEfek(UPDATED_KODE_EFEK)
            .namaEfek(UPDATED_NAMA_EFEK)
            .tipeEfek(UPDATED_TIPE_EFEK)
            .lastClosingPrice(UPDATED_LAST_CLOSING_PRICE)
            .lastClosingDate(UPDATED_LAST_CLOSING_DATE)
            .lastHairCut(UPDATED_LAST_HAIR_CUT)
            .lastHairCutDate(UPDATED_LAST_HAIR_CUT_DATE)
            .statusGadai(UPDATED_STATUS_GADAI)
            .jlhEfekBeredar(UPDATED_JLH_EFEK_BEREDAR)
            .bmpk(UPDATED_BMPK)
            .bondRating(UPDATED_BOND_RATING)
            .bondMaturityDate(UPDATED_BOND_MATURITY_DATE)
            .satuan(UPDATED_SATUAN)
            .status(UPDATED_STATUS);
        return efek;
    }

    @BeforeEach
    public void initTest() {
        efek = createEntity(em);
    }

    @Test
    @Transactional
    public void createEfek() throws Exception {
        int databaseSizeBeforeCreate = efekRepository.findAll().size();

        // Create the Efek
        restEfekMockMvc.perform(post("/api/efeks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(efek)))
            .andExpect(status().isCreated());

        // Validate the Efek in the database
        List<Efek> efekList = efekRepository.findAll();
        assertThat(efekList).hasSize(databaseSizeBeforeCreate + 1);
        Efek testEfek = efekList.get(efekList.size() - 1);
        assertThat(testEfek.getKodeEfek()).isEqualTo(DEFAULT_KODE_EFEK);
        assertThat(testEfek.getNamaEfek()).isEqualTo(DEFAULT_NAMA_EFEK);
        assertThat(testEfek.getTipeEfek()).isEqualTo(DEFAULT_TIPE_EFEK);
        assertThat(testEfek.getLastClosingPrice()).isEqualTo(DEFAULT_LAST_CLOSING_PRICE);
        assertThat(testEfek.getLastClosingDate()).isEqualTo(DEFAULT_LAST_CLOSING_DATE);
        assertThat(testEfek.getLastHairCut()).isEqualTo(DEFAULT_LAST_HAIR_CUT);
        assertThat(testEfek.getLastHairCutDate()).isEqualTo(DEFAULT_LAST_HAIR_CUT_DATE);
        assertThat(testEfek.isStatusGadai()).isEqualTo(DEFAULT_STATUS_GADAI);
        assertThat(testEfek.getJlhEfekBeredar()).isEqualTo(DEFAULT_JLH_EFEK_BEREDAR);
        assertThat(testEfek.getBmpk()).isEqualTo(DEFAULT_BMPK);
        assertThat(testEfek.getBondRating()).isEqualTo(DEFAULT_BOND_RATING);
        assertThat(testEfek.getBondMaturityDate()).isEqualTo(DEFAULT_BOND_MATURITY_DATE);
        assertThat(testEfek.getSatuan()).isEqualTo(DEFAULT_SATUAN);
        assertThat(testEfek.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createEfekWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = efekRepository.findAll().size();

        // Create the Efek with an existing ID
        efek.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEfekMockMvc.perform(post("/api/efeks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(efek)))
            .andExpect(status().isBadRequest());

        // Validate the Efek in the database
        List<Efek> efekList = efekRepository.findAll();
        assertThat(efekList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkKodeEfekIsRequired() throws Exception {
        int databaseSizeBeforeTest = efekRepository.findAll().size();
        // set the field null
        efek.setKodeEfek(null);

        // Create the Efek, which fails.

        restEfekMockMvc.perform(post("/api/efeks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(efek)))
            .andExpect(status().isBadRequest());

        List<Efek> efekList = efekRepository.findAll();
        assertThat(efekList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNamaEfekIsRequired() throws Exception {
        int databaseSizeBeforeTest = efekRepository.findAll().size();
        // set the field null
        efek.setNamaEfek(null);

        // Create the Efek, which fails.

        restEfekMockMvc.perform(post("/api/efeks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(efek)))
            .andExpect(status().isBadRequest());

        List<Efek> efekList = efekRepository.findAll();
        assertThat(efekList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTipeEfekIsRequired() throws Exception {
        int databaseSizeBeforeTest = efekRepository.findAll().size();
        // set the field null
        efek.setTipeEfek(null);

        // Create the Efek, which fails.

        restEfekMockMvc.perform(post("/api/efeks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(efek)))
            .andExpect(status().isBadRequest());

        List<Efek> efekList = efekRepository.findAll();
        assertThat(efekList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLastHairCutIsRequired() throws Exception {
        int databaseSizeBeforeTest = efekRepository.findAll().size();
        // set the field null
        efek.setLastHairCut(null);

        // Create the Efek, which fails.

        restEfekMockMvc.perform(post("/api/efeks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(efek)))
            .andExpect(status().isBadRequest());

        List<Efek> efekList = efekRepository.findAll();
        assertThat(efekList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEfeks() throws Exception {
        // Initialize the database
        efekRepository.saveAndFlush(efek);

        // Get all the efekList
        restEfekMockMvc.perform(get("/api/efeks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(efek.getId().intValue())))
            .andExpect(jsonPath("$.[*].kodeEfek").value(hasItem(DEFAULT_KODE_EFEK.toString())))
            .andExpect(jsonPath("$.[*].namaEfek").value(hasItem(DEFAULT_NAMA_EFEK.toString())))
            .andExpect(jsonPath("$.[*].tipeEfek").value(hasItem(DEFAULT_TIPE_EFEK.toString())))
            .andExpect(jsonPath("$.[*].lastClosingPrice").value(hasItem(DEFAULT_LAST_CLOSING_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].lastClosingDate").value(hasItem(DEFAULT_LAST_CLOSING_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastHairCut").value(hasItem(DEFAULT_LAST_HAIR_CUT.doubleValue())))
            .andExpect(jsonPath("$.[*].lastHairCutDate").value(hasItem(DEFAULT_LAST_HAIR_CUT_DATE.toString())))
            .andExpect(jsonPath("$.[*].statusGadai").value(hasItem(DEFAULT_STATUS_GADAI.booleanValue())))
            .andExpect(jsonPath("$.[*].jlhEfekBeredar").value(hasItem(DEFAULT_JLH_EFEK_BEREDAR.intValue())))
            .andExpect(jsonPath("$.[*].bmpk").value(hasItem(DEFAULT_BMPK.doubleValue())))
            .andExpect(jsonPath("$.[*].bondRating").value(hasItem(DEFAULT_BOND_RATING.toString())))
            .andExpect(jsonPath("$.[*].bondMaturityDate").value(hasItem(DEFAULT_BOND_MATURITY_DATE.toString())))
            .andExpect(jsonPath("$.[*].satuan").value(hasItem(DEFAULT_SATUAN.intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getEfek() throws Exception {
        // Initialize the database
        efekRepository.saveAndFlush(efek);

        // Get the efek
        restEfekMockMvc.perform(get("/api/efeks/{id}", efek.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(efek.getId().intValue()))
            .andExpect(jsonPath("$.kodeEfek").value(DEFAULT_KODE_EFEK.toString()))
            .andExpect(jsonPath("$.namaEfek").value(DEFAULT_NAMA_EFEK.toString()))
            .andExpect(jsonPath("$.tipeEfek").value(DEFAULT_TIPE_EFEK.toString()))
            .andExpect(jsonPath("$.lastClosingPrice").value(DEFAULT_LAST_CLOSING_PRICE.doubleValue()))
            .andExpect(jsonPath("$.lastClosingDate").value(DEFAULT_LAST_CLOSING_DATE.toString()))
            .andExpect(jsonPath("$.lastHairCut").value(DEFAULT_LAST_HAIR_CUT.doubleValue()))
            .andExpect(jsonPath("$.lastHairCutDate").value(DEFAULT_LAST_HAIR_CUT_DATE.toString()))
            .andExpect(jsonPath("$.statusGadai").value(DEFAULT_STATUS_GADAI.booleanValue()))
            .andExpect(jsonPath("$.jlhEfekBeredar").value(DEFAULT_JLH_EFEK_BEREDAR.intValue()))
            .andExpect(jsonPath("$.bmpk").value(DEFAULT_BMPK.doubleValue()))
            .andExpect(jsonPath("$.bondRating").value(DEFAULT_BOND_RATING.toString()))
            .andExpect(jsonPath("$.bondMaturityDate").value(DEFAULT_BOND_MATURITY_DATE.toString()))
            .andExpect(jsonPath("$.satuan").value(DEFAULT_SATUAN.intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEfek() throws Exception {
        // Get the efek
        restEfekMockMvc.perform(get("/api/efeks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEfek() throws Exception {
        // Initialize the database
        efekRepository.saveAndFlush(efek);

        int databaseSizeBeforeUpdate = efekRepository.findAll().size();

        // Update the efek
        Efek updatedEfek = efekRepository.findById(efek.getId()).get();
        // Disconnect from session so that the updates on updatedEfek are not directly saved in db
        em.detach(updatedEfek);
        updatedEfek
            .kodeEfek(UPDATED_KODE_EFEK)
            .namaEfek(UPDATED_NAMA_EFEK)
            .tipeEfek(UPDATED_TIPE_EFEK)
            .lastClosingPrice(UPDATED_LAST_CLOSING_PRICE)
            .lastClosingDate(UPDATED_LAST_CLOSING_DATE)
            .lastHairCut(UPDATED_LAST_HAIR_CUT)
            .lastHairCutDate(UPDATED_LAST_HAIR_CUT_DATE)
            .statusGadai(UPDATED_STATUS_GADAI)
            .jlhEfekBeredar(UPDATED_JLH_EFEK_BEREDAR)
            .bmpk(UPDATED_BMPK)
            .bondRating(UPDATED_BOND_RATING)
            .bondMaturityDate(UPDATED_BOND_MATURITY_DATE)
            .satuan(UPDATED_SATUAN)
            .status(UPDATED_STATUS);

        restEfekMockMvc.perform(put("/api/efeks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedEfek)))
            .andExpect(status().isOk());

        // Validate the Efek in the database
        List<Efek> efekList = efekRepository.findAll();
        assertThat(efekList).hasSize(databaseSizeBeforeUpdate);
        Efek testEfek = efekList.get(efekList.size() - 1);
        assertThat(testEfek.getKodeEfek()).isEqualTo(UPDATED_KODE_EFEK);
        assertThat(testEfek.getNamaEfek()).isEqualTo(UPDATED_NAMA_EFEK);
        assertThat(testEfek.getTipeEfek()).isEqualTo(UPDATED_TIPE_EFEK);
        assertThat(testEfek.getLastClosingPrice()).isEqualTo(UPDATED_LAST_CLOSING_PRICE);
        assertThat(testEfek.getLastClosingDate()).isEqualTo(UPDATED_LAST_CLOSING_DATE);
        assertThat(testEfek.getLastHairCut()).isEqualTo(UPDATED_LAST_HAIR_CUT);
        assertThat(testEfek.getLastHairCutDate()).isEqualTo(UPDATED_LAST_HAIR_CUT_DATE);
        assertThat(testEfek.isStatusGadai()).isEqualTo(UPDATED_STATUS_GADAI);
        assertThat(testEfek.getJlhEfekBeredar()).isEqualTo(UPDATED_JLH_EFEK_BEREDAR);
        assertThat(testEfek.getBmpk()).isEqualTo(UPDATED_BMPK);
        assertThat(testEfek.getBondRating()).isEqualTo(UPDATED_BOND_RATING);
        assertThat(testEfek.getBondMaturityDate()).isEqualTo(UPDATED_BOND_MATURITY_DATE);
        assertThat(testEfek.getSatuan()).isEqualTo(UPDATED_SATUAN);
        assertThat(testEfek.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingEfek() throws Exception {
        int databaseSizeBeforeUpdate = efekRepository.findAll().size();

        // Create the Efek

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEfekMockMvc.perform(put("/api/efeks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(efek)))
            .andExpect(status().isBadRequest());

        // Validate the Efek in the database
        List<Efek> efekList = efekRepository.findAll();
        assertThat(efekList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEfek() throws Exception {
        // Initialize the database
        efekRepository.saveAndFlush(efek);

        int databaseSizeBeforeDelete = efekRepository.findAll().size();

        // Delete the efek
        restEfekMockMvc.perform(delete("/api/efeks/{id}", efek.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Efek> efekList = efekRepository.findAll();
        assertThat(efekList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Efek.class);
        Efek efek1 = new Efek();
        efek1.setId(1L);
        Efek efek2 = new Efek();
        efek2.setId(efek1.getId());
        assertThat(efek1).isEqualTo(efek2);
        efek2.setId(2L);
        assertThat(efek1).isNotEqualTo(efek2);
        efek1.setId(null);
        assertThat(efek1).isNotEqualTo(efek2);
    }
}
