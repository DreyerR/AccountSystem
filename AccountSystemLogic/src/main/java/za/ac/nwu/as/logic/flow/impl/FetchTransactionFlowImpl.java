package za.ac.nwu.as.logic.flow.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.as.domain.dto.TransactionDto;
import za.ac.nwu.as.logic.flow.FetchTransactionFlow;
import za.ac.nwu.as.translator.TransactionTranslator;

import javax.transaction.Transactional;

@Transactional
@Component
public class FetchTransactionFlowImpl implements FetchTransactionFlow {

    private static final Logger LOGGER = LoggerFactory.getLogger(FetchTransactionFlowImpl.class);

    private final TransactionTranslator transactionTranslator;

    @Autowired
    public FetchTransactionFlowImpl(TransactionTranslator transactionTranslator) {
        this.transactionTranslator = transactionTranslator;
    }

    @Override
    public TransactionDto findTransactionById(Integer id) {
        LOGGER.info("Fetching transaction with ID {}", id);
        return transactionTranslator.findTransactionById(id);
    }
}
