//package za.ac.nwu.as.logic.flow.impl;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import za.ac.nwu.as.domain.dto.CurrencyDto;
//import za.ac.nwu.as.domain.persistence.Currency;
//import za.ac.nwu.as.domain.persistence.CurrencyType;
//import za.ac.nwu.as.domain.persistence.Member;
//import za.ac.nwu.as.translator.CurrencyTranslator;
//import za.ac.nwu.as.translator.MemberTranslator;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//
//import static org.junit.Assert.*;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.Mockito.*;
//
//@RunWith(MockitoJUnitRunner.class)
//public class FetchCurrencyFlowImplTest {
//
//    @Mock
//    private CurrencyTranslator currencyTranslator;
//
//    @Mock
//    private MemberTranslator memberTranslator;
//
//    @InjectMocks
//    private FetchCurrencyFlowImpl flow;
//
//    @Test
//    public void fetchCurrencyById() {
//        CurrencyDto currencyDto = new CurrencyDto(BigDecimal.valueOf(15), "MILES");
//        when(flow.fetchCurrencyById(anyInt())).thenReturn(currencyDto);
//        CurrencyDto output = flow.fetchCurrencyById(anyInt());
//        assertNotNull(output);
//        assertEquals(output, currencyDto);
//        verify(currencyTranslator, times(1)).fetchCurrencyById(anyInt());
//    }
//
////    @Test
////    public void fetchCurrencyByMemberId() {
////        Member member = new Member("Rudi", "Dreyer", LocalDate.of(2000, 5, 24),
////                "rudidreyer7@gmail.com", "0767869466", new Currency(BigDecimal.valueOf(50),
////                new CurrencyType("MILES")));
//////        lenient().when(flow.fetchCurrencyByMemberId(1)).thenReturn(member);
////        lenient().when(flow.fetchCurrencyByMemberId(1)).thenReturn(
////                new CurrencyDto(member.getCurrency())
////        );
////        CurrencyDto currencyDto = flow.fetchCurrencyByMemberId(1);
////        assertNotNull(currencyDto);
////        verify(memberTranslator, times(1)).fetchMemberByIdPersist(3);
////    }
//
//    @Test
//    public void fetchCurrencyByMemberIdIfNull() {
//        when(memberTranslator.fetchMemberByIdPersist(anyInt())).thenReturn(null);
//        CurrencyDto currencyDto = flow.fetchCurrencyByMemberId(anyInt());
//        assertNull(currencyDto);
//    }
//}