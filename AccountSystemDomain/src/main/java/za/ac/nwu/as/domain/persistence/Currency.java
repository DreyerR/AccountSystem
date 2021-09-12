package za.ac.nwu.as.domain.persistence;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "currency")
public class Currency implements Serializable {

    private static final long serialVersionUID = -6983561962347930257L;

    private Integer currencyId;
    private BigDecimal currencyAmount;
    private Member member;
    private CurrencyType currencyType;

    public Currency() {
    }

    public Currency(Integer currencyId, BigDecimal currencyAmount, Member member, CurrencyType currencyType) {
        this.currencyId = currencyId;
        this.currencyAmount = currencyAmount;
        this.member = member;
        this.currencyType = currencyType;
    }

    public Currency(BigDecimal currencyAmount, CurrencyType currencyType) {
        this.currencyAmount = currencyAmount;
        this.currencyType = currencyType;
    }

    public Currency(BigDecimal currencyAmount) {
        this.currencyAmount = currencyAmount;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "currency_id")
    public Integer getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }

    @Column(name = "currency_amount")
    public BigDecimal getCurrencyAmount() {
        return currencyAmount;
    }

    public void setCurrencyAmount(BigDecimal currencyAmount) {
        this.currencyAmount = currencyAmount;
    }

    @OneToOne(targetEntity = Member.class, mappedBy = "currency", fetch = FetchType.LAZY)
    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ct_id")
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
        Currency currency = (Currency) o;
        return Objects.equals(currencyId, currency.currencyId) &&
                Objects.equals(currencyAmount, currency.currencyAmount) &&
                Objects.equals(member, currency.member) && Objects.equals(currencyType, currency.currencyType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currencyId, currencyAmount, member, currencyType);
    }
}
