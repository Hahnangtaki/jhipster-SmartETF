package id.tech.cakra.smartetf.web.rest;

import id.tech.cakra.smartetf.SmartEtfApp;
import id.tech.cakra.smartetf.domain.EksekusiDtl;
import id.tech.cakra.smartetf.repository.EksekusiDtlRepository;
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
 * Integration tests for the {@link EksekusiDtlResource} REST controller.
 */
@SpringBootTest(classes = SmartEtfApp.class)
public class EksekusiDtlResourceIT {

    private static final String DEFAULT_NO_EKSEKUSI = "AAAAAAAAAA";
    private static final String UPDATED_NO_EKSEKUSI = "BBBBBBBBBB";

    private static final String DEFAULT_NOMOR_KONTRAK = "AAAAAAAAAA";
    private static final String UPDATED_NOMOR_KONTRAK = "BBBBBBBBBB";

    private static final String DEFAULT_KODE_EFEK = "AAAAAAAAAA";
    private static final String UPDATED_KODE_EFEK = "BBBBBBBBBB";

    private static final Long DEFAULT_QUANTITY = 0L;
    private static final Long UPDATED_QUANTITY = 1L;
    private static final Long SMALLER_QUANTITY = 0L - 1L;

    private static final Long DEFAULT_DONE_QTY = 0L;
    private static final Long UPDATED_DONE_QTY = 1L;
    private static final Long SMALLER_DONE_QTY = 0L - 1L;

    private static final Double DEFAULT_DONE_AMOUNT = 0D;
    private static final Double UPDATED_DONE_AMOUNT = 1D;
    private static final Double SMALLER_DONE_AMOUNT = 0D - 1D;

    @Autowired
    private EksekusiDtlRepository eksekusiDtlRepository;

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

    private MockMvc restEksekusiDtlMockMvc;

