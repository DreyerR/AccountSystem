package za.ac.nwu.as.logic.flow;

import za.ac.nwu.as.domain.dto.CurrencyTypeDto;

public interface CreateCurrencyTypeFlow {
    CurrencyTypeDto saveCurrencyType(CurrencyTypeDto currencyTypeDto);
}
