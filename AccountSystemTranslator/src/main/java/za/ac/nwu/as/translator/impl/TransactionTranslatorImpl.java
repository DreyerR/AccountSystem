package za.ac.nwu.as.translator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.as.domain.dto.TransactionDto;
import za.ac.nwu.as.domain.persistence.Transaction;
import za.ac.nwu.as.repo.persistence.TransactionRepository;
import za.ac.nwu.as.translator.TransactionTranslator;

@Component
public class TransactionTranslatorImpl implements TransactionTranslator {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionTranslatorImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public TransactionDto findTransactionById(Integer id) {
        try {
            Transaction transaction = transactionRepository.findById(id).orElse(null);
            return transaction != null ? new TransactionDto(transaction) : null;
        }
        catch (Exception e) {
            // TODO: Log
            throw new RuntimeException("TransactionTranslator: Unable to fetch by Id");
        }
    }
}
