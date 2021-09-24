package za.ac.nwu.as.logic.flow;

import java.math.BigDecimal;

public interface CurrencyFlow {
    boolean addCurrency(Integer memberId, BigDecimal amount);
}
