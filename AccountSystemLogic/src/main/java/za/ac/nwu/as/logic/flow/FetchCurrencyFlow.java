package za.ac.nwu.as.logic.flow;

import za.ac.nwu.as.domain.dto.CurrencyDto;

public interface FetchCurrencyFlow {
    CurrencyDto fetchCurrencyById(Integer currencyId);
    CurrencyDto fetchCurrencyByMemberId(Integer memberId);
}
