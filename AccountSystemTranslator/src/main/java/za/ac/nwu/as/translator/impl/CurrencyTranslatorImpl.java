package za.ac.nwu.as.translator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.as.domain.dto.CurrencyDto;
import za.ac.nwu.as.domain.persistence.Currency;
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

    @Autowired
    public CurrencyTranslatorImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public int updateCurrency(Integer memberId, BigDecimal amount) {
        return currencyRepository.updateCurrency(memberId, amount);
    }

    @Override
    public CurrencyDto fetchCurrencyById(Integer currencyId) {
        Currency currency = currencyRepository.findById(currencyId).orElse(null);
        return null != currency ? new CurrencyDto(currency) : null;
    }

    @Override
    public int updateCurrencyTypes(Integer fromCurrencyTypeId, Integer toCurrencyTypeId) {
        return currencyRepository.updateCurrencyTypes(toCurrencyTypeId, fromCurrencyTypeId);
    }
}
