package hr.ecc.corepoc.demo.service;

import hr.ecc.corepoc.demo.domain.Country;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CountryService {

    /**
     * Save a country.
     *
     * @param country the entity to save.
     * @return the persisted entity.
     */
    Country save(Country country);

    /**
     * Get all the countries.
     *
     * @return the list of entities.
     */
    List<Country> findAll();


    /**
     * Get the "id" country.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Country> findOne(Long id);

    /**
     * Delete the "id" country.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
