package id.tech.cakra.smartetf.web.rest;

import id.tech.cakra.smartetf.SmartEtfApp;
import id.tech.cakra.smartetf.domain.PengajuanGadaiEfekDtl;
import id.tech.cakra.smartetf.repository.PengajuanGadaiEfekDtlRepository;
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
 * Integration tests for the {@link PengajuanGadaiEfekDtlResource} REST controller.
 */
@SpringBootTest(classes = SmartEtfApp.class)
public class PengajuanGadaiEfekDtlResourceIT {

    private static final String DEFAULT_NO_PENGAJUAN_GADAI = "AAAAAAAAAA";
    private static final String UPDATED_NO_PENGAJUAN_GADAI = "BBBBBBBBBB";

    private static final String DEFAULT_KODE_EFEK = "AAAAAAAAAA";
    private static final String UPDATED_KODE_EFEK = "BBBBBBBBBB";

    private static final Long DEFAULT_QUANTITY = 1L;
    private static final Long UPDATED_QUANTITY = 2L;
    private static final Long SMALLER_QUANTITY = 1L - 1L;

    @Autowired
    private PengajuanGadaiEfekDtlRepository pengajuanGadaiEfekDtlRepository;

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

    private MockMvc restPengajuanGadaiEfekDtlMockMvc;

