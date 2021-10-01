package za.ac.nwu.as.logic.flow.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.as.domain.dto.CurrencyTypeDto;
import za.ac.nwu.as.domain.persistence.CurrencyType;
import za.ac.nwu.as.logic.flow.CreateCurrencyTypeFlow;
import za.ac.nwu.as.translator.CurrencyTypeTranslator;

import javax.transaction.Transactional;

@Transactional
@Component
public class CreateCurrencyTypeFlowImpl implements CreateCurrencyTypeFlow {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateCurrencyTypeFlowImpl.class);

    private final CurrencyTypeTranslator currencyTypeTranslator;

    @Autowired
    public CreateCurrencyTypeFlowImpl(CurrencyTypeTranslator currencyTypeTranslator) {
        this.currencyTypeTranslator = currencyTypeTranslator;
    }

    @Override
    public CurrencyTypeDto saveCurrencyType(CurrencyTypeDto currencyTypeDto) {
        currencyTypeDto.setCurrencyTypeId(null);
        LOGGER.info("Save new CurrencyType: {}", currencyTypeDto);
        CurrencyType currencyType = currencyTypeTranslator.saveCurrencyType(currencyTypeDto);
        LOGGER.info("Saved new CurrencyType with ID {}", currencyType.getCurrencyTypeId());
        return new CurrencyTypeDto(currencyType);
    }
}
