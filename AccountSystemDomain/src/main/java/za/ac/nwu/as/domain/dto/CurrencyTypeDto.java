package za.ac.nwu.as.domain.dto;

import za.ac.nwu.as.domain.persistence.Currency;
import za.ac.nwu.as.domain.persistence.CurrencyType;
import za.ac.nwu.as.domain.persistence.Transaction;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

public class CurrencyTypeDto implements Serializable {

    private static final long serialVersionUID = 6088279328216216431L;

    private String currencyTypeName;
    private Set<Transaction> transactions;
    private Set<Currency> currencies;

    public CurrencyTypeDto() {
    }

    public CurrencyTypeDto(String currencyTypeName, Set<Transaction> transactions, Set<Currency> currencies) {
        this.currencyTypeName = currencyTypeName;
        this.transactions = transactions;
        this.currencies = currencies;
    }

    public CurrencyTypeDto(CurrencyType currencyType) {
        this.setCurrencyTypeName(currencyType.getCurrencyTypeName());
        this.setTransactions(currencyType.getTransactions());
        this.setCurrencies(currencyType.getCurrencies());
    }

    public String getCurrencyTypeName() {
        return currencyTypeName;
    }

    public void setCurrencyTypeName(String currencyTypeName) {
        this.currencyTypeName = currencyTypeName;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Set<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(Set<Currency> currencies) {
        this.currencies = currencies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyTypeDto that = (CurrencyTypeDto) o;
        return Objects.equals(currencyTypeName, that.currencyTypeName)
                && Objects.equals(transactions, that.transactions) && Objects.equals(currencies, that.currencies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currencyTypeName, transactions, currencies);
    }
}
