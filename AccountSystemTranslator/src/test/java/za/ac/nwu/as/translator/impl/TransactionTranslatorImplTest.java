package za.ac.nwu.as.translator.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import za.ac.nwu.as.domain.dto.TransactionDto;
import za.ac.nwu.as.domain.persistence.Member;
import za.ac.nwu.as.domain.persistence.Transaction;
import za.ac.nwu.as.repo.persistence.TransactionRepository;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TransactionTranslatorImplTest {

    @Mock
    TransactionRepository transactionRepository;

    @InjectMocks
    TransactionTranslatorImpl translator;

    Transaction transaction;

    @Before
    public void init() {
        transaction = new Transaction(1, LocalDate.now(), "+40", new Member());
    }

    @Test
    public void findTransactionById() {
        when(transactionRepository.findById(1)).thenReturn(Optional.of(transaction));
        TransactionDto transactionDto = translator.findTransactionById(1);
        assertNotNull(transactionDto);
        assertEquals(transaction.getTransactionChange(), transactionDto.getTransactionChange());
        verify(transactionRepository, times(1)).findById(1);
    }

//    @Test
//    public void findTransactionByIdIfNull() {
//        Transaction transaction = null;
//        when(transactionRepository.findById(1)).thenReturn(null);
//        TransactionDto transactionDto = translator.findTransactionById(1);
//        assertNull(transactionDto);
//    }
//
//    @Test
//    public void saveTransaction() {
//        when(transactionRepository.save(transaction)).thenReturn(new Transaction(
//                2,
//                LocalDate.now(),
//                "+40",
//                new Member()
//        ));
//        Transaction transaction = translator.saveTransaction(new Transaction(1, LocalDate.now(), "+40",
//                new Member()));
//        assertNotNull(transaction);
//        verify(transactionRepository, times(1)).save(transaction);
//    }
}