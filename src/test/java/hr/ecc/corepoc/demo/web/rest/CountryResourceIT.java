package hr.ecc.corepoc.demo.web.rest;

import hr.ecc.corepoc.demo.EccCorePocDemoApplication;
import hr.ecc.corepoc.demo.TestUtil;
import hr.ecc.corepoc.demo.domain.Country;
import hr.ecc.corepoc.demo.repository.CountryRepository;
import hr.ecc.corepoc.demo.service.CountryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Validator;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Integration tests for the {@link CountryResource} REST controller.
 */
@SpringBootTest(classes = EccCorePocDemoApplication.class)
public class CountryResourceIT {

    private static final Long DEFAULT_ID = 1L;

    private static final String DEFAULT_NAME = "ARGENTINA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "032";
    private static final String UPDATED_CODE = "2222";

    private static final String DEFAULT_CODE_A_2 = "AR";
    private static final String UPDATED_CODE_A_2 = "BB";

    private static final String DEFAULT_CODE_A_3 = "ARG";
    private static final String UPDATED_CODE_A_3 = "BBB";

    private static final Long EXISTING_ID = 8L;
    private static final String EXISTING_NAME = "BELGIUM";
    private static final String EXISTING_CODE = "056";
    private static final String EXISTING_CODE_A_2 = "BE";
    private static final String EXISTING_CODE_A_3 = "BEL";

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CountryService countryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private Validator validator;

    private MockMvc restCountryMockMvc;

    private Country country;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CountryResource countryResource = new CountryResource(countryService);

        this.restCountryMockMvc = MockMvcBuilders.standaloneSetup(countryResource)
                .setMessageConverters(jacksonMessageConverter)
                .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Country createEntity() {
        Country country = new Country()
                .name(DEFAULT_NAME)
                .code(DEFAULT_CODE)
                .codeA2(DEFAULT_CODE_A_2)
                .codeA3(DEFAULT_CODE_A_3);
        return country;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Country createUpdatedEntity() {
        Country country = new Country()
                .name(UPDATED_NAME)
                .code(UPDATED_CODE)
                .codeA2(UPDATED_CODE_A_2)
                .codeA3(UPDATED_CODE_A_3);
        return country;
    }

    @BeforeEach
    public void initTest() {
        country = createEntity();
    }

    @Test
    public void createCountry() throws Exception {
        int databaseSizeBeforeCreate = countryRepository.findAll().size();

        // Create the Country
        restCountryMockMvc.perform(post("/api/countries")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(country)))
                .andExpect(status().isCreated());

        // Validate the Country in the database
        List<Country> countryList = countryRepository.findAll();
        assertThat(countryList).hasSize(databaseSizeBeforeCreate + 1);
        Country testCountry = countryList.get(countryList.size() - 1);
        assertThat(testCountry.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCountry.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testCountry.getCodeA2()).isEqualTo(DEFAULT_CODE_A_2);
        assertThat(testCountry.getCodeA3()).isEqualTo(DEFAULT_CODE_A_3);
    }

    @Test
    public void createCountryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = countryRepository.findAll().size();

        // Create the Country with an existing ID
        country.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCountryMockMvc.perform(post("/api/countries")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(country)))
                .andExpect(status().isBadRequest());

        // Validate the Country in the database
        List<Country> countryList = countryRepository.findAll();
        assertThat(countryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = countryRepository.findAll().size();
        // set the field null
        country.setName(null);

        restCountryMockMvc.perform(post("/api/countries")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(country)))
                .andExpect(status().isBadRequest());

        List<Country> countryList = countryRepository.findAll();
        assertThat(countryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = countryRepository.findAll().size();
        // set the field null
        country.setCode(null);

        restCountryMockMvc.perform(post("/api/countries")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(country)))
                .andExpect(status().isBadRequest());

        List<Country> countryList = countryRepository.findAll();
        assertThat(countryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCodeA2IsRequired() throws Exception {
        int databaseSizeBeforeTest = countryRepository.findAll().size();
        // set the field null
        country.setCodeA2(null);

        restCountryMockMvc.perform(post("/api/countries")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(country)))
                .andExpect(status().isBadRequest());

        List<Country> countryList = countryRepository.findAll();
        assertThat(countryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCodeA3IsRequired() throws Exception {
        int databaseSizeBeforeTest = countryRepository.findAll().size();
        // set the field null
        country.setCodeA3(null);

        restCountryMockMvc.perform(post("/api/countries")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(country)))
                .andExpect(status().isBadRequest());

        List<Country> countryList = countryRepository.findAll();
        assertThat(countryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllCountries() throws Exception {

        // Get all the countryList
        restCountryMockMvc.perform(get("/api/countries"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
                .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
                .andExpect(jsonPath("$.[*].codeA2").value(hasItem(DEFAULT_CODE_A_2)))
                .andExpect(jsonPath("$.[*].codeA3").value(hasItem(DEFAULT_CODE_A_3)));
    }

    @Test
    public void getCountry() throws Exception {

        // Get the country
        restCountryMockMvc.perform(get("/api/countries/{id}", EXISTING_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(EXISTING_ID))
                .andExpect(jsonPath("$.name").value(EXISTING_NAME))
                .andExpect(jsonPath("$.code").value(EXISTING_CODE))
                .andExpect(jsonPath("$.codeA2").value(EXISTING_CODE_A_2))
                .andExpect(jsonPath("$.codeA3").value(EXISTING_CODE_A_3));
    }

    @Test
    public void getNonExistingCountry() throws Exception {
        // Get the country
        restCountryMockMvc.perform(get("/api/countries/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    public void updateCountry() throws Exception {

        int databaseSizeBeforeUpdate = countryRepository.findAll().size();

        // Update the country
        Country updatedCountry = countryRepository.findById(DEFAULT_ID);
        updatedCountry
                .name(UPDATED_NAME)
                .code(UPDATED_CODE)
                .codeA2(UPDATED_CODE_A_2)
                .codeA3(UPDATED_CODE_A_3);

        restCountryMockMvc.perform(put("/api/countries")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedCountry)))
                .andExpect(status().isOk());

        // Validate the Country in the database
        List<Country> countryList = countryRepository.findAll();
        assertThat(countryList).hasSize(databaseSizeBeforeUpdate);
        Country testCountry = countryRepository.findById(DEFAULT_ID);
        assertThat(testCountry.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCountry.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testCountry.getCodeA2()).isEqualTo(UPDATED_CODE_A_2);
        assertThat(testCountry.getCodeA3()).isEqualTo(UPDATED_CODE_A_3);
    }

    @Test
    public void updateNonExistingCountry() throws Exception {
        int databaseSizeBeforeUpdate = countryRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCountryMockMvc.perform(put("/api/countries")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(country)))
                .andExpect(status().isBadRequest());

        // Validate the Country in the database
        List<Country> countryList = countryRepository.findAll();
        assertThat(countryList).hasSize(databaseSizeBeforeUpdate);
    }

    /*
    @Test
    public void deleteCountry() throws Exception {

        int databaseSizeBeforeDelete = countryRepository.findAll().size();

        // Delete the country
        restCountryMockMvc.perform(delete("/api/countries/{id}", country.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Country> countryList = countryRepository.findAll();
        assertThat(countryList).hasSize(databaseSizeBeforeDelete - 1);
    }*/
}
