package za.ac.nwu.as.logic.flow;

import za.ac.nwu.as.domain.dto.CurrencyTypeDto;

import java.util.List;

public interface FetchCurrencyTypeFlow {
    List<CurrencyTypeDto> fetchAllCurrencyTypes();
    CurrencyTypeDto saveCurrencyType(CurrencyTypeDto currencyTypeDto);
}
