package za.ac.nwu.as.translator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.as.domain.persistence.Member;
import za.ac.nwu.as.domain.persistence.Transaction;
import za.ac.nwu.as.domain.service.GeneralResponse;
import za.ac.nwu.as.repo.persistence.CurrencyRepository;
import za.ac.nwu.as.translator.CurrencyTranslator;
import za.ac.nwu.as.translator.MemberTranslator;
import za.ac.nwu.as.translator.TransactionTranslator;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class CurrencyTranslatorImpl implements CurrencyTranslator {

    private final CurrencyRepository currencyRepository;
    private final MemberTranslator memberTranslator;
    private final TransactionTranslator transactionTranslator;

    @Autowired
    public CurrencyTranslatorImpl(CurrencyRepository currencyRepository, MemberTranslator memberTranslator,
                                  TransactionTranslator transactionTranslator) {
        this.currencyRepository = currencyRepository;
        this.memberTranslator = memberTranslator;
        this.transactionTranslator = transactionTranslator;
    }

    @Override
    public GeneralResponse<String> addCurrency(Integer memberId, BigDecimal amount) {
        try {
            boolean isSuccessful = false;
            String message;

            Member doesMemberExist = memberTranslator.doesMemberExist(memberId);
            if (null != doesMemberExist) {
                // Insert transaction
                Transaction transaction = transactionTranslator.saveTransaction(new Transaction(
                        LocalDate.now(),
                        "+" + amount,
                        doesMemberExist
                ));

                // Update currency
                if (null != transaction) { // If the transaction was successful, update currency
                    Integer currencyId = doesMemberExist.getCurrency().getCurrencyId();
                    BigDecimal currencyAmount = doesMemberExist.getCurrency().getCurrencyAmount().add(amount);

                    isSuccessful = currencyRepository.updateCurrency(currencyId, currencyAmount) == 1;
                    if (isSuccessful)
                        message = "Successfully added " + amount + " currency";
                    else
                        message = "Error: Could not update currency in database";
                }
                else {
                    message = "Error: Unable to insert transaction. Operation aborted";
                }
            }
            else {
                message = String.format("Member with ID %d does not exist", memberId);
            }

            return new GeneralResponse<>(isSuccessful, message);
        }
        catch (Exception e) {
            throw new RuntimeException("CurrencyTranslator: Unable to add currency", e);
        }
    }

    @Override
    public GeneralResponse<String> subtractCurrency(Integer memberId, BigDecimal amount) {
        try {
            boolean isSuccessful = false;
            String message;

            Member doesMemberExist = memberTranslator.doesMemberExist(memberId);
            if (null != doesMemberExist) {
                BigDecimal currencyAmount = doesMemberExist.getCurrency().getCurrencyAmount();
                if (currencyAmount.compareTo(BigDecimal.valueOf(0)) > 0 && currencyAmount.compareTo(amount) >= 0) { // Do they have sufficient funds to subtract currency? And is the amount available >= the amount subtracted?
                    // Insert transaction
                    Transaction transaction = transactionTranslator.saveTransaction(new Transaction(
                            LocalDate.now(),
                            "-" + amount,
                            doesMemberExist
                    ));

                    // Update currency
                    if (null != transaction) {
                        Integer currencyId = doesMemberExist.getCurrency().getCurrencyId();
                        currencyAmount = currencyAmount.subtract(amount);

                        isSuccessful = currencyRepository.updateCurrency(currencyId, currencyAmount) == 1;
                        if (isSuccessful)
                            message = "Successfully subtracted " + amount + " currency";
                        else
                            message = "Error: Could not update currency in database";
                    }
                    else {
                        message = "Error: Unable to insert transaction. Operation aborted";
                    }
                }
                else {
                    message = "Unable to subtract: Insufficient funds";
                }
            }
            else {
                message = String.format("Member with ID %d does not exist", memberId);
            }

            return new GeneralResponse<>(isSuccessful, message);
        }
        catch (Exception e) {
            // TODO: Log
            throw new RuntimeException("CurrencyTranslator: Unable to subtract currency", e);
        }
    }
}
