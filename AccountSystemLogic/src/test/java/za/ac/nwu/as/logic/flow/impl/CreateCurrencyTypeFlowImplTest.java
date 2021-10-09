package za.ac.nwu.as.logic.flow.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import za.ac.nwu.as.domain.dto.CurrencyTypeDto;
import za.ac.nwu.as.domain.persistence.CurrencyType;
import za.ac.nwu.as.translator.CurrencyTypeTranslator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CreateCurrencyTypeFlowImplTest {

    @Mock
    CurrencyTypeTranslator currencyTypeTranslator;

    @InjectMocks
    CreateCurrencyTypeFlowImpl flow;

    @Test
    public void saveCurrencyType() {
        CurrencyTypeDto insertCurrencyTypeDto = new CurrencyTypeDto(0, "PLAYS");
        when(currencyTypeTranslator.saveCurrencyType(insertCurrencyTypeDto)).thenReturn(new CurrencyType(
                1,
                "PLAYS"
        ));
        CurrencyTypeDto output = flow.saveCurrencyType(insertCurrencyTypeDto);
        assertNotNull(output);
        assertEquals(insertCurrencyTypeDto.getCurrencyTypeName(), output.getCurrencyTypeName());
        verify(currencyTypeTranslator, times(1)).saveCurrencyType(insertCurrencyTypeDto);
    }
}