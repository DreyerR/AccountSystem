package za.ac.nwu.as.domain.persistence;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "CURRENCY")
public class Currency implements Serializable {

    private static final long serialVersionUID = -6983561962347930257L;

    private Long currencyId;
    private BigDecimal currencyAmount;
    private Member member;
    private CurrencyType currencyType;

    public Currency() {
    }

    public Currency(Long currencyId, BigDecimal currencyAmount, Member member, CurrencyType currencyType) {
        this.currencyId = currencyId;
        this.currencyAmount = currencyAmount;
        this.member = member;
        this.currencyType = currencyType;
    }

    @Id
    @SequenceGenerator(name = "CURRENCY_ID_SEQ", sequenceName = "CURRENCY_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CURRENCY_ID_SEQ")
    @Column(name = "CURRENCY_ID")
    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    @Column(name = "CURRENCY_AMOUNT")
    public BigDecimal getCurrencyAmount() {
        return currencyAmount;
    }

    public void setCurrencyAmount(BigDecimal currencyAmount) {
        this.currencyAmount = currencyAmount;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CT_ID")
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
        return currencyId.equals(currency.currencyId) && currencyAmount.equals(currency.currencyAmount) && member.equals(currency.member) && currencyType.equals(currency.currencyType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currencyId, currencyAmount, member, currencyType);
    }
}
