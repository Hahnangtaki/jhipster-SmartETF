package id.tech.cakra.smartetf.web.rest;

import id.tech.cakra.smartetf.SmartEtfApp;
import id.tech.cakra.smartetf.domain.Broker;
import id.tech.cakra.smartetf.repository.BrokerRepository;
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
 * Integration tests for the {@link BrokerResource} REST controller.
 */
@SpringBootTest(classes = SmartEtfApp.class)
public class BrokerResourceIT {

    private static final String DEFAULT_KODE_BROKER = "AAAAAAAAAA";
    private static final String UPDATED_KODE_BROKER = "BBBBBBBBBB";

    private static final String DEFAULT_NAMA_BROKER = "AAAAAAAAAA";
    private static final String UPDATED_NAMA_BROKER = "BBBBBBBBBB";

    @Autowired
    private BrokerRepository brokerRepository;

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

    private MockMvc restBrokerMockMvc;

    private Broker broker;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BrokerResource brokerResource = new BrokerResource(brokerRepository);
        this.restBrokerMockMvc = MockMvcBuilders.standaloneSetup(brokerResource)
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
    public static Broker createEntity(EntityManager em) {
        Broker broker = new Broker()
            .kodeBroker(DEFAULT_KODE_BROKER)
            .namaBroker(DEFAULT_NAMA_BROKER);
        return broker;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Broker createUpdatedEntity(EntityManager em) {
        Broker broker = new Broker()
            .kodeBroker(UPDATED_KODE_BROKER)
            .namaBroker(UPDATED_NAMA_BROKER);
        return broker;
    }

    @BeforeEach
    public void initTest() {
        broker = createEntity(em);
    }

    @Test
    @Transactional
    public void createBroker() throws Exception {
        int databaseSizeBeforeCreate = brokerRepository.findAll().size();

        // Create the Broker
        restBrokerMockMvc.perform(post("/api/brokers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(broker)))
            .andExpect(status().isCreated());

        // Validate the Broker in the database
        List<Broker> brokerList = brokerRepository.findAll();
        assertThat(brokerList).hasSize(databaseSizeBeforeCreate + 1);
        Broker testBroker = brokerList.get(brokerList.size() - 1);
        assertThat(testBroker.getKodeBroker()).isEqualTo(DEFAULT_KODE_BROKER);
        assertThat(testBroker.getNamaBroker()).isEqualTo(DEFAULT_NAMA_BROKER);
    }

    @Test
    @Transactional
    public void createBrokerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = brokerRepository.findAll().size();

        // Create the Broker with an existing ID
        broker.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBrokerMockMvc.perform(post("/api/brokers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(broker)))
            .andExpect(status().isBadRequest());

        // Validate the Broker in the database
        List<Broker> brokerList = brokerRepository.findAll();
        assertThat(brokerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkKodeBrokerIsRequired() throws Exception {
        int databaseSizeBeforeTest = brokerRepository.findAll().size();
        // set the field null
        broker.setKodeBroker(null);

        // Create the Broker, which fails.

        restBrokerMockMvc.perform(post("/api/brokers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(broker)))
            .andExpect(status().isBadRequest());

        List<Broker> brokerList = brokerRepository.findAll();
        assertThat(brokerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNamaBrokerIsRequired() throws Exception {
        int databaseSizeBeforeTest = brokerRepository.findAll().size();
        // set the field null
        broker.setNamaBroker(null);

        // Create the Broker, which fails.

        restBrokerMockMvc.perform(post("/api/brokers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(broker)))
            .andExpect(status().isBadRequest());

        List<Broker> brokerList = brokerRepository.findAll();
        assertThat(brokerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBrokers() throws Exception {
        // Initialize the database
        brokerRepository.saveAndFlush(broker);

        // Get all the brokerList
        restBrokerMockMvc.perform(get("/api/brokers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(broker.getId().intValue())))
            .andExpect(jsonPath("$.[*].kodeBroker").value(hasItem(DEFAULT_KODE_BROKER.toString())))
            .andExpect(jsonPath("$.[*].namaBroker").value(hasItem(DEFAULT_NAMA_BROKER.toString())));
    }
    
    @Test
    @Transactional
    public void getBroker() throws Exception {
        // Initialize the database
        brokerRepository.saveAndFlush(broker);

        // Get the broker
        restBrokerMockMvc.perform(get("/api/brokers/{id}", broker.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(broker.getId().intValue()))
            .andExpect(jsonPath("$.kodeBroker").value(DEFAULT_KODE_BROKER.toString()))
            .andExpect(jsonPath("$.namaBroker").value(DEFAULT_NAMA_BROKER.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBroker() throws Exception {
        // Get the broker
        restBrokerMockMvc.perform(get("/api/brokers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBroker() throws Exception {
        // Initialize the database
        brokerRepository.saveAndFlush(broker);

        int databaseSizeBeforeUpdate = brokerRepository.findAll().size();

        // Update the broker
        Broker updatedBroker = brokerRepository.findById(broker.getId()).get();
        // Disconnect from session so that the updates on updatedBroker are not directly saved in db
        em.detach(updatedBroker);
        updatedBroker
            .kodeBroker(UPDATED_KODE_BROKER)
            .namaBroker(UPDATED_NAMA_BROKER);

        restBrokerMockMvc.perform(put("/api/brokers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedBroker)))
            .andExpect(status().isOk());

        // Validate the Broker in the database
        List<Broker> brokerList = brokerRepository.findAll();
        assertThat(brokerList).hasSize(databaseSizeBeforeUpdate);
        Broker testBroker = brokerList.get(brokerList.size() - 1);
        assertThat(testBroker.getKodeBroker()).isEqualTo(UPDATED_KODE_BROKER);
        assertThat(testBroker.getNamaBroker()).isEqualTo(UPDATED_NAMA_BROKER);
    }

    @Test
    @Transactional
    public void updateNonExistingBroker() throws Exception {
        int databaseSizeBeforeUpdate = brokerRepository.findAll().size();

        // Create the Broker

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBrokerMockMvc.perform(put("/api/brokers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(broker)))
            .andExpect(status().isBadRequest());

        // Validate the Broker in the database
        List<Broker> brokerList = brokerRepository.findAll();
        assertThat(brokerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBroker() throws Exception {
        // Initialize the database
        brokerRepository.saveAndFlush(broker);

        int databaseSizeBeforeDelete = brokerRepository.findAll().size();

        // Delete the broker
        restBrokerMockMvc.perform(delete("/api/brokers/{id}", broker.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Broker> brokerList = brokerRepository.findAll();
        assertThat(brokerList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Broker.class);
        Broker broker1 = new Broker();
        broker1.setId(1L);
        Broker broker2 = new Broker();
        broker2.setId(broker1.getId());
        assertThat(broker1).isEqualTo(broker2);
        broker2.setId(2L);
        assertThat(broker1).isNotEqualTo(broker2);
        broker1.setId(null);
        assertThat(broker1).isNotEqualTo(broker2);
    }
}
