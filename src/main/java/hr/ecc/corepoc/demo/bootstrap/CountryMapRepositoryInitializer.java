package hr.ecc.corepoc.demo.bootstrap;

import hr.ecc.corepoc.demo.domain.Country;
import hr.ecc.corepoc.demo.repository.impl.CountryMapRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CountryMapRepositoryInitializer {
    private static final String[][] COUNTRIES = {
        {"032", "AR", "ARG", "ARGENTINA"},
        {"051", "AM", "ARM", "ARMENIA"},
        {"553", "AW", "ABW", "ARUBA"},
        {"036", "AU", "AUS", "AUSTRALIA"},
        {"040", "AT", "AUT", "AUSTRIA"},
        {"031", "AZ", "AZE", "AZERBAIJAN"},
        {"048", "BD", "BGD", "BANGLADESH"},
        {"056", "BE", "BEL", "BELGIUM"}
    };
    private final Logger log = LoggerFactory.getLogger(CountryMapRepositoryInitializer.class);

    CountryMapRepositoryImpl countryMapRepository;

    @Autowired
    public CountryMapRepositoryInitializer(CountryMapRepositoryImpl countryMapRepository) {
        this.countryMapRepository = countryMapRepository;
        initData();
    }

    public void initData() {
        long sid = 0;
        for (int i=0; i < COUNTRIES.length; i++) {
            sid++;
            String code = COUNTRIES[i][0];
            String a2code = COUNTRIES[i][1];
            String a3code = COUNTRIES[i][2];
            String countryName = COUNTRIES[i][3];

            Country c = new Country();
            c.setId(sid);
            c.setCode(code);
            c.setCodeA2(a2code);
            c.setCodeA3(a3code);
            c.setName(countryName);

            countryMapRepository.getMap().put(sid, c);
        }
        log.info("--> Finished initializing data for CountryMapRepositoryImpl");
    }
}