    private PengajuanGadaiEfekDtl pengajuanGadaiEfekDtl;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PengajuanGadaiEfekDtlResource pengajuanGadaiEfekDtlResource = new PengajuanGadaiEfekDtlResource(pengajuanGadaiEfekDtlRepository);
        this.restPengajuanGadaiEfekDtlMockMvc = MockMvcBuilders.standaloneSetup(pengajuanGadaiEfekDtlResource)
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
    public static PengajuanGadaiEfekDtl createEntity(EntityManager em) {
        PengajuanGadaiEfekDtl pengajuanGadaiEfekDtl = new PengajuanGadaiEfekDtl()
            .noPengajuanGadai(DEFAULT_NO_PENGAJUAN_GADAI)
            .kodeEfek(DEFAULT_KODE_EFEK)
            .quantity(DEFAULT_QUANTITY);
        return pengajuanGadaiEfekDtl;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PengajuanGadaiEfekDtl createUpdatedEntity(EntityManager em) {
        PengajuanGadaiEfekDtl pengajuanGadaiEfekDtl = new PengajuanGadaiEfekDtl()
            .noPengajuanGadai(UPDATED_NO_PENGAJUAN_GADAI)
            .kodeEfek(UPDATED_KODE_EFEK)
            .quantity(UPDATED_QUANTITY);
        return pengajuanGadaiEfekDtl;
    }

    @BeforeEach
    public void initTest() {
        pengajuanGadaiEfekDtl = createEntity(em);
    }

    @Test
    @Transactional
    public void createPengajuanGadaiEfekDtl() throws Exception {
        int databaseSizeBeforeCreate = pengajuanGadaiEfekDtlRepository.findAll().size();

        // Create the PengajuanGadaiEfekDtl
        restPengajuanGadaiEfekDtlMockMvc.perform(post("/api/pengajuan-gadai-efek-dtls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pengajuanGadaiEfekDtl)))
            .andExpect(status().isCreated());

        // Validate the PengajuanGadaiEfekDtl in the database
        List<PengajuanGadaiEfekDtl> pengajuanGadaiEfekDtlList = pengajuanGadaiEfekDtlRepository.findAll();
        assertThat(pengajuanGadaiEfekDtlList).hasSize(databaseSizeBeforeCreate + 1);
        PengajuanGadaiEfekDtl testPengajuanGadaiEfekDtl = pengajuanGadaiEfekDtlList.get(pengajuanGadaiEfekDtlList.size() - 1);
        assertThat(testPengajuanGadaiEfekDtl.getNoPengajuanGadai()).isEqualTo(DEFAULT_NO_PENGAJUAN_GADAI);
        assertThat(testPengajuanGadaiEfekDtl.getKodeEfek()).isEqualTo(DEFAULT_KODE_EFEK);
        assertThat(testPengajuanGadaiEfekDtl.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
    }

    @Test
    @Transactional
    public void createPengajuanGadaiEfekDtlWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pengajuanGadaiEfekDtlRepository.findAll().size();

        // Create the PengajuanGadaiEfekDtl with an existing ID
        pengajuanGadaiEfekDtl.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPengajuanGadaiEfekDtlMockMvc.perform(post("/api/pengajuan-gadai-efek-dtls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pengajuanGadaiEfekDtl)))
            .andExpect(status().isBadRequest());

        // Validate the PengajuanGadaiEfekDtl in the database
        List<PengajuanGadaiEfekDtl> pengajuanGadaiEfekDtlList = pengajuanGadaiEfekDtlRepository.findAll();
        assertThat(pengajuanGadaiEfekDtlList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNoPengajuanGadaiIsRequired() throws Exception {
        int databaseSizeBeforeTest = pengajuanGadaiEfekDtlRepository.findAll().size();
        // set the field null
        pengajuanGadaiEfekDtl.setNoPengajuanGadai(null);

        // Create the PengajuanGadaiEfekDtl, which fails.

        restPengajuanGadaiEfekDtlMockMvc.perform(post("/api/pengajuan-gadai-efek-dtls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pengajuanGadaiEfekDtl)))
            .andExpect(status().isBadRequest());

        List<PengajuanGadaiEfekDtl> pengajuanGadaiEfekDtlList = pengajuanGadaiEfekDtlRepository.findAll();
        assertThat(pengajuanGadaiEfekDtlList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkKodeEfekIsRequired() throws Exception {
        int databaseSizeBeforeTest = pengajuanGadaiEfekDtlRepository.findAll().size();
        // set the field null
        pengajuanGadaiEfekDtl.setKodeEfek(null);

        // Create the PengajuanGadaiEfekDtl, which fails.

        restPengajuanGadaiEfekDtlMockMvc.perform(post("/api/pengajuan-gadai-efek-dtls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pengajuanGadaiEfekDtl)))
            .andExpect(status().isBadRequest());

        List<PengajuanGadaiEfekDtl> pengajuanGadaiEfekDtlList = pengajuanGadaiEfekDtlRepository.findAll();
        assertThat(pengajuanGadaiEfekDtlList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPengajuanGadaiEfekDtls() throws Exception {
        // Initialize the database
        pengajuanGadaiEfekDtlRepository.saveAndFlush(pengajuanGadaiEfekDtl);

        // Get all the pengajuanGadaiEfekDtlList
        restPengajuanGadaiEfekDtlMockMvc.perform(get("/api/pengajuan-gadai-efek-dtls?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pengajuanGadaiEfekDtl.getId().intValue())))
            .andExpect(jsonPath("$.[*].noPengajuanGadai").value(hasItem(DEFAULT_NO_PENGAJUAN_GADAI.toString())))
            .andExpect(jsonPath("$.[*].kodeEfek").value(hasItem(DEFAULT_KODE_EFEK.toString())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY.intValue())));
    }
    
    @Test
    @Transactional
    public void getPengajuanGadaiEfekDtl() throws Exception {
        // Initialize the database
        pengajuanGadaiEfekDtlRepository.saveAndFlush(pengajuanGadaiEfekDtl);

        // Get the pengajuanGadaiEfekDtl
        restPengajuanGadaiEfekDtlMockMvc.perform(get("/api/pengajuan-gadai-efek-dtls/{id}", pengajuanGadaiEfekDtl.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pengajuanGadaiEfekDtl.getId().intValue()))
            .andExpect(jsonPath("$.noPengajuanGadai").value(DEFAULT_NO_PENGAJUAN_GADAI.toString()))
            .andExpect(jsonPath("$.kodeEfek").value(DEFAULT_KODE_EFEK.toString()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingPengajuanGadaiEfekDtl() throws Exception {
        // Get the pengajuanGadaiEfekDtl
        restPengajuanGadaiEfekDtlMockMvc.perform(get("/api/pengajuan-gadai-efek-dtls/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePengajuanGadaiEfekDtl() throws Exception {
        // Initialize the database
        pengajuanGadaiEfekDtlRepository.saveAndFlush(pengajuanGadaiEfekDtl);

        int databaseSizeBeforeUpdate = pengajuanGadaiEfekDtlRepository.findAll().size();

        // Update the pengajuanGadaiEfekDtl
        PengajuanGadaiEfekDtl updatedPengajuanGadaiEfekDtl = pengajuanGadaiEfekDtlRepository.findById(pengajuanGadaiEfekDtl.getId()).get();
        // Disconnect from session so that the updates on updatedPengajuanGadaiEfekDtl are not directly saved in db
        em.detach(updatedPengajuanGadaiEfekDtl);
        updatedPengajuanGadaiEfekDtl
            .noPengajuanGadai(UPDATED_NO_PENGAJUAN_GADAI)
            .kodeEfek(UPDATED_KODE_EFEK)
            .quantity(UPDATED_QUANTITY);

        restPengajuanGadaiEfekDtlMockMvc.perform(put("/api/pengajuan-gadai-efek-dtls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPengajuanGadaiEfekDtl)))
            .andExpect(status().isOk());

        // Validate the PengajuanGadaiEfekDtl in the database
        List<PengajuanGadaiEfekDtl> pengajuanGadaiEfekDtlList = pengajuanGadaiEfekDtlRepository.findAll();
        assertThat(pengajuanGadaiEfekDtlList).hasSize(databaseSizeBeforeUpdate);
        PengajuanGadaiEfekDtl testPengajuanGadaiEfekDtl = pengajuanGadaiEfekDtlList.get(pengajuanGadaiEfekDtlList.size() - 1);
        assertThat(testPengajuanGadaiEfekDtl.getNoPengajuanGadai()).isEqualTo(UPDATED_NO_PENGAJUAN_GADAI);
        assertThat(testPengajuanGadaiEfekDtl.getKodeEfek()).isEqualTo(UPDATED_KODE_EFEK);
        assertThat(testPengajuanGadaiEfekDtl.getQuantity()).isEqualTo(UPDATED_QUANTITY);
    }

    @Test
    @Transactional
    public void updateNonExistingPengajuanGadaiEfekDtl() throws Exception {
        int databaseSizeBeforeUpdate = pengajuanGadaiEfekDtlRepository.findAll().size();

        // Create the PengajuanGadaiEfekDtl

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPengajuanGadaiEfekDtlMockMvc.perform(put("/api/pengajuan-gadai-efek-dtls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pengajuanGadaiEfekDtl)))
            .andExpect(status().isBadRequest());

        // Validate the PengajuanGadaiEfekDtl in the database
        List<PengajuanGadaiEfekDtl> pengajuanGadaiEfekDtlList = pengajuanGadaiEfekDtlRepository.findAll();
        assertThat(pengajuanGadaiEfekDtlList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePengajuanGadaiEfekDtl() throws Exception {
        // Initialize the database
        pengajuanGadaiEfekDtlRepository.saveAndFlush(pengajuanGadaiEfekDtl);

        int databaseSizeBeforeDelete = pengajuanGadaiEfekDtlRepository.findAll().size();

        // Delete the pengajuanGadaiEfekDtl
        restPengajuanGadaiEfekDtlMockMvc.perform(delete("/api/pengajuan-gadai-efek-dtls/{id}", pengajuanGadaiEfekDtl.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PengajuanGadaiEfekDtl> pengajuanGadaiEfekDtlList = pengajuanGadaiEfekDtlRepository.findAll();
        assertThat(pengajuanGadaiEfekDtlList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PengajuanGadaiEfekDtl.class);
        PengajuanGadaiEfekDtl pengajuanGadaiEfekDtl1 = new PengajuanGadaiEfekDtl();
        pengajuanGadaiEfekDtl1.setId(1L);
        PengajuanGadaiEfekDtl pengajuanGadaiEfekDtl2 = new PengajuanGadaiEfekDtl();
        pengajuanGadaiEfekDtl2.setId(pengajuanGadaiEfekDtl1.getId());
        assertThat(pengajuanGadaiEfekDtl1).isEqualTo(pengajuanGadaiEfekDtl2);
        pengajuanGadaiEfekDtl2.setId(2L);
        assertThat(pengajuanGadaiEfekDtl1).isNotEqualTo(pengajuanGadaiEfekDtl2);
        pengajuanGadaiEfekDtl1.setId(null);
        assertThat(pengajuanGadaiEfekDtl1).isNotEqualTo(pengajuanGadaiEfekDtl2);
    }
}
