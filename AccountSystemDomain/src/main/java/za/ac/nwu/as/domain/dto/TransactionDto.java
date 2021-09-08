package za.ac.nwu.as.domain.dto;

import za.ac.nwu.as.domain.persistence.CurrencyType;
import za.ac.nwu.as.domain.persistence.Member;
import za.ac.nwu.as.domain.persistence.Transaction;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class TransactionDto implements Serializable {

    private static final long serialVersionUID = 6772889418823834797L;

    private LocalDate transactionDate;
    private String transactionChange;
    private BigDecimal transactionTotal;
    private Member member;
    private CurrencyType currencyType;

    public TransactionDto() {
    }

    public TransactionDto(LocalDate transactionDate, String transactionChange, BigDecimal transactionTotal,
                          Member member, CurrencyType currencyType) {
        this.transactionDate = transactionDate;
        this.transactionChange = transactionChange;
        this.transactionTotal = transactionTotal;
        this.member = member;
        this.currencyType = currencyType;
    }

    public TransactionDto(Transaction transaction) {
        this.setTransactionDate(transaction.getTransactionDate());
        this.setTransactionChange(transaction.getTransactionChange());
        this.setTransactionTotal(transaction.getTransactionTotal());
        this.setMember(transaction.getMember());
        this.setCurrencyType(transaction.getCurrencyType());
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

    public BigDecimal getTransactionTotal() {
        return transactionTotal;
    }

    public void setTransactionTotal(BigDecimal transactionTotal) {
        this.transactionTotal = transactionTotal;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

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
        TransactionDto that = (TransactionDto) o;
        return transactionDate.equals(that.transactionDate) &&
                transactionChange.equals(that.transactionChange) &&
                transactionTotal.equals(that.transactionTotal) &&
                member.equals(that.member) && currencyType.equals(that.currencyType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionDate, transactionChange, transactionTotal, member, currencyType);
    }
}
