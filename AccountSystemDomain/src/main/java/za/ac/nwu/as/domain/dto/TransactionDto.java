package za.ac.nwu.as.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import za.ac.nwu.as.domain.persistence.Member;
import za.ac.nwu.as.domain.persistence.Transaction;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class TransactionDto implements Serializable {

    private static final long serialVersionUID = 6772889418823834797L;

    private Integer transactionId;
    private LocalDate transactionDate;
    private String transactionChange;
    private MemberDto member;

    public TransactionDto() {
    }

    public TransactionDto(Integer transactionId, LocalDate transactionDate, String transactionChange, MemberDto member) {
        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.transactionChange = transactionChange;
        this.member = member;
    }

    public TransactionDto(Integer transactionId, LocalDate transactionDate, String transactionChange) {
        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.transactionChange = transactionChange;
    }

    public TransactionDto(LocalDate transactionDate, String transactionChange, MemberDto member) {
        this.transactionDate = transactionDate;
        this.transactionChange = transactionChange;
        this.member = member;
    }

    public TransactionDto(Transaction transaction) {
        this.transactionId = transaction.getTransactionId();
        this.transactionDate = transaction.getTransactionDate();
        this.transactionChange = transaction.getTransactionChange();
        if (null != transaction.getMember()) {
            this.member = new MemberDto(transaction.getMember());
        }
    }

    @JsonIgnore
    public Transaction buildTransaction(Member member) {
        return new Transaction(this.transactionDate, this.transactionChange, member);
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionChange() {
        return transactionChange;
    }

    public void setTransactionChange(String transactionChange) {
        this.transactionChange = transactionChange;
    }

    public MemberDto getMember() {
        return member;
    }

    public void setMember(MemberDto member) {
        this.member = member;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionDto that = (TransactionDto) o;
        return transactionId.equals(that.transactionId) &&
                transactionDate.equals(that.transactionDate) &&
                transactionChange.equals(that.transactionChange) && member.equals(that.member);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, transactionDate, transactionChange, member);
    }
}
