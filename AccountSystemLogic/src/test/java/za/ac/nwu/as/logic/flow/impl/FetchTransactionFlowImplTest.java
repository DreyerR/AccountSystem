package za.ac.nwu.as.logic.flow.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import za.ac.nwu.as.domain.dto.TransactionDto;
import za.ac.nwu.as.translator.TransactionTranslator;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FetchTransactionFlowImplTest {

    @Mock
    private TransactionTranslator transactionTranslator;

    @InjectMocks
    private FetchTransactionFlowImpl flow;

    @Test
    public void findTransactionById() {
        TransactionDto transactionDto = new TransactionDto(
                1,
                LocalDate.of(2021, 10, 8),
                "+50");

        when(transactionTranslator.findTransactionById(anyInt())).thenReturn(transactionDto);
        TransactionDto output = flow.findTransactionById(anyInt());
        assertNotNull(output);
        assertEquals(transactionDto.getTransactionChange(), output.getTransactionChange());
        verify(transactionTranslator, times(1)).findTransactionById(anyInt());
    }
}