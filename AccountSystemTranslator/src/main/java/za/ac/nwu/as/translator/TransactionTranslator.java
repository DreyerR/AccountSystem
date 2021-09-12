package za.ac.nwu.as.translator;

import za.ac.nwu.as.domain.dto.TransactionDto;

public interface TransactionTranslator {
    TransactionDto findTransactionById(Integer id);
}
