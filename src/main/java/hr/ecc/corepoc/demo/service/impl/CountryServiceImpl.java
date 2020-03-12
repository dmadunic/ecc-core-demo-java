package hr.ecc.corepoc.demo.service.impl;

import hr.ecc.corepoc.demo.domain.Country;
import hr.ecc.corepoc.demo.repository.CountryRepository;
import hr.ecc.corepoc.demo.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * CountryService implementation. This is the place to configure cache-ing and other NFR.
 *
 */
@Service
public class CountryServiceImpl implements CountryService {
    private final Logger log = LoggerFactory.getLogger(CountryServiceImpl.class);

    CountryRepository countryRepository;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    @Transactional
    public Country save(Country country) {
        return countryRepository.save(country);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Country> findAll()throws DataAccessException {
        log.debug("Request to get all Countries");
        return countryRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Country> findOne(Long id) {
        log.debug("Request to get Country : {}", id);
        Country country = null;
        try {
            country = countryRepository.findById(id);
        } catch (ObjectRetrievalFailureException | EmptyResultDataAccessException e) {
            // just ignore not found exceptions for Jdbc/Jpa realization
            return Optional.empty();
        }
        Optional<Country> opt = Optional.ofNullable(country);
        return opt;
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Country : {}", id);
        countryRepository.deleteById(id);
    }
}
