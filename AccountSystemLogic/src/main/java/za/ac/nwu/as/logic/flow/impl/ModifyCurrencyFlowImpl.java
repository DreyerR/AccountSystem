package za.ac.nwu.as.logic.flow.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.as.domain.persistence.CurrencyType;
import za.ac.nwu.as.domain.persistence.Member;
import za.ac.nwu.as.domain.persistence.Transaction;
import za.ac.nwu.as.domain.service.GeneralResponse;
import za.ac.nwu.as.logic.flow.ModifyCurrencyFlow;
import za.ac.nwu.as.translator.CurrencyTranslator;
import za.ac.nwu.as.translator.CurrencyTypeTranslator;
import za.ac.nwu.as.translator.MemberTranslator;
import za.ac.nwu.as.translator.TransactionTranslator;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;

@Transactional
@Component
public class ModifyCurrencyFlowImpl implements ModifyCurrencyFlow {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModifyCurrencyFlowImpl.class);

    private final CurrencyTranslator currencyTranslator;
    private final MemberTranslator memberTranslator;
    private final TransactionTranslator transactionTranslator;
    private final CurrencyTypeTranslator currencyTypeTranslator;

    @Autowired
    public ModifyCurrencyFlowImpl(CurrencyTranslator currencyTranslator, MemberTranslator memberTranslator,
                                  TransactionTranslator transactionTranslator, CurrencyTypeTranslator currencyTypeTranslator) {
        this.currencyTranslator = currencyTranslator;
        this.memberTranslator = memberTranslator;
        this.transactionTranslator = transactionTranslator;
        this.currencyTypeTranslator = currencyTypeTranslator;
    }

    @Override
    public GeneralResponse<String> addCurrency(Integer memberId, BigDecimal amount) {
        try {
            boolean isSuccessful = false;
            String message;

            LOGGER.info("Adding {} currency to member with ID {}",amount, memberId);
            Member doesMemberExist = memberTranslator.fetchMemberByIdPersist(memberId);
            if (null != doesMemberExist) {
                // Insert transaction
                int transaction = transactionTranslator.saveTransaction(new Transaction(
                        LocalDate.now(),
                        "+" + amount,
                        doesMemberExist
                ));

                // Update currency
                if (1 == transaction) { // If the transaction was successful, update currency
                    LOGGER.info("Transaction created: {}", transaction);
                    Integer currencyId = doesMemberExist.getCurrency().getCurrencyId();
                    BigDecimal currencyAmount = doesMemberExist.getCurrency().getCurrencyAmount().add(amount);

                    LOGGER.info("Updating currency with ID {} to {}", currencyId, currencyAmount);

                    isSuccessful = currencyTranslator.updateCurrency(currencyId, currencyAmount) == 1;
                    if (isSuccessful) {
                        message = "Successfully added " + amount + " currency";
                        LOGGER.info("Currency with ID {} updated successfully", currencyId);
                    }
                    else {
                        message = "Error: Could not update currency in database";
                        LOGGER.warn("Unable to update currency in database: isSuccessful = {}", false);
                    }
                }
                else {
                    message = "Error: Unable to insert transaction. Operation aborted";
                    LOGGER.warn("Failed to create transaction");
                }
            }
            else {
                message = String.format("Member with ID %d does not exist", memberId);
                LOGGER.warn("Member with ID {} does not exist", memberId);
            }

            return new GeneralResponse<>(isSuccessful, message);
        }
        catch (Exception e) {
            LOGGER.error("RuntimeException: {}", e.getMessage());
            throw new RuntimeException("ModifyCurrencyFlowImpl: Unable to add currency", e);
        }
    }

    @Override
    public GeneralResponse<String> subtractCurrency(Integer memberId, BigDecimal amount) {
        try {
            boolean isSuccessful = false;
            String message;

            LOGGER.info("Subtracting {} currency from member with ID {}",amount, memberId);
            Member doesMemberExist = memberTranslator.fetchMemberByIdPersist(memberId);
            if (null != doesMemberExist) {
                BigDecimal currencyAmount = doesMemberExist.getCurrency().getCurrencyAmount();
                if (currencyAmount.compareTo(BigDecimal.valueOf(0)) > 0 && currencyAmount.compareTo(amount) >= 0) { // Do they have sufficient funds to subtract currency? And is the amount available >= the amount subtracted?
                    // Insert transaction
                    int wasSuccessful = transactionTranslator.saveTransaction(new Transaction(
                            LocalDate.now(),
                            "-" + amount,
                            doesMemberExist
                    ));

                    // Update currency
                    if (1 == wasSuccessful) {
                        LOGGER.info("Transaction created successfully");
                        Integer currencyId = doesMemberExist.getCurrency().getCurrencyId();
                        currencyAmount = currencyAmount.subtract(amount);

                        isSuccessful = currencyTranslator.updateCurrency(currencyId, currencyAmount) == 1;
                        if (isSuccessful) {
                            message = "Successfully subtracted " + amount + " currency";
                            LOGGER.info("Currency with ID {} updated successfully", currencyId);
                        }
                        else {
                            message = "Error: Could not update currency in database";
                            LOGGER.warn("Unable to update currency in database: isSuccessful = {}", false);
                        }
                    }
                    else {
                        message = "Error: Unable to insert transaction. Operation aborted";
                        LOGGER.warn("Failed to create transaction");
                    }
                }
                else {
                    message = "Unable to subtract: Insufficient funds";
                    LOGGER.warn("Could not subtract currency: Insufficient funds");
                }
            }
            else {
                message = String.format("Member with ID %d does not exist", memberId);
                LOGGER.warn("Member with ID {} does not exist", memberId);
            }

            return new GeneralResponse<>(isSuccessful, message);
        }
        catch (Exception e) {
            LOGGER.error("RuntimeException: {}", e.getMessage());
            throw new RuntimeException("ModifyCurrencyFlowImpl: Unable to subtract currency", e);
        }
    }

    @Override
    public int updateCurrencyTypes(String fromCT, String toCT) {
        try {
            CurrencyType fromCurrencyType = currencyTypeTranslator.fetchCurrencyTypeByName(fromCT);
            CurrencyType toCurrencyType = currencyTypeTranslator.fetchCurrencyTypeByName(toCT);

            LOGGER.info("Attempting to update currency types from {} to {}", fromCT, toCT);

            if (null != fromCurrencyType && null != toCurrencyType) {
                LOGGER.info("From currency type: {}", fromCurrencyType);
                LOGGER.info("To currency type: {}", toCurrencyType);
                return currencyTranslator.updateCurrencyTypes(fromCurrencyType.getCurrencyTypeId(),
                        toCurrencyType.getCurrencyTypeId());
            }
            LOGGER.warn("One or more currency types not found: FromCT - {} or ToCT - {}", fromCurrencyType, toCurrencyType);
            return -1; // If one or both of the currency types cannot be found
        }
        catch (Exception e) {
            LOGGER.error("RuntimeException: {}", e.getMessage());
            throw new RuntimeException("ModifyCurrencyTranslator: Unable to update currency types", e);
        }
    }
}
