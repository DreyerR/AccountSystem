package za.ac.nwu.as.domain.persistence;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "transaction")
public class Transaction implements Serializable {

    private static final long serialVersionUID = 8739548004412484066L;
    private Integer transactionId;
    private LocalDate transactionDate;
    private String transactionChange;
    private BigDecimal transactionTotal;
    private Member member;
    private CurrencyType currencyType;

    public Transaction() {
    }

    public Transaction(Integer transactionId, LocalDate transactionDate, String transactionChange,
                       BigDecimal transactionTotal, Member member, CurrencyType currencyType) {
        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.transactionChange = transactionChange;
        this.transactionTotal = transactionTotal;
        this.member = member;
        this.currencyType = currencyType;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    @Column(name = "transaction_date")
    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Column(name = "transaction_change")
    public String getTransactionChange() {
        return transactionChange;
    }

    public void setTransactionChange(String transactionChange) {
        this.transactionChange = transactionChange;
    }

    @Column(name = "transaction_total")
    public BigDecimal getTransactionTotal() {
        return transactionTotal;
    }

    public void setTransactionTotal(BigDecimal transactionTotal) {
        this.transactionTotal = transactionTotal;
    }

    @ManyToOne(targetEntity = Member.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @JsonBackReference
    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @ManyToOne(targetEntity = CurrencyType.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "ct_id")
    @JsonBackReference
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
        return Objects.equals(transactionId, that.transactionId) &&
                Objects.equals(transactionDate, that.transactionDate) &&
                Objects.equals(transactionChange, that.transactionChange) &&
                Objects.equals(transactionTotal, that.transactionTotal) &&
                Objects.equals(member, that.member) && Objects.equals(currencyType, that.currencyType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, transactionDate, transactionChange, transactionTotal, member, currencyType);
    }
}
