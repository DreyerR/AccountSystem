package za.ac.nwu.as.translator;

import za.ac.nwu.as.domain.service.GeneralResponse;

import java.math.BigDecimal;

public interface CurrencyTranslator {
    GeneralResponse<String> addCurrency(Integer memberId, BigDecimal amount);
    GeneralResponse<String> subtractCurrency(Integer memberId, BigDecimal amount);
}
