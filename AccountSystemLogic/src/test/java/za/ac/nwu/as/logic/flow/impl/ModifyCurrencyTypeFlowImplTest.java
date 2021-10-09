//package za.ac.nwu.as.logic.flow.impl;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;
//import za.ac.nwu.as.translator.CurrencyTypeTranslator;
//
//import static org.junit.Assert.*;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.Mockito.*;
//
//@RunWith(MockitoJUnitRunner.class)
//public class ModifyCurrencyTypeFlowImplTest {
//
//    @Mock
//    private CurrencyTypeTranslator currencyTypeTranslator;
//
//    @InjectMocks
//    private ModifyCurrencyTypeFlowImpl flow;
//
//    @Test
//    public void deleteCurrencyTypeById() {
//        doNothing().when(currencyTypeTranslator).deleteCurrencyTypeById(2);
//        verify(currencyTypeTranslator, times(1)).deleteCurrencyTypeById(2);
//    }
//}