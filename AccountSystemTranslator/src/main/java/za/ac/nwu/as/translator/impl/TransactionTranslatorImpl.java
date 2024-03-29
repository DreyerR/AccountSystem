package za.ac.nwu.as.translator.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.as.domain.dto.MemberDto;
import za.ac.nwu.as.domain.dto.TransactionDto;
import za.ac.nwu.as.domain.persistence.Member;
import za.ac.nwu.as.domain.persistence.Transaction;
import za.ac.nwu.as.repo.persistence.TransactionRepository;
import za.ac.nwu.as.translator.TransactionTranslator;

import java.math.BigDecimal;

@Component
public class TransactionTranslatorImpl implements TransactionTranslator {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionTranslatorImpl.class);
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
            LOGGER.error("TransactionTranslator: Unable to fetch by Id, {}", e.getMessage());
            throw new RuntimeException("TransactionTranslator: Unable to fetch by Id");
        }
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
}
