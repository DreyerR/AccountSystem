package za.ac.nwu.as.translator;

import java.math.BigDecimal;

public interface CurrencyTranslator {
    boolean addCurrency(Integer memberId, BigDecimal amount);
}
