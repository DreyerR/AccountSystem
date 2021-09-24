package za.ac.nwu.as.translator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.as.domain.persistence.Member;
import za.ac.nwu.as.repo.persistence.CurrencyRepository;
import za.ac.nwu.as.translator.CurrencyTranslator;
import za.ac.nwu.as.translator.MemberTranslator;

import java.math.BigDecimal;

@Component
public class CurrencyTranslatorImpl implements CurrencyTranslator {

    private final CurrencyRepository currencyRepository;
    private final MemberTranslator memberTranslator;

    @Autowired
    public CurrencyTranslatorImpl(CurrencyRepository currencyRepository, MemberTranslator memberTranslator) {
        this.currencyRepository = currencyRepository;
        this.memberTranslator = memberTranslator;
    }

    @Override
    public boolean addCurrency(Integer memberId, BigDecimal amount) {
        try {
            Member doesMemberExist = memberTranslator.doesMemberExist(memberId);
            if (null != doesMemberExist) {
                Integer currencyId = doesMemberExist.getCurrency().getCurrencyId();
                int success = currencyRepository.addCurrency(currencyId, amount);
                return success == 1;
            }
            return false;
        }
        catch (Exception e) {
            throw new RuntimeException("CurrencyTranslator: Unable to update currency", e);
        }
    }
}
