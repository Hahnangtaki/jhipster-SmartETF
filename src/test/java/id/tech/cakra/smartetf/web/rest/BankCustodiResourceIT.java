package id.tech.cakra.smartetf.web.rest;

import id.tech.cakra.smartetf.SmartEtfApp;
import id.tech.cakra.smartetf.domain.BankCustodi;
import id.tech.cakra.smartetf.repository.BankCustodiRepository;
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
 * Integration tests for the {@link BankCustodiResource} REST controller.
 */
@SpringBootTest(classes = SmartEtfApp.class)
public class BankCustodiResourceIT {

    private static final String DEFAULT_KODE_CUSTODI = "AAAAAAAAAA";
    private static final String UPDATED_KODE_CUSTODI = "BBBBBBBBBB";

    private static final String DEFAULT_NAMA_CUSTODI = "AAAAAAAAAA";
    private static final String UPDATED_NAMA_CUSTODI = "BBBBBBBBBB";

    @Autowired
    private BankCustodiRepository bankCustodiRepository;

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

    private MockMvc restBankCustodiMockMvc;

    private BankCustodi bankCustodi;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BankCustodiResource bankCustodiResource = new BankCustodiResource(bankCustodiRepository);
        this.restBankCustodiMockMvc = MockMvcBuilders.standaloneSetup(bankCustodiResource)
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
    public static BankCustodi createEntity(EntityManager em) {
        BankCustodi bankCustodi = new BankCustodi()
            .kodeCustodi(DEFAULT_KODE_CUSTODI)
            .namaCustodi(DEFAULT_NAMA_CUSTODI);
        return bankCustodi;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BankCustodi createUpdatedEntity(EntityManager em) {
        BankCustodi bankCustodi = new BankCustodi()
            .kodeCustodi(UPDATED_KODE_CUSTODI)
            .namaCustodi(UPDATED_NAMA_CUSTODI);
        return bankCustodi;
    }

    @BeforeEach
    public void initTest() {
        bankCustodi = createEntity(em);
    }

    @Test
    @Transactional
    public void createBankCustodi() throws Exception {
        int databaseSizeBeforeCreate = bankCustodiRepository.findAll().size();

        // Create the BankCustodi
        restBankCustodiMockMvc.perform(post("/api/bank-custodis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bankCustodi)))
            .andExpect(status().isCreated());

        // Validate the BankCustodi in the database
        List<BankCustodi> bankCustodiList = bankCustodiRepository.findAll();
        assertThat(bankCustodiList).hasSize(databaseSizeBeforeCreate + 1);
        BankCustodi testBankCustodi = bankCustodiList.get(bankCustodiList.size() - 1);
        assertThat(testBankCustodi.getKodeCustodi()).isEqualTo(DEFAULT_KODE_CUSTODI);
        assertThat(testBankCustodi.getNamaCustodi()).isEqualTo(DEFAULT_NAMA_CUSTODI);
    }

    @Test
    @Transactional
    public void createBankCustodiWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bankCustodiRepository.findAll().size();

        // Create the BankCustodi with an existing ID
        bankCustodi.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBankCustodiMockMvc.perform(post("/api/bank-custodis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bankCustodi)))
            .andExpect(status().isBadRequest());

        // Validate the BankCustodi in the database
        List<BankCustodi> bankCustodiList = bankCustodiRepository.findAll();
        assertThat(bankCustodiList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkKodeCustodiIsRequired() throws Exception {
        int databaseSizeBeforeTest = bankCustodiRepository.findAll().size();
        // set the field null
        bankCustodi.setKodeCustodi(null);

        // Create the BankCustodi, which fails.

        restBankCustodiMockMvc.perform(post("/api/bank-custodis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bankCustodi)))
            .andExpect(status().isBadRequest());

        List<BankCustodi> bankCustodiList = bankCustodiRepository.findAll();
        assertThat(bankCustodiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNamaCustodiIsRequired() throws Exception {
        int databaseSizeBeforeTest = bankCustodiRepository.findAll().size();
        // set the field null
        bankCustodi.setNamaCustodi(null);

        // Create the BankCustodi, which fails.

        restBankCustodiMockMvc.perform(post("/api/bank-custodis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bankCustodi)))
            .andExpect(status().isBadRequest());

        List<BankCustodi> bankCustodiList = bankCustodiRepository.findAll();
        assertThat(bankCustodiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBankCustodis() throws Exception {
        // Initialize the database
        bankCustodiRepository.saveAndFlush(bankCustodi);

        // Get all the bankCustodiList
        restBankCustodiMockMvc.perform(get("/api/bank-custodis?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bankCustodi.getId().intValue())))
            .andExpect(jsonPath("$.[*].kodeCustodi").value(hasItem(DEFAULT_KODE_CUSTODI.toString())))
            .andExpect(jsonPath("$.[*].namaCustodi").value(hasItem(DEFAULT_NAMA_CUSTODI.toString())));
    }
    
    @Test
    @Transactional
    public void getBankCustodi() throws Exception {
        // Initialize the database
        bankCustodiRepository.saveAndFlush(bankCustodi);

        // Get the bankCustodi
        restBankCustodiMockMvc.perform(get("/api/bank-custodis/{id}", bankCustodi.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(bankCustodi.getId().intValue()))
            .andExpect(jsonPath("$.kodeCustodi").value(DEFAULT_KODE_CUSTODI.toString()))
            .andExpect(jsonPath("$.namaCustodi").value(DEFAULT_NAMA_CUSTODI.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBankCustodi() throws Exception {
        // Get the bankCustodi
        restBankCustodiMockMvc.perform(get("/api/bank-custodis/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBankCustodi() throws Exception {
        // Initialize the database
        bankCustodiRepository.saveAndFlush(bankCustodi);

        int databaseSizeBeforeUpdate = bankCustodiRepository.findAll().size();

        // Update the bankCustodi
        BankCustodi updatedBankCustodi = bankCustodiRepository.findById(bankCustodi.getId()).get();
        // Disconnect from session so that the updates on updatedBankCustodi are not directly saved in db
        em.detach(updatedBankCustodi);
        updatedBankCustodi
            .kodeCustodi(UPDATED_KODE_CUSTODI)
            .namaCustodi(UPDATED_NAMA_CUSTODI);

        restBankCustodiMockMvc.perform(put("/api/bank-custodis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedBankCustodi)))
            .andExpect(status().isOk());

        // Validate the BankCustodi in the database
        List<BankCustodi> bankCustodiList = bankCustodiRepository.findAll();
        assertThat(bankCustodiList).hasSize(databaseSizeBeforeUpdate);
        BankCustodi testBankCustodi = bankCustodiList.get(bankCustodiList.size() - 1);
        assertThat(testBankCustodi.getKodeCustodi()).isEqualTo(UPDATED_KODE_CUSTODI);
        assertThat(testBankCustodi.getNamaCustodi()).isEqualTo(UPDATED_NAMA_CUSTODI);
    }

    @Test
    @Transactional
    public void updateNonExistingBankCustodi() throws Exception {
        int databaseSizeBeforeUpdate = bankCustodiRepository.findAll().size();

        // Create the BankCustodi

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBankCustodiMockMvc.perform(put("/api/bank-custodis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bankCustodi)))
            .andExpect(status().isBadRequest());

        // Validate the BankCustodi in the database
        List<BankCustodi> bankCustodiList = bankCustodiRepository.findAll();
        assertThat(bankCustodiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBankCustodi() throws Exception {
        // Initialize the database
        bankCustodiRepository.saveAndFlush(bankCustodi);

        int databaseSizeBeforeDelete = bankCustodiRepository.findAll().size();

        // Delete the bankCustodi
        restBankCustodiMockMvc.perform(delete("/api/bank-custodis/{id}", bankCustodi.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BankCustodi> bankCustodiList = bankCustodiRepository.findAll();
        assertThat(bankCustodiList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BankCustodi.class);
        BankCustodi bankCustodi1 = new BankCustodi();
        bankCustodi1.setId(1L);
        BankCustodi bankCustodi2 = new BankCustodi();
        bankCustodi2.setId(bankCustodi1.getId());
        assertThat(bankCustodi1).isEqualTo(bankCustodi2);
        bankCustodi2.setId(2L);
        assertThat(bankCustodi1).isNotEqualTo(bankCustodi2);
        bankCustodi1.setId(null);
        assertThat(bankCustodi1).isNotEqualTo(bankCustodi2);
    }
}
