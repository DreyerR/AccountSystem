package za.ac.nwu.as.domain.dto;

import com.sun.istack.internal.NotNull;
import za.ac.nwu.as.domain.persistence.Currency;
import za.ac.nwu.as.domain.persistence.CurrencyType;
import za.ac.nwu.as.domain.persistence.Member;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class CurrencyDto implements Serializable {

    private static final long serialVersionUID = 8044731264669617961L;

    private BigDecimal currencyAmount;
    private Member member;
    private CurrencyType currencyType;

    public CurrencyDto() {
    }

    public CurrencyDto(BigDecimal currencyAmount, Member member, CurrencyType currencyType) {
        this.currencyAmount = currencyAmount;
        this.member = member;
        this.currencyType = currencyType;
    }

    public CurrencyDto(Currency currency) {
        this.setCurrencyAmount(currency.getCurrencyAmount());
        this.setMember(currency.getMember());
        this.setCurrencyType(currency.getCurrencyType());
    }

    public BigDecimal getCurrencyAmount() {
        return currencyAmount;
    }

    public void setCurrencyAmount(BigDecimal currencyAmount) {
        this.currencyAmount = currencyAmount;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(CurrencyType currencyType) {
        this.currencyType = currencyType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyDto that = (CurrencyDto) o;
        return Objects.equals(currencyAmount, that.currencyAmount) && Objects.equals(member, that.member)
                && Objects.equals(currencyType, that.currencyType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currencyAmount, member, currencyType);
    }
}
