package za.ac.nwu.as.logic.flow.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.as.domain.service.GeneralResponse;
import za.ac.nwu.as.logic.flow.CurrencyFlow;
import za.ac.nwu.as.translator.CurrencyTranslator;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Transactional
@Component
public class CurrencyFlowImpl implements CurrencyFlow {

    private final CurrencyTranslator currencyTranslator;

    @Autowired
    public CurrencyFlowImpl(CurrencyTranslator currencyTranslator) {
        this.currencyTranslator = currencyTranslator;
    }

    @Override
    public GeneralResponse<String> addCurrency(Integer memberId, BigDecimal amount) {
        return currencyTranslator.addCurrency(memberId, amount);
    }

    @Override
    public GeneralResponse<String> subtractCurrency(Integer memberId, BigDecimal amount) {
        return currencyTranslator.subtractCurrency(memberId, amount);
    }
}
