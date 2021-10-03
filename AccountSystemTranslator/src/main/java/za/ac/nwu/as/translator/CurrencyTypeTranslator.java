package za.ac.nwu.as.translator;

import za.ac.nwu.as.domain.dto.CurrencyTypeDto;
import za.ac.nwu.as.domain.persistence.CurrencyType;

import java.util.List;

public interface CurrencyTypeTranslator {
    List<CurrencyTypeDto> fetchAllCurrencyTypes();
    CurrencyType saveCurrencyType(CurrencyTypeDto currencyType);
    CurrencyType fetchCurrencyTypeByName(String currencyTypeName);
    void deleteCurrencyTypeById(Integer ctId);
}
