package za.ac.nwu.as.repo.persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import za.ac.nwu.as.domain.persistence.CurrencyType;
import za.ac.nwu.as.repo.config.RepositoryTestConfig;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = {RepositoryTestConfig.class})
public class CurrencyTypeRepositoryTest {

    @Autowired
    CurrencyTypeRepository currencyTypeRepository;

    @After
    public void tearDown() throws Exception {
        currencyTypeRepository = null;
    }

    @Test
    public void findAllByOrderByCurrencyTypeIdAsc() {
        List<CurrencyType> currencyTypes = currencyTypeRepository.findAllByOrderByCurrencyTypeIdAsc();
        assertEquals(currencyTypes.size(), 2); // Only inserted 2 currency types in import.sql
    }

    @Test
    public void findFirstCurrencyTypeByName() {
        CurrencyType currencyType = currencyTypeRepository.findById(1).orElse(null);
        assertNotNull(currencyType);
        assertEquals(currencyType.getCurrencyTypeName(), "MILES");
    }

    @Test
    public void findCurrencyTypeThatDoesNotExist() {
        CurrencyType currencyType = currencyTypeRepository.findById(6).orElse(null);
        assertNull(currencyType);
    }
}