package za.ac.nwu.as.logic.flow.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import za.ac.nwu.as.domain.dto.CurrencyDto;
import za.ac.nwu.as.domain.persistence.Currency;
import za.ac.nwu.as.domain.persistence.CurrencyType;
import za.ac.nwu.as.domain.persistence.Member;
import za.ac.nwu.as.translator.CurrencyTranslator;
import za.ac.nwu.as.translator.MemberTranslator;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FetchCurrencyFlowImplTest {

    @Mock
    CurrencyTranslator currencyTranslator;

    @Mock
    MemberTranslator memberTranslator;

    @InjectMocks
    FetchCurrencyFlowImpl flow;

    @Test
    public void fetchCurrencyById() {
        CurrencyDto currencyDto = new CurrencyDto(BigDecimal.valueOf(15), "MILES");
        when(flow.fetchCurrencyById(anyInt())).thenReturn(currencyDto);
        CurrencyDto output = flow.fetchCurrencyById(anyInt());
        assertNotNull(output);
        assertEquals(output, currencyDto);
        verify(currencyTranslator, times(1)).fetchCurrencyById(anyInt());
    }

    @Test
    public void fetchCurrencyByMemberId() {
        Member member = new Member("Rudi", "Dreyer", LocalDate.of(2000, 5, 24),
                "rudidreyer7@gmail.com", "0767869466", new Currency(BigDecimal.valueOf(50),
                new CurrencyType("MILES")));

        when(memberTranslator.fetchMemberByIdPersist(anyInt())).thenReturn(member);
        CurrencyDto output = flow.fetchCurrencyByMemberId(anyInt());
        assertNotNull(output);
        assertEquals(member.getCurrency().getCurrencyAmount(), output.getCurrencyAmount());
        verify(memberTranslator, times(1)).fetchMemberByIdPersist(anyInt());
    }

    @Test
    public void fetchCurrencyByMemberIdIfNull() {
        when(memberTranslator.fetchMemberByIdPersist(anyInt())).thenReturn(null);
        CurrencyDto currencyDto = flow.fetchCurrencyByMemberId(anyInt());
        assertNull(currencyDto);
    }
}