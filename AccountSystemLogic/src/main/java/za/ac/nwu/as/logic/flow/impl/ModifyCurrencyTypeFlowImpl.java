package za.ac.nwu.as.logic.flow.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.as.logic.flow.ModifyCurrencyTypeFlow;
import za.ac.nwu.as.translator.CurrencyTypeTranslator;

import javax.transaction.Transactional;

@Transactional
@Component
public class ModifyCurrencyTypeFlowImpl implements ModifyCurrencyTypeFlow {

    private final CurrencyTypeTranslator currencyTypeTranslator;

    @Autowired
    public ModifyCurrencyTypeFlowImpl(CurrencyTypeTranslator currencyTypeTranslator) {
        this.currencyTypeTranslator = currencyTypeTranslator;
    }

    @Override
    public void deleteCurrencyTypeById(Integer ctId) {
        currencyTypeTranslator.deleteCurrencyTypeById(ctId);
    }
}
