package za.ac.nwu.as.logic.flow.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.as.domain.dto.CurrencyTypeDto;
import za.ac.nwu.as.domain.persistence.CurrencyType;
import za.ac.nwu.as.logic.flow.FetchCurrencyTypeFlow;
import za.ac.nwu.as.translator.CurrencyTypeTranslator;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Component
public class FetchCurrencyTypeFlowImpl implements FetchCurrencyTypeFlow {

    private final CurrencyTypeTranslator currencyTypeTranslator;

    @Autowired
    public FetchCurrencyTypeFlowImpl(CurrencyTypeTranslator currencyTypeTranslator) {
        this.currencyTypeTranslator = currencyTypeTranslator;
    }

    @Override
    public List<CurrencyTypeDto> fetchAllCurrencyTypes() {
        return currencyTypeTranslator.fetchAllCurrencyTypes();
    }

    @Override
    public CurrencyTypeDto saveCurrencyType(CurrencyTypeDto currencyTypeDto) {
        currencyTypeDto.setCurrencyTypeId(null);
        CurrencyType currencyType = currencyTypeTranslator.saveCurrencyType(currencyTypeDto);
        return new CurrencyTypeDto(currencyType);
    }
}
