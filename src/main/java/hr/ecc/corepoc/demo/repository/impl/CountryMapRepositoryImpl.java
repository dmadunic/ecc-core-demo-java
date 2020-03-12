package hr.ecc.corepoc.demo.repository.impl;

import hr.ecc.corepoc.demo.domain.Country;
import hr.ecc.corepoc.demo.repository.CountryRepository;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Simple inmemory Map implementation of Country repository.
 *
 */
@Repository
public class CountryMapRepositoryImpl implements CountryRepository {
    protected Map<Long, Country> map = new HashMap<>();

    //--- interface methods ---------------------------------------------------

    @Override
    public Country findById(Long id) {
        return map.get(id);
    }

    @Override
    public List<Country> findAll() {
        return new ArrayList(map.values());
    }

    @Override
    public Country save(Country country) {
        if (country.getId() == null) {
            Long id = MapUtils.getNextLongId(map);
            country.setId(id);
        }
        map.put(country.getId(), country);
        return country;
    }

    @Override
    public void deleteById(Long id) {
        if (map.get(id) == null) {
            throw new ObjectRetrievalFailureException(Country.class, id);
        }
        map.remove(id);
    }

    public void delete(Country country) {
        deleteById(country.getId());
    }

    public long count() {
        return map.size();
    }

    //--- implementation specific methods -------------------------------------

    public Map<Long, Country> getMap() {
        return this.map;
    }

}
