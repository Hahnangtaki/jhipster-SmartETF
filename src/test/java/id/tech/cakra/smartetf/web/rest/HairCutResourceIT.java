package id.tech.cakra.smartetf.web.rest;

import id.tech.cakra.smartetf.SmartEtfApp;
import id.tech.cakra.smartetf.domain.HairCut;
import id.tech.cakra.smartetf.repository.HairCutRepository;
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
 * Integration tests for the {@link HairCutResource} REST controller.
 */
@SpringBootTest(classes = SmartEtfApp.class)
public class HairCutResourceIT {

    private static final LocalDate DEFAULT_TANGGAL = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TANGGAL = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_TANGGAL = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_KODE_EFEK = "AAAAAAAAAA";
    private static final String UPDATED_KODE_EFEK = "BBBBBBBBBB";

    private static final Double DEFAULT_NILAI_HAIR_CUT = 0D;
    private static final Double UPDATED_NILAI_HAIR_CUT = 1D;
    private static final Double SMALLER_NILAI_HAIR_CUT = 0D - 1D;

    @Autowired
    private HairCutRepository hairCutRepository;

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

    private MockMvc restHairCutMockMvc;

    private HairCut hairCut;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final HairCutResource hairCutResource = new HairCutResource(hairCutRepository);
        this.restHairCutMockMvc = MockMvcBuilders.standaloneSetup(hairCutResource)
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
    public static HairCut createEntity(EntityManager em) {
        HairCut hairCut = new HairCut()
            .tanggal(DEFAULT_TANGGAL)
            .kodeEfek(DEFAULT_KODE_EFEK)
            .nilaiHairCut(DEFAULT_NILAI_HAIR_CUT);
        return hairCut;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HairCut createUpdatedEntity(EntityManager em) {
        HairCut hairCut = new HairCut()
            .tanggal(UPDATED_TANGGAL)
            .kodeEfek(UPDATED_KODE_EFEK)
            .nilaiHairCut(UPDATED_NILAI_HAIR_CUT);
        return hairCut;
    }

    @BeforeEach
    public void initTest() {
        hairCut = createEntity(em);
    }

    @Test
    @Transactional
    public void createHairCut() throws Exception {
        int databaseSizeBeforeCreate = hairCutRepository.findAll().size();

        // Create the HairCut
        restHairCutMockMvc.perform(post("/api/hair-cuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hairCut)))
            .andExpect(status().isCreated());

        // Validate the HairCut in the database
        List<HairCut> hairCutList = hairCutRepository.findAll();
        assertThat(hairCutList).hasSize(databaseSizeBeforeCreate + 1);
        HairCut testHairCut = hairCutList.get(hairCutList.size() - 1);
        assertThat(testHairCut.getTanggal()).isEqualTo(DEFAULT_TANGGAL);
        assertThat(testHairCut.getKodeEfek()).isEqualTo(DEFAULT_KODE_EFEK);
        assertThat(testHairCut.getNilaiHairCut()).isEqualTo(DEFAULT_NILAI_HAIR_CUT);
    }

    @Test
    @Transactional
    public void createHairCutWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = hairCutRepository.findAll().size();

        // Create the HairCut with an existing ID
        hairCut.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restHairCutMockMvc.perform(post("/api/hair-cuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hairCut)))
            .andExpect(status().isBadRequest());

        // Validate the HairCut in the database
        List<HairCut> hairCutList = hairCutRepository.findAll();
        assertThat(hairCutList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTanggalIsRequired() throws Exception {
        int databaseSizeBeforeTest = hairCutRepository.findAll().size();
        // set the field null
        hairCut.setTanggal(null);

        // Create the HairCut, which fails.

        restHairCutMockMvc.perform(post("/api/hair-cuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hairCut)))
            .andExpect(status().isBadRequest());

        List<HairCut> hairCutList = hairCutRepository.findAll();
        assertThat(hairCutList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkKodeEfekIsRequired() throws Exception {
        int databaseSizeBeforeTest = hairCutRepository.findAll().size();
        // set the field null
        hairCut.setKodeEfek(null);

        // Create the HairCut, which fails.

        restHairCutMockMvc.perform(post("/api/hair-cuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hairCut)))
            .andExpect(status().isBadRequest());

        List<HairCut> hairCutList = hairCutRepository.findAll();
        assertThat(hairCutList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNilaiHairCutIsRequired() throws Exception {
        int databaseSizeBeforeTest = hairCutRepository.findAll().size();
        // set the field null
        hairCut.setNilaiHairCut(null);

        // Create the HairCut, which fails.

        restHairCutMockMvc.perform(post("/api/hair-cuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hairCut)))
            .andExpect(status().isBadRequest());

        List<HairCut> hairCutList = hairCutRepository.findAll();
        assertThat(hairCutList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllHairCuts() throws Exception {
        // Initialize the database
        hairCutRepository.saveAndFlush(hairCut);

        // Get all the hairCutList
        restHairCutMockMvc.perform(get("/api/hair-cuts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hairCut.getId().intValue())))
            .andExpect(jsonPath("$.[*].tanggal").value(hasItem(DEFAULT_TANGGAL.toString())))
            .andExpect(jsonPath("$.[*].kodeEfek").value(hasItem(DEFAULT_KODE_EFEK.toString())))
            .andExpect(jsonPath("$.[*].nilaiHairCut").value(hasItem(DEFAULT_NILAI_HAIR_CUT.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getHairCut() throws Exception {
        // Initialize the database
        hairCutRepository.saveAndFlush(hairCut);

        // Get the hairCut
        restHairCutMockMvc.perform(get("/api/hair-cuts/{id}", hairCut.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(hairCut.getId().intValue()))
            .andExpect(jsonPath("$.tanggal").value(DEFAULT_TANGGAL.toString()))
            .andExpect(jsonPath("$.kodeEfek").value(DEFAULT_KODE_EFEK.toString()))
            .andExpect(jsonPath("$.nilaiHairCut").value(DEFAULT_NILAI_HAIR_CUT.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingHairCut() throws Exception {
        // Get the hairCut
        restHairCutMockMvc.perform(get("/api/hair-cuts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateHairCut() throws Exception {
        // Initialize the database
        hairCutRepository.saveAndFlush(hairCut);

        int databaseSizeBeforeUpdate = hairCutRepository.findAll().size();

        // Update the hairCut
        HairCut updatedHairCut = hairCutRepository.findById(hairCut.getId()).get();
        // Disconnect from session so that the updates on updatedHairCut are not directly saved in db
        em.detach(updatedHairCut);
        updatedHairCut
            .tanggal(UPDATED_TANGGAL)
            .kodeEfek(UPDATED_KODE_EFEK)
            .nilaiHairCut(UPDATED_NILAI_HAIR_CUT);

        restHairCutMockMvc.perform(put("/api/hair-cuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedHairCut)))
            .andExpect(status().isOk());

        // Validate the HairCut in the database
        List<HairCut> hairCutList = hairCutRepository.findAll();
        assertThat(hairCutList).hasSize(databaseSizeBeforeUpdate);
        HairCut testHairCut = hairCutList.get(hairCutList.size() - 1);
        assertThat(testHairCut.getTanggal()).isEqualTo(UPDATED_TANGGAL);
        assertThat(testHairCut.getKodeEfek()).isEqualTo(UPDATED_KODE_EFEK);
        assertThat(testHairCut.getNilaiHairCut()).isEqualTo(UPDATED_NILAI_HAIR_CUT);
    }

    @Test
    @Transactional
    public void updateNonExistingHairCut() throws Exception {
        int databaseSizeBeforeUpdate = hairCutRepository.findAll().size();

        // Create the HairCut

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHairCutMockMvc.perform(put("/api/hair-cuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hairCut)))
            .andExpect(status().isBadRequest());

        // Validate the HairCut in the database
        List<HairCut> hairCutList = hairCutRepository.findAll();
        assertThat(hairCutList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteHairCut() throws Exception {
        // Initialize the database
        hairCutRepository.saveAndFlush(hairCut);

        int databaseSizeBeforeDelete = hairCutRepository.findAll().size();

        // Delete the hairCut
        restHairCutMockMvc.perform(delete("/api/hair-cuts/{id}", hairCut.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<HairCut> hairCutList = hairCutRepository.findAll();
        assertThat(hairCutList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(HairCut.class);
        HairCut hairCut1 = new HairCut();
        hairCut1.setId(1L);
        HairCut hairCut2 = new HairCut();
        hairCut2.setId(hairCut1.getId());
        assertThat(hairCut1).isEqualTo(hairCut2);
        hairCut2.setId(2L);
        assertThat(hairCut1).isNotEqualTo(hairCut2);
        hairCut1.setId(null);
        assertThat(hairCut1).isNotEqualTo(hairCut2);
    }
}
