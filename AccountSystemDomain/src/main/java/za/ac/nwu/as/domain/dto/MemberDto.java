package za.ac.nwu.as.domain.dto;

import za.ac.nwu.as.domain.persistence.Currency;
import za.ac.nwu.as.domain.persistence.Member;
import za.ac.nwu.as.domain.persistence.Transaction;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public class MemberDto implements Serializable {

    private static final long serialVersionUID = 1195600164163241730L;

    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String email;
    private String contactNr;
    private Set<Transaction> transactions;
    private Currency currency;

    public MemberDto() {
    }

    public MemberDto(String firstName, String lastName, LocalDate dob, String email, String contactNr,
                     Set<Transaction> transactions, Currency currency) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.email = email;
        this.contactNr = contactNr;
        this.transactions = transactions;
        this.currency = currency;
    }

    public MemberDto(Member member) {
        this.setFirstName(member.getFirstName());
        this.setLastName(member.getLastName());
        this.setDob(member.getDob());
        this.setEmail(member.getEmail());
        this.setContactNr(member.getContactNr());
        this.setTransactions(member.getTransactions());
        this.setCurrency(member.getCurrency());
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNr() {
        return contactNr;
    }

    public void setContactNr(String contactNr) {
        this.contactNr = contactNr;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberDto memberDto = (MemberDto) o;
        return Objects.equals(firstName, memberDto.firstName) && Objects.equals(lastName, memberDto.lastName)
                && Objects.equals(dob, memberDto.dob) && Objects.equals(email, memberDto.email)
                && Objects.equals(contactNr, memberDto.contactNr)
                && Objects.equals(transactions, memberDto.transactions) && Objects.equals(currency, memberDto.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, dob, email, contactNr, transactions, currency);
    }
}
