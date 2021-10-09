package za.ac.nwu.as.logic.flow.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import za.ac.nwu.as.domain.dto.CurrencyTypeDto;
import za.ac.nwu.as.translator.CurrencyTypeTranslator;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FetchCurrencyTypeFlowImplTest {

    @Mock
    private CurrencyTypeTranslator currencyTypeTranslator;

    @InjectMocks
    private FetchCurrencyTypeFlowImpl flow;

    @Test
    public void fetchAllCurrencyTypes() {
        List<CurrencyTypeDto> currencyTypeDtoList = new ArrayList<>();
        currencyTypeDtoList.add(new CurrencyTypeDto(1, "MILES"));
        currencyTypeDtoList.add(new CurrencyTypeDto(2, "PLAYS"));

        when(currencyTypeTranslator.fetchAllCurrencyTypes()).thenReturn(currencyTypeDtoList);
        List<CurrencyTypeDto> output = flow.fetchAllCurrencyTypes();
        assertEquals(currencyTypeDtoList.size(), output.size());
        verify(currencyTypeTranslator, times(1)).fetchAllCurrencyTypes();
    }
}