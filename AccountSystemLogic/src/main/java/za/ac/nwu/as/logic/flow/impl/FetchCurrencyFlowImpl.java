package za.ac.nwu.as.logic.flow.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(FetchCurrencyFlowImpl.class);

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
        LOGGER.info("Member ID to fetch: {}", memberId);
        Member member = memberTranslator.fetchMemberByIdPersist(memberId);

        if (null != member) {
//            LOGGER.info("Currency with ID {} returned", member.getCurrency().getCurrencyId());
            return new CurrencyDto(member.getCurrency());
        }

//        LOGGER.warn("Currency with member ID {} does not exist", memberId);
        return null;
    }
}
