package za.ac.nwu.as.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import za.ac.nwu.as.domain.persistence.Currency;
import za.ac.nwu.as.domain.persistence.CurrencyType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class CurrencyDto implements Serializable {

    private static final long serialVersionUID = 8044731264669617961L;

    private BigDecimal currencyAmount;
    private String currencyTypeName;
    private MemberDto memberDto;

    public CurrencyDto() {
    }

    public CurrencyDto(BigDecimal currencyAmount, String currencyTypeName) {
        this.currencyAmount = currencyAmount;
        this.currencyTypeName = currencyTypeName;
    }

    public CurrencyDto(BigDecimal currencyAmount, CurrencyTypeDto currencyType, MemberDto memberDto) {
        this.currencyAmount = currencyAmount;
        this.currencyTypeName = currencyType.getCurrencyTypeName();
        this.memberDto = memberDto;
    }

    public CurrencyDto(Currency currency) {
        this.currencyAmount = currency.getCurrencyAmount();
        this.currencyTypeName = currency.getCurrencyType().getCurrencyTypeName();
        if (null != currency.getMember()) {
            this.memberDto = new MemberDto(currency.getMember());
        }
    }

    @JsonIgnore
    public Currency buildCurrency(CurrencyType currencyType) {
        return new Currency(this.currencyAmount, currencyType);
    }

    @JsonIgnore
    public Currency buildCurrency() {
        return new Currency(this.currencyAmount);
    }

    public BigDecimal getCurrencyAmount() {
        return currencyAmount;
    }

    public void setCurrencyAmount(BigDecimal currencyAmount) {
        this.currencyAmount = currencyAmount;
    }

    public String getCurrencyTypeName() {
        return currencyTypeName;
    }

    public void setCurrencyTypeName(String currencyTypeName) {
        this.currencyTypeName = currencyTypeName;
    }

    public MemberDto getMemberDto() {
        return memberDto;
    }

    public void setMemberDto(MemberDto memberDto) {
        this.memberDto = memberDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyDto that = (CurrencyDto) o;
        return currencyAmount.equals(that.currencyAmount) && currencyTypeName.equals(that.currencyTypeName) &&
                Objects.equals(memberDto, that.memberDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currencyAmount, currencyTypeName, memberDto);
    }
}
