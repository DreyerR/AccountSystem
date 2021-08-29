package za.ac.nwu.as.domain.persistence;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "TRANSACTION")
public class Transaction implements Serializable {

    private static final long serialVersionUID = 8739548004412484066L;
    private Long transactionId;
    private LocalDate transactionDate;
    private String transactionChange;
    private BigDecimal transactionTotal;
    private Member member;
    private CurrencyType currencyType;

    public Transaction() {
    }

    public Transaction(Long transactionId, LocalDate transactionDate, String transactionChange,
                       BigDecimal transactionTotal, Member member, CurrencyType currencyType) {
        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.transactionChange = transactionChange;
        this.transactionTotal = transactionTotal;
        this.member = member;
        this.currencyType = currencyType;
    }

    @Id
    @SequenceGenerator(name = "TRANSACTION_ID_SEQ", sequenceName = "TRANSACTION_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRANSACTION_ID_SEQ")
    @Column(name = "TRANSACTION_ID")
    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    @Column(name = "TRANSACTION_DATE")
    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Column(name = "TRANSACTION_CHANGE")
    public String getTransactionChange() {
        return transactionChange;
    }

    public void setTransactionChange(String transactionChange) {
        this.transactionChange = transactionChange;
    }

    @Column(name = "TRANSACTION_TOTAL")
    public BigDecimal getTransactionTotal() {
        return transactionTotal;
    }

    public void setTransactionTotal(BigDecimal transactionTotal) {
        this.transactionTotal = transactionTotal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
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
        Transaction that = (Transaction) o;
        return transactionId.equals(that.transactionId) && transactionDate.equals(that.transactionDate) &&
                transactionChange.equals(that.transactionChange) && transactionTotal.equals(that.transactionTotal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, transactionDate, transactionChange, transactionTotal);
    }
}
