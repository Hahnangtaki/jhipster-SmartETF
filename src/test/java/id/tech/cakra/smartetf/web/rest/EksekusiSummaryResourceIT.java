package id.tech.cakra.smartetf.web.rest;

import id.tech.cakra.smartetf.SmartEtfApp;
import id.tech.cakra.smartetf.domain.EksekusiSummary;
import id.tech.cakra.smartetf.repository.EksekusiSummaryRepository;
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
 * Integration tests for the {@link EksekusiSummaryResource} REST controller.
 */
@SpringBootTest(classes = SmartEtfApp.class)
public class EksekusiSummaryResourceIT {

    private static final String DEFAULT_NO_EKSEKUSI = "AAAAAAAAAA";
    private static final String UPDATED_NO_EKSEKUSI = "BBBBBBBBBB";

    private static final String DEFAULT_KODE_EFEK = "AAAAAAAAAA";
    private static final String UPDATED_KODE_EFEK = "BBBBBBBBBB";

    private static final Double DEFAULT_HARGA_JUAL = 0D;
    private static final Double UPDATED_HARGA_JUAL = 1D;
    private static final Double SMALLER_HARGA_JUAL = 0D - 1D;

    private static final Long DEFAULT_QUANTITY = 0L;
    private static final Long UPDATED_QUANTITY = 1L;
    private static final Long SMALLER_QUANTITY = 0L - 1L;

    private static final Long DEFAULT_DONE_QTY = 0L;
    private static final Long UPDATED_DONE_QTY = 1L;
    private static final Long SMALLER_DONE_QTY = 0L - 1L;

    private static final Double DEFAULT_AMOUNT = 0D;
    private static final Double UPDATED_AMOUNT = 1D;
    private static final Double SMALLER_AMOUNT = 0D - 1D;

    private static final Double DEFAULT_BIAYA = 0D;
    private static final Double UPDATED_BIAYA = 1D;
    private static final Double SMALLER_BIAYA = 0D - 1D;

    private static final Double DEFAULT_NET_AMOUNT = 0D;
    private static final Double UPDATED_NET_AMOUNT = 1D;
    private static final Double SMALLER_NET_AMOUNT = 0D - 1D;

    private static final Long DEFAULT_ALOKASI_QTY = 0L;
    private static final Long UPDATED_ALOKASI_QTY = 1L;
    private static final Long SMALLER_ALOKASI_QTY = 0L - 1L;

    private static final Double DEFAULT_ALOKSI_AMOUNT = 0D;
    private static final Double UPDATED_ALOKSI_AMOUNT = 1D;
    private static final Double SMALLER_ALOKSI_AMOUNT = 0D - 1D;

    @Autowired
    private EksekusiSummaryRepository eksekusiSummaryRepository;

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

    private MockMvc restEksekusiSummaryMockMvc;

    private EksekusiSummary eksekusiSummary;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EksekusiSummaryResource eksekusiSummaryResource = new EksekusiSummaryResource(eksekusiSummaryRepository);
        this.restEksekusiSummaryMockMvc = MockMvcBuilders.standaloneSetup(eksekusiSummaryResource)
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
    public static EksekusiSummary createEntity(EntityManager em) {
        EksekusiSummary eksekusiSummary = new EksekusiSummary()
            .noEksekusi(DEFAULT_NO_EKSEKUSI)
            .kodeEfek(DEFAULT_KODE_EFEK)
            .hargaJual(DEFAULT_HARGA_JUAL)
            .quantity(DEFAULT_QUANTITY)
            .doneQty(DEFAULT_DONE_QTY)
            .amount(DEFAULT_AMOUNT)
            .biaya(DEFAULT_BIAYA)
            .netAmount(DEFAULT_NET_AMOUNT)
            .alokasiQty(DEFAULT_ALOKASI_QTY)
            .aloksiAmount(DEFAULT_ALOKSI_AMOUNT);
        return eksekusiSummary;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EksekusiSummary createUpdatedEntity(EntityManager em) {
        EksekusiSummary eksekusiSummary = new EksekusiSummary()
            .noEksekusi(UPDATED_NO_EKSEKUSI)
            .kodeEfek(UPDATED_KODE_EFEK)
            .hargaJual(UPDATED_HARGA_JUAL)
            .quantity(UPDATED_QUANTITY)
            .doneQty(UPDATED_DONE_QTY)
            .amount(UPDATED_AMOUNT)
            .biaya(UPDATED_BIAYA)
            .netAmount(UPDATED_NET_AMOUNT)
            .alokasiQty(UPDATED_ALOKASI_QTY)
            .aloksiAmount(UPDATED_ALOKSI_AMOUNT);
        return eksekusiSummary;
    }

    @BeforeEach
    public void initTest() {
        eksekusiSummary = createEntity(em);
    }

    @Test
    @Transactional
    public void createEksekusiSummary() throws Exception {
        int databaseSizeBeforeCreate = eksekusiSummaryRepository.findAll().size();

        // Create the EksekusiSummary
        restEksekusiSummaryMockMvc.perform(post("/api/eksekusi-summaries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eksekusiSummary)))
            .andExpect(status().isCreated());

        // Validate the EksekusiSummary in the database
        List<EksekusiSummary> eksekusiSummaryList = eksekusiSummaryRepository.findAll();
        assertThat(eksekusiSummaryList).hasSize(databaseSizeBeforeCreate + 1);
        EksekusiSummary testEksekusiSummary = eksekusiSummaryList.get(eksekusiSummaryList.size() - 1);
        assertThat(testEksekusiSummary.getNoEksekusi()).isEqualTo(DEFAULT_NO_EKSEKUSI);
        assertThat(testEksekusiSummary.getKodeEfek()).isEqualTo(DEFAULT_KODE_EFEK);
        assertThat(testEksekusiSummary.getHargaJual()).isEqualTo(DEFAULT_HARGA_JUAL);
        assertThat(testEksekusiSummary.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testEksekusiSummary.getDoneQty()).isEqualTo(DEFAULT_DONE_QTY);
        assertThat(testEksekusiSummary.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testEksekusiSummary.getBiaya()).isEqualTo(DEFAULT_BIAYA);
        assertThat(testEksekusiSummary.getNetAmount()).isEqualTo(DEFAULT_NET_AMOUNT);
        assertThat(testEksekusiSummary.getAlokasiQty()).isEqualTo(DEFAULT_ALOKASI_QTY);
        assertThat(testEksekusiSummary.getAloksiAmount()).isEqualTo(DEFAULT_ALOKSI_AMOUNT);
    }

    @Test
    @Transactional
    public void createEksekusiSummaryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = eksekusiSummaryRepository.findAll().size();

        // Create the EksekusiSummary with an existing ID
        eksekusiSummary.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEksekusiSummaryMockMvc.perform(post("/api/eksekusi-summaries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eksekusiSummary)))
            .andExpect(status().isBadRequest());

        // Validate the EksekusiSummary in the database
        List<EksekusiSummary> eksekusiSummaryList = eksekusiSummaryRepository.findAll();
        assertThat(eksekusiSummaryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNoEksekusiIsRequired() throws Exception {
        int databaseSizeBeforeTest = eksekusiSummaryRepository.findAll().size();
        // set the field null
        eksekusiSummary.setNoEksekusi(null);

        // Create the EksekusiSummary, which fails.

        restEksekusiSummaryMockMvc.perform(post("/api/eksekusi-summaries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eksekusiSummary)))
            .andExpect(status().isBadRequest());

        List<EksekusiSummary> eksekusiSummaryList = eksekusiSummaryRepository.findAll();
        assertThat(eksekusiSummaryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkKodeEfekIsRequired() throws Exception {
        int databaseSizeBeforeTest = eksekusiSummaryRepository.findAll().size();
        // set the field null
        eksekusiSummary.setKodeEfek(null);

        // Create the EksekusiSummary, which fails.

        restEksekusiSummaryMockMvc.perform(post("/api/eksekusi-summaries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eksekusiSummary)))
            .andExpect(status().isBadRequest());

        List<EksekusiSummary> eksekusiSummaryList = eksekusiSummaryRepository.findAll();
        assertThat(eksekusiSummaryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEksekusiSummaries() throws Exception {
        // Initialize the database
        eksekusiSummaryRepository.saveAndFlush(eksekusiSummary);

        // Get all the eksekusiSummaryList
        restEksekusiSummaryMockMvc.perform(get("/api/eksekusi-summaries?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(eksekusiSummary.getId().intValue())))
            .andExpect(jsonPath("$.[*].noEksekusi").value(hasItem(DEFAULT_NO_EKSEKUSI.toString())))
            .andExpect(jsonPath("$.[*].kodeEfek").value(hasItem(DEFAULT_KODE_EFEK.toString())))
            .andExpect(jsonPath("$.[*].hargaJual").value(hasItem(DEFAULT_HARGA_JUAL.doubleValue())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY.intValue())))
            .andExpect(jsonPath("$.[*].doneQty").value(hasItem(DEFAULT_DONE_QTY.intValue())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].biaya").value(hasItem(DEFAULT_BIAYA.doubleValue())))
            .andExpect(jsonPath("$.[*].netAmount").value(hasItem(DEFAULT_NET_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].alokasiQty").value(hasItem(DEFAULT_ALOKASI_QTY.intValue())))
            .andExpect(jsonPath("$.[*].aloksiAmount").value(hasItem(DEFAULT_ALOKSI_AMOUNT.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getEksekusiSummary() throws Exception {
        // Initialize the database
        eksekusiSummaryRepository.saveAndFlush(eksekusiSummary);

        // Get the eksekusiSummary
        restEksekusiSummaryMockMvc.perform(get("/api/eksekusi-summaries/{id}", eksekusiSummary.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(eksekusiSummary.getId().intValue()))
            .andExpect(jsonPath("$.noEksekusi").value(DEFAULT_NO_EKSEKUSI.toString()))
            .andExpect(jsonPath("$.kodeEfek").value(DEFAULT_KODE_EFEK.toString()))
            .andExpect(jsonPath("$.hargaJual").value(DEFAULT_HARGA_JUAL.doubleValue()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY.intValue()))
            .andExpect(jsonPath("$.doneQty").value(DEFAULT_DONE_QTY.intValue()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.biaya").value(DEFAULT_BIAYA.doubleValue()))
            .andExpect(jsonPath("$.netAmount").value(DEFAULT_NET_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.alokasiQty").value(DEFAULT_ALOKASI_QTY.intValue()))
            .andExpect(jsonPath("$.aloksiAmount").value(DEFAULT_ALOKSI_AMOUNT.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingEksekusiSummary() throws Exception {
        // Get the eksekusiSummary
        restEksekusiSummaryMockMvc.perform(get("/api/eksekusi-summaries/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEksekusiSummary() throws Exception {
        // Initialize the database
        eksekusiSummaryRepository.saveAndFlush(eksekusiSummary);

        int databaseSizeBeforeUpdate = eksekusiSummaryRepository.findAll().size();

        // Update the eksekusiSummary
        EksekusiSummary updatedEksekusiSummary = eksekusiSummaryRepository.findById(eksekusiSummary.getId()).get();
        // Disconnect from session so that the updates on updatedEksekusiSummary are not directly saved in db
        em.detach(updatedEksekusiSummary);
        updatedEksekusiSummary
            .noEksekusi(UPDATED_NO_EKSEKUSI)
            .kodeEfek(UPDATED_KODE_EFEK)
            .hargaJual(UPDATED_HARGA_JUAL)
            .quantity(UPDATED_QUANTITY)
            .doneQty(UPDATED_DONE_QTY)
            .amount(UPDATED_AMOUNT)
            .biaya(UPDATED_BIAYA)
            .netAmount(UPDATED_NET_AMOUNT)
            .alokasiQty(UPDATED_ALOKASI_QTY)
            .aloksiAmount(UPDATED_ALOKSI_AMOUNT);

        restEksekusiSummaryMockMvc.perform(put("/api/eksekusi-summaries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedEksekusiSummary)))
            .andExpect(status().isOk());

        // Validate the EksekusiSummary in the database
        List<EksekusiSummary> eksekusiSummaryList = eksekusiSummaryRepository.findAll();
        assertThat(eksekusiSummaryList).hasSize(databaseSizeBeforeUpdate);
        EksekusiSummary testEksekusiSummary = eksekusiSummaryList.get(eksekusiSummaryList.size() - 1);
        assertThat(testEksekusiSummary.getNoEksekusi()).isEqualTo(UPDATED_NO_EKSEKUSI);
        assertThat(testEksekusiSummary.getKodeEfek()).isEqualTo(UPDATED_KODE_EFEK);
        assertThat(testEksekusiSummary.getHargaJual()).isEqualTo(UPDATED_HARGA_JUAL);
        assertThat(testEksekusiSummary.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testEksekusiSummary.getDoneQty()).isEqualTo(UPDATED_DONE_QTY);
        assertThat(testEksekusiSummary.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testEksekusiSummary.getBiaya()).isEqualTo(UPDATED_BIAYA);
        assertThat(testEksekusiSummary.getNetAmount()).isEqualTo(UPDATED_NET_AMOUNT);
        assertThat(testEksekusiSummary.getAlokasiQty()).isEqualTo(UPDATED_ALOKASI_QTY);
        assertThat(testEksekusiSummary.getAloksiAmount()).isEqualTo(UPDATED_ALOKSI_AMOUNT);
    }

    @Test
    @Transactional
    public void updateNonExistingEksekusiSummary() throws Exception {
        int databaseSizeBeforeUpdate = eksekusiSummaryRepository.findAll().size();

        // Create the EksekusiSummary

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEksekusiSummaryMockMvc.perform(put("/api/eksekusi-summaries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eksekusiSummary)))
            .andExpect(status().isBadRequest());

        // Validate the EksekusiSummary in the database
        List<EksekusiSummary> eksekusiSummaryList = eksekusiSummaryRepository.findAll();
        assertThat(eksekusiSummaryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEksekusiSummary() throws Exception {
        // Initialize the database
        eksekusiSummaryRepository.saveAndFlush(eksekusiSummary);

        int databaseSizeBeforeDelete = eksekusiSummaryRepository.findAll().size();

        // Delete the eksekusiSummary
        restEksekusiSummaryMockMvc.perform(delete("/api/eksekusi-summaries/{id}", eksekusiSummary.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EksekusiSummary> eksekusiSummaryList = eksekusiSummaryRepository.findAll();
        assertThat(eksekusiSummaryList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EksekusiSummary.class);
        EksekusiSummary eksekusiSummary1 = new EksekusiSummary();
        eksekusiSummary1.setId(1L);
        EksekusiSummary eksekusiSummary2 = new EksekusiSummary();
        eksekusiSummary2.setId(eksekusiSummary1.getId());
        assertThat(eksekusiSummary1).isEqualTo(eksekusiSummary2);
        eksekusiSummary2.setId(2L);
        assertThat(eksekusiSummary1).isNotEqualTo(eksekusiSummary2);
        eksekusiSummary1.setId(null);
        assertThat(eksekusiSummary1).isNotEqualTo(eksekusiSummary2);
    }
}
