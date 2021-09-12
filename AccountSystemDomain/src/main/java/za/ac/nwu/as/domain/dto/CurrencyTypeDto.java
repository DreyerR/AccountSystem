package za.ac.nwu.as.domain.dto;

import za.ac.nwu.as.domain.persistence.CurrencyType;

import java.io.Serializable;
import java.util.Objects;

public class CurrencyTypeDto implements Serializable {

    private static final long serialVersionUID = 6088279328216216431L;

    private Integer currencyTypeId;
    private String currencyTypeName;

    public CurrencyTypeDto() {
    }

    public CurrencyTypeDto(Integer currencyTypeId, String currencyTypeName) {
        this.currencyTypeId = currencyTypeId;
        this.currencyTypeName = currencyTypeName;
    }

    public CurrencyTypeDto(CurrencyType currencyType) {
        this.currencyTypeId = currencyType.getCurrencyTypeId();
        this.currencyTypeName = currencyType.getCurrencyTypeName();
    }

    public Integer getCurrencyTypeId() {
        return currencyTypeId;
    }

    public void setCurrencyTypeId(Integer currencyTypeId) {
        this.currencyTypeId = currencyTypeId;
    }

    public String getCurrencyTypeName() {
        return currencyTypeName;
    }

    public void setCurrencyTypeName(String currencyTypeName) {
        this.currencyTypeName = currencyTypeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyTypeDto that = (CurrencyTypeDto) o;
        return currencyTypeName.equals(that.currencyTypeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currencyTypeName);
    }
}