    private EksekusiDtl eksekusiDtl;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EksekusiDtlResource eksekusiDtlResource = new EksekusiDtlResource(eksekusiDtlRepository);
        this.restEksekusiDtlMockMvc = MockMvcBuilders.standaloneSetup(eksekusiDtlResource)
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
    public static EksekusiDtl createEntity(EntityManager em) {
        EksekusiDtl eksekusiDtl = new EksekusiDtl()
            .noEksekusi(DEFAULT_NO_EKSEKUSI)
            .nomorKontrak(DEFAULT_NOMOR_KONTRAK)
            .kodeEfek(DEFAULT_KODE_EFEK)
            .quantity(DEFAULT_QUANTITY)
            .doneQty(DEFAULT_DONE_QTY)
            .doneAmount(DEFAULT_DONE_AMOUNT);
        return eksekusiDtl;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EksekusiDtl createUpdatedEntity(EntityManager em) {
        EksekusiDtl eksekusiDtl = new EksekusiDtl()
            .noEksekusi(UPDATED_NO_EKSEKUSI)
            .nomorKontrak(UPDATED_NOMOR_KONTRAK)
            .kodeEfek(UPDATED_KODE_EFEK)
            .quantity(UPDATED_QUANTITY)
            .doneQty(UPDATED_DONE_QTY)
            .doneAmount(UPDATED_DONE_AMOUNT);
        return eksekusiDtl;
    }

    @BeforeEach
    public void initTest() {
        eksekusiDtl = createEntity(em);
    }

    @Test
    @Transactional
    public void createEksekusiDtl() throws Exception {
        int databaseSizeBeforeCreate = eksekusiDtlRepository.findAll().size();

        // Create the EksekusiDtl
        restEksekusiDtlMockMvc.perform(post("/api/eksekusi-dtls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eksekusiDtl)))
            .andExpect(status().isCreated());

        // Validate the EksekusiDtl in the database
        List<EksekusiDtl> eksekusiDtlList = eksekusiDtlRepository.findAll();
        assertThat(eksekusiDtlList).hasSize(databaseSizeBeforeCreate + 1);
        EksekusiDtl testEksekusiDtl = eksekusiDtlList.get(eksekusiDtlList.size() - 1);
        assertThat(testEksekusiDtl.getNoEksekusi()).isEqualTo(DEFAULT_NO_EKSEKUSI);
        assertThat(testEksekusiDtl.getNomorKontrak()).isEqualTo(DEFAULT_NOMOR_KONTRAK);
        assertThat(testEksekusiDtl.getKodeEfek()).isEqualTo(DEFAULT_KODE_EFEK);
        assertThat(testEksekusiDtl.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testEksekusiDtl.getDoneQty()).isEqualTo(DEFAULT_DONE_QTY);
        assertThat(testEksekusiDtl.getDoneAmount()).isEqualTo(DEFAULT_DONE_AMOUNT);
    }

    @Test
    @Transactional
    public void createEksekusiDtlWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = eksekusiDtlRepository.findAll().size();

        // Create the EksekusiDtl with an existing ID
        eksekusiDtl.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEksekusiDtlMockMvc.perform(post("/api/eksekusi-dtls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eksekusiDtl)))
            .andExpect(status().isBadRequest());

        // Validate the EksekusiDtl in the database
        List<EksekusiDtl> eksekusiDtlList = eksekusiDtlRepository.findAll();
        assertThat(eksekusiDtlList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNoEksekusiIsRequired() throws Exception {
        int databaseSizeBeforeTest = eksekusiDtlRepository.findAll().size();
        // set the field null
        eksekusiDtl.setNoEksekusi(null);

        // Create the EksekusiDtl, which fails.

        restEksekusiDtlMockMvc.perform(post("/api/eksekusi-dtls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eksekusiDtl)))
            .andExpect(status().isBadRequest());

        List<EksekusiDtl> eksekusiDtlList = eksekusiDtlRepository.findAll();
        assertThat(eksekusiDtlList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNomorKontrakIsRequired() throws Exception {
        int databaseSizeBeforeTest = eksekusiDtlRepository.findAll().size();
        // set the field null
        eksekusiDtl.setNomorKontrak(null);

        // Create the EksekusiDtl, which fails.

        restEksekusiDtlMockMvc.perform(post("/api/eksekusi-dtls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eksekusiDtl)))
            .andExpect(status().isBadRequest());

        List<EksekusiDtl> eksekusiDtlList = eksekusiDtlRepository.findAll();
        assertThat(eksekusiDtlList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkKodeEfekIsRequired() throws Exception {
        int databaseSizeBeforeTest = eksekusiDtlRepository.findAll().size();
        // set the field null
        eksekusiDtl.setKodeEfek(null);

        // Create the EksekusiDtl, which fails.

        restEksekusiDtlMockMvc.perform(post("/api/eksekusi-dtls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eksekusiDtl)))
            .andExpect(status().isBadRequest());

        List<EksekusiDtl> eksekusiDtlList = eksekusiDtlRepository.findAll();
        assertThat(eksekusiDtlList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEksekusiDtls() throws Exception {
        // Initialize the database
        eksekusiDtlRepository.saveAndFlush(eksekusiDtl);

        // Get all the eksekusiDtlList
        restEksekusiDtlMockMvc.perform(get("/api/eksekusi-dtls?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(eksekusiDtl.getId().intValue())))
            .andExpect(jsonPath("$.[*].noEksekusi").value(hasItem(DEFAULT_NO_EKSEKUSI.toString())))
            .andExpect(jsonPath("$.[*].nomorKontrak").value(hasItem(DEFAULT_NOMOR_KONTRAK.toString())))
            .andExpect(jsonPath("$.[*].kodeEfek").value(hasItem(DEFAULT_KODE_EFEK.toString())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY.intValue())))
            .andExpect(jsonPath("$.[*].doneQty").value(hasItem(DEFAULT_DONE_QTY.intValue())))
            .andExpect(jsonPath("$.[*].doneAmount").value(hasItem(DEFAULT_DONE_AMOUNT.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getEksekusiDtl() throws Exception {
        // Initialize the database
        eksekusiDtlRepository.saveAndFlush(eksekusiDtl);

        // Get the eksekusiDtl
        restEksekusiDtlMockMvc.perform(get("/api/eksekusi-dtls/{id}", eksekusiDtl.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(eksekusiDtl.getId().intValue()))
            .andExpect(jsonPath("$.noEksekusi").value(DEFAULT_NO_EKSEKUSI.toString()))
            .andExpect(jsonPath("$.nomorKontrak").value(DEFAULT_NOMOR_KONTRAK.toString()))
            .andExpect(jsonPath("$.kodeEfek").value(DEFAULT_KODE_EFEK.toString()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY.intValue()))
            .andExpect(jsonPath("$.doneQty").value(DEFAULT_DONE_QTY.intValue()))
            .andExpect(jsonPath("$.doneAmount").value(DEFAULT_DONE_AMOUNT.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingEksekusiDtl() throws Exception {
        // Get the eksekusiDtl
        restEksekusiDtlMockMvc.perform(get("/api/eksekusi-dtls/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEksekusiDtl() throws Exception {
        // Initialize the database
        eksekusiDtlRepository.saveAndFlush(eksekusiDtl);

        int databaseSizeBeforeUpdate = eksekusiDtlRepository.findAll().size();

        // Update the eksekusiDtl
        EksekusiDtl updatedEksekusiDtl = eksekusiDtlRepository.findById(eksekusiDtl.getId()).get();
        // Disconnect from session so that the updates on updatedEksekusiDtl are not directly saved in db
        em.detach(updatedEksekusiDtl);
        updatedEksekusiDtl
            .noEksekusi(UPDATED_NO_EKSEKUSI)
            .nomorKontrak(UPDATED_NOMOR_KONTRAK)
            .kodeEfek(UPDATED_KODE_EFEK)
            .quantity(UPDATED_QUANTITY)
            .doneQty(UPDATED_DONE_QTY)
            .doneAmount(UPDATED_DONE_AMOUNT);

        restEksekusiDtlMockMvc.perform(put("/api/eksekusi-dtls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedEksekusiDtl)))
            .andExpect(status().isOk());

        // Validate the EksekusiDtl in the database
        List<EksekusiDtl> eksekusiDtlList = eksekusiDtlRepository.findAll();
        assertThat(eksekusiDtlList).hasSize(databaseSizeBeforeUpdate);
        EksekusiDtl testEksekusiDtl = eksekusiDtlList.get(eksekusiDtlList.size() - 1);
        assertThat(testEksekusiDtl.getNoEksekusi()).isEqualTo(UPDATED_NO_EKSEKUSI);
        assertThat(testEksekusiDtl.getNomorKontrak()).isEqualTo(UPDATED_NOMOR_KONTRAK);
        assertThat(testEksekusiDtl.getKodeEfek()).isEqualTo(UPDATED_KODE_EFEK);
        assertThat(testEksekusiDtl.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testEksekusiDtl.getDoneQty()).isEqualTo(UPDATED_DONE_QTY);
        assertThat(testEksekusiDtl.getDoneAmount()).isEqualTo(UPDATED_DONE_AMOUNT);
    }

    @Test
    @Transactional
    public void updateNonExistingEksekusiDtl() throws Exception {
        int databaseSizeBeforeUpdate = eksekusiDtlRepository.findAll().size();

        // Create the EksekusiDtl

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEksekusiDtlMockMvc.perform(put("/api/eksekusi-dtls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eksekusiDtl)))
            .andExpect(status().isBadRequest());

        // Validate the EksekusiDtl in the database
        List<EksekusiDtl> eksekusiDtlList = eksekusiDtlRepository.findAll();
        assertThat(eksekusiDtlList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEksekusiDtl() throws Exception {
        // Initialize the database
        eksekusiDtlRepository.saveAndFlush(eksekusiDtl);

        int databaseSizeBeforeDelete = eksekusiDtlRepository.findAll().size();

        // Delete the eksekusiDtl
        restEksekusiDtlMockMvc.perform(delete("/api/eksekusi-dtls/{id}", eksekusiDtl.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EksekusiDtl> eksekusiDtlList = eksekusiDtlRepository.findAll();
        assertThat(eksekusiDtlList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EksekusiDtl.class);
        EksekusiDtl eksekusiDtl1 = new EksekusiDtl();
        eksekusiDtl1.setId(1L);
        EksekusiDtl eksekusiDtl2 = new EksekusiDtl();
        eksekusiDtl2.setId(eksekusiDtl1.getId());
        assertThat(eksekusiDtl1).isEqualTo(eksekusiDtl2);
        eksekusiDtl2.setId(2L);
        assertThat(eksekusiDtl1).isNotEqualTo(eksekusiDtl2);
        eksekusiDtl1.setId(null);
        assertThat(eksekusiDtl1).isNotEqualTo(eksekusiDtl2);
    }
}
