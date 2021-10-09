//package za.ac.nwu.as.logic.flow.impl;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import za.ac.nwu.as.domain.persistence.Currency;
//import za.ac.nwu.as.domain.persistence.Member;
//import za.ac.nwu.as.domain.persistence.Transaction;
//import za.ac.nwu.as.domain.service.GeneralResponse;
//import za.ac.nwu.as.translator.CurrencyTranslator;
//import za.ac.nwu.as.translator.CurrencyTypeTranslator;
//import za.ac.nwu.as.translator.MemberTranslator;
//import za.ac.nwu.as.translator.TransactionTranslator;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//
//import static org.junit.Assert.*;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.Mockito.*;
//
//@RunWith(MockitoJUnitRunner.class)
//public class ModifyCurrencyFlowImplTest {
//
//    @Mock
//    private CurrencyTranslator currencyTranslator;
//    @Mock
//    private MemberTranslator memberTranslator;
//    @Mock
//    private TransactionTranslator transactionTranslator;
//    @Mock
//    private CurrencyTypeTranslator currencyTypeTranslator;
//
//    @InjectMocks
//    private ModifyCurrencyFlowImpl flow;
//
//    Member member;
//    Transaction transaction;
//
//    @Before
//    public void setUp() {
//        member = new Member(
//                "Rudi",
//                "Dreyer",
//                LocalDate.of(2000, 5, 24),
//                "rudidreyer7@gmail.com",
//                "0824516387",
//                new Currency(1, BigDecimal.valueOf(120))
//        );
//
//        transaction = new Transaction(LocalDate.of(2021, 10, 8), "+50", member);
//    }
//
//    @Test
//    public void addCurrency() {
//        when(memberTranslator.fetchMemberByIdPersist(1)).thenReturn(member);
//        doReturn(1).when(currencyTranslator).updateCurrency(1, BigDecimal.valueOf(50));
//        ModifyCurrencyFlowImpl modifyCurrencyFlowMock = spy(flow);
//        when(transactionTranslator.saveTransaction(transaction)).thenReturn(new Transaction(
//                1,
//                LocalDate.of(2021, 10, 8),
//                "+50",
//                member
//        ));
//        doReturn(true).when(modifyCurrencyFlowMock).createTransaction(member, "+50");
//        GeneralResponse<String> expectedOutput = new GeneralResponse<>(true, "Successfully added 170 currency");
//        GeneralResponse<String> output = flow.addCurrency(1, BigDecimal.valueOf(50));
//        assertEquals(expectedOutput.getPayload(), output.getPayload());
//    }
//
//    @Test
//    public void subtractCurrency() {
//        when(memberTranslator.fetchMemberByIdPersist(1)).thenReturn(member);
//        doReturn(1).when(currencyTranslator).updateCurrency(1, BigDecimal.valueOf(20));
//        ModifyCurrencyFlowImpl modifyCurrencyFlowMock = spy(flow);
//        when(transactionTranslator.saveTransaction(transaction)).thenReturn(new Transaction(
//                1,
//                LocalDate.of(2021, 10, 8),
//                "-20",
//                member
//        ));
//        doReturn(true).when(modifyCurrencyFlowMock).createTransaction(member, "-20");
//
//        GeneralResponse<String> expectedOutput = new GeneralResponse<>(true, "Successfully subtracted 20 currency");
//        GeneralResponse<String> output = flow.addCurrency(1, BigDecimal.valueOf(20));
//        assertEquals(expectedOutput.getPayload(), output.getPayload());
//    }
//}