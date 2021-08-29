package za.ac.nwu.as.domain.persistence;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "CURRENCY_TYPE")
public class CurrencyType implements Serializable {

    private static final long serialVersionUID = 3728128313540771856L;

    private Long currencyTypeId;
    private String currencyTypeName;
    private Set<Transaction> transactions;
    private Set<Currency> currencies;

    public CurrencyType() {
    }

    public CurrencyType(Long currencyTypeId, String currencyTypeName) {
        this.currencyTypeId = currencyTypeId;
        this.currencyTypeName = currencyTypeName;
    }

    @Id
    @SequenceGenerator(name = "CT_ID_SEQ", sequenceName = "CT_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CT_ID_SEQ")
    @Column(name = "CT_ID")
    public Long getCurrencyTypeId() {
        return currencyTypeId;
    }

    public void setCurrencyTypeId(Long currencyTypeId) {
        this.currencyTypeId = currencyTypeId;
    }

    @Column(name = "CT_NAME")
    public String getCurrencyTypeName() {
        return currencyTypeName;
    }

    public void setCurrencyTypeName(String currencyTypeName) {
        this.currencyTypeName = currencyTypeName;
    }

    @OneToMany(targetEntity = Transaction.class, fetch = FetchType.LAZY, mappedBy = "currencyType", orphanRemoval = true,
            cascade = CascadeType.PERSIST)
    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    @OneToMany(targetEntity = Currency.class, fetch = FetchType.LAZY, mappedBy = "currencyType", orphanRemoval = true,
            cascade = CascadeType.PERSIST)
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
        CurrencyType that = (CurrencyType) o;
        return currencyTypeId.equals(that.currencyTypeId) && currencyTypeName.equals(that.currencyTypeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currencyTypeId, currencyTypeName);
    }
}
