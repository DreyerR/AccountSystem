package za.ac.nwu.as.translator;

import za.ac.nwu.as.domain.dto.MemberDto;
import za.ac.nwu.as.domain.dto.TransactionDto;
import za.ac.nwu.as.domain.persistence.Transaction;

import java.math.BigDecimal;

public interface TransactionTranslator {
    TransactionDto findTransactionById(Integer id);
    Transaction saveTransaction(Transaction transaction);
}
