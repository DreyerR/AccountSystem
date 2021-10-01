package za.ac.nwu.as.translator;

import za.ac.nwu.as.domain.dto.CurrencyDto;

import java.math.BigDecimal;

public interface CurrencyTranslator {
    int updateCurrency(Integer memberId, BigDecimal amount);
    CurrencyDto fetchCurrencyById(Integer currencyId);
    int updateCurrencyTypes(Integer fromCurrencyTypeId, Integer toCurrencyTypeId);
}
