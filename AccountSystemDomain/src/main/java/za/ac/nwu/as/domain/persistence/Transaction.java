package za.ac.nwu.as.domain.persistence;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "transaction")
public class Transaction implements Serializable {

    private static final long serialVersionUID = 8739548004412484066L;
    private Integer transactionId;
    private LocalDate transactionDate;
    private String transactionChange;
    private Member member;

    public Transaction() {
    }

    public Transaction(Integer transactionId, LocalDate transactionDate, String transactionChange, Member member) {
        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.transactionChange = transactionChange;
        this.member = member;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return transactionId.equals(that.transactionId) && transactionDate.equals(that.transactionDate) &&
                transactionChange.equals(that.transactionChange) && member.equals(that.member);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, transactionDate, transactionChange, member);
    }
}
