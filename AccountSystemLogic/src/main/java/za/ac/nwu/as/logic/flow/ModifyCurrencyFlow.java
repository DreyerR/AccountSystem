package za.ac.nwu.as.logic.flow;

import za.ac.nwu.as.domain.service.GeneralResponse;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ModifyCurrencyFlow {
    GeneralResponse<String> addCurrency(Integer memberId, BigDecimal amount, LocalDate date);
    GeneralResponse<String> subtractCurrency(Integer memberId, BigDecimal amount);
    int updateCurrencyTypes(String fromCT, String toCT);
}
