package za.ac.nwu.as.logic.flow.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.as.domain.dto.CurrencyDto;
import za.ac.nwu.as.domain.persistence.Member;
import za.ac.nwu.as.logic.flow.FetchCurrencyFlow;
import za.ac.nwu.as.translator.CurrencyTranslator;
import za.ac.nwu.as.translator.MemberTranslator;

import javax.transaction.Transactional;

@Transactional
@Component
public class FetchCurrencyFlowImpl implements FetchCurrencyFlow {

    private final CurrencyTranslator currencyTranslator;
    private final MemberTranslator memberTranslator;

    @Autowired
    public FetchCurrencyFlowImpl(CurrencyTranslator currencyTranslator, MemberTranslator memberTranslator) {
        this.currencyTranslator = currencyTranslator;
        this.memberTranslator = memberTranslator;
    }

    @Override
    public CurrencyDto fetchCurrencyById(Integer currencyId) {
        return currencyTranslator.fetchCurrencyById(currencyId);
    }

    @Override
    public CurrencyDto fetchCurrencyByMemberId(Integer memberId) {
        Member member = memberTranslator.fetchMemberByIdPersist(memberId);

        if (null != member) {
            return new CurrencyDto(member.getCurrency());
        }

        return null;
    }
}
