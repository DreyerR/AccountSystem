package za.ac.nwu.as.domain.persistence;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "member")
public class Member implements Serializable {

    private static final long serialVersionUID = 3119208079893242320L;

    private Integer memberId;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String email;
    private String contactNr;
    private Set<Transaction> transactions;
    private Currency currency;

    public Member() {
    }

    public Member(Integer memberId, String firstName, String lastName, LocalDate dob, String email,
                  String contactNr, Set<Transaction> transactions, Currency currency) {
        this.memberId = memberId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.email = email;
        this.contactNr = contactNr;
        this.transactions = transactions;
        this.currency = currency;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    @Column(name = "member_firstname")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "member_lastname")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "member_dob")
    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    @Column(name = "member_email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "member_contact_nr")
    public String getContactNr() {
        return contactNr;
    }

    public void setContactNr(String contactNr) {
        this.contactNr = contactNr;
    }

    @OneToMany(targetEntity = Transaction.class, fetch = FetchType.LAZY, mappedBy = "member"/*, orphanRemoval = true,
    cascade = CascadeType.PERSIST*/)
    @JsonManagedReference
    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    @OneToOne(targetEntity = Currency.class, fetch = FetchType.LAZY/*, cascade = CascadeType.PERSIST*/)
    @JoinColumn(name = "currency_id", referencedColumnName = "currency_id")
    @JsonBackReference
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
        Member member = (Member) o;
        return memberId.equals(member.memberId) && firstName.equals(member.firstName) &&
                lastName.equals(member.lastName) && dob.equals(member.dob) &&
                email.equals(member.email) && Objects.equals(contactNr, member.contactNr) &&
                transactions.equals(member.transactions) && currency.equals(member.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId, firstName, lastName, dob, email, contactNr, transactions, currency);
    }
}
