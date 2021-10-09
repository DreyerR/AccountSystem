package za.ac.nwu.as.translator.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import za.ac.nwu.as.domain.dto.CurrencyTypeDto;
import za.ac.nwu.as.domain.persistence.CurrencyType;
import za.ac.nwu.as.repo.persistence.CurrencyTypeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyTypeTranslatorImplTest {

    @Mock
    CurrencyTypeRepository currencyTypeRepository;

    @InjectMocks
    CurrencyTypeTranslatorImpl translator;

//    @Test
//    public void fetchAllCurrencyTypes() {
//        List<CurrencyTypeDto> currencyTypeList = new ArrayList<>();
//        currencyTypeList.add(new CurrencyTypeDto(1, "MILES"));
//        currencyTypeList.add(new CurrencyTypeDto(2, "PLAYS"));
//
//        when(translator.fetchAllCurrencyTypes()).thenReturn(currencyTypeList);
//        List<CurrencyTypeDto> output = translator.fetchAllCurrencyTypes();
//        assertNotNull(output);
//        assertEquals(2, output.size());
//    }
//
//    @Test
//    public void saveCurrencyType() {
//        CurrencyType currencyType = new CurrencyType(1, "MILES");
//        when(currencyTypeRepository.save(new CurrencyType(currencyType.getCurrencyTypeName()))).thenReturn(currencyType);
//        CurrencyType testCurrencyType = translator.saveCurrencyType(new CurrencyTypeDto(0, "MILES"));
//        assertNotNull(testCurrencyType);
//    }

    @Test
    public void fetchCurrencyTypeByName() {
        when(translator.fetchCurrencyTypeByName(anyString()))
                .thenReturn(new CurrencyType(1, "RAND"));
        CurrencyType output = translator.fetchCurrencyTypeByName("RAND");
        assertNotNull(output);
        assertEquals(output.getCurrencyTypeName(), "RAND");
    }

    @Test
    public void deleteCurrencyTypeById() {
        translator.deleteCurrencyTypeById(anyInt());
        verify(currencyTypeRepository, times(1)).deleteById(anyInt());
    }
}