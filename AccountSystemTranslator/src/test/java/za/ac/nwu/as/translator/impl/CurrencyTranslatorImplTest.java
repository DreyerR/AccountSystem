package za.ac.nwu.as.translator.impl;

import net.bytebuddy.dynamic.DynamicType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import za.ac.nwu.as.domain.dto.CurrencyDto;
import za.ac.nwu.as.repo.persistence.CurrencyRepository;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyTranslatorImplTest {

    @Mock
    CurrencyRepository currencyRepository;

    @InjectMocks
    CurrencyTranslatorImpl translator;

    @Test
    public void updateCurrency() {
        when(translator.updateCurrency(1, BigDecimal.valueOf(20))).thenReturn(1);
        int isSuccessful = translator.updateCurrency(1, BigDecimal.valueOf(20));
        assertEquals(1, isSuccessful);
    }

//    @Test
//    public void fetchCurrencyById() {
//        Optional<CurrencyDto> optionalCurrencyDto = new Optional<CurrencyDto>(BigDecimal.valueOf(50), "MILES");
//        when(translator.fetchCurrencyById(4)).thenReturn(optionalCurrencyDto);
//        CurrencyDto currencyDto = translator.fetchCurrencyById(4);
//        assertNotNull(currencyDto);
//        assertEquals(BigDecimal.valueOf(50), currencyDto.getCurrencyAmount());
//        assertEquals("MILES", currencyDto.getCurrencyTypeName());
//        verify(currencyRepository, times(1)).findById(4);
//    }

    @Test
    public void updateCurrencyTypes() {
        when(translator.updateCurrencyTypes(1, 2)).thenReturn(1);
        int isSuccessful = translator.updateCurrencyTypes(1, 2);
        assertEquals(1, isSuccessful);
    }
}