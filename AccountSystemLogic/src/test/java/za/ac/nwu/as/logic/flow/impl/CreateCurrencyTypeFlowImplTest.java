package za.ac.nwu.as.logic.flow.impl;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MvcResult;
import za.ac.nwu.as.domain.dto.CurrencyTypeDto;
import za.ac.nwu.as.translator.CurrencyTypeTranslator;

import static org.junit.Assert.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CreateCurrencyTypeFlowImplTest {

    @Mock
    private CurrencyTypeTranslator currencyTypeTranslator;

    @InjectMocks
    private CreateCurrencyTypeFlowImpl flow;

//    @Test
//    public void saveCurrencyType() {
//        CurrencyTypeDto currencyTypeDto = new CurrencyTypeDto(3, "PLAYS");
//        when(currencyTypeTranslator.saveCurrencyType(eq(currencyTypeDto))).thenReturn(currencyTypeDto);
//        CurrencyTypeDto savedCurrencyTypeDto = flow.saveCurrencyType(currencyTypeDto);
//        assertNotNull(savedCurrencyTypeDto);
//        assertEquals(currencyTypeDto.getCurrencyTypeName(), savedCurrencyTypeDto.getCurrencyTypeName());
//        verify(currencyTypeTranslator, times(1)).saveCurrencyType(any());
//
////        CurrencyTypeDto currencyTypeDto = new CurrencyTypeDto(null, "TEST");
////        when(createCurrencyTypeFlow.saveCurrencyType(eq(currencyTypeDto))).then(returnsFirstArg());
////
////        MvcResult mvcResult = mockMvc.perform(post(CT_CONTROLLER_URL)
////                        .servletPath(BASE_URL)
////                        .accept(MediaType.APPLICATION_JSON)
////                        .content(currencyTypeToBeCreated)
////                        .contentType(MediaType.APPLICATION_JSON))
////                .andExpect(status().isCreated())
////                .andReturn();
////
////        verify(createCurrencyTypeFlow, times(1)).saveCurrencyType(eq(currencyTypeDto));
////        assertEquals(expectedOutput, mvcResult.getResponse().getContentAsString());
//    }
}