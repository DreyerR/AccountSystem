package za.ac.nwu.as.domain.persistence;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "MEMBER")
public class Member implements Serializable {

    private static final long serialVersionUID = 3119208079893242320L;

    private Long memberId;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String email;
    private String contactNr;
    private Set<Transaction> transactions;
    private Currency currency;

    public Member() {
    }

    public Member(Long memberId, String firstName, String lastName, LocalDate dob, String email, String contactNr) {
        this.memberId = memberId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.email = email;
        this.contactNr = contactNr;
    }

    @Id
    @SequenceGenerator(name = "MEMBER_ID_SEQ", sequenceName = "MEMBER_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_ID_SEQ")
    @Column(name = "MEMBER_ID")
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    @Column(name = "MEMBER_FIRSTNAME")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "MEMBER_LASTNAME")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "MEMBER_DOB")
    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    @Column(name = "MEMBER_EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "MEMBER_CONTACT_NR")
    public String getContactNr() {
        return contactNr;
    }

    public void setContactNr(String contactNr) {
        this.contactNr = contactNr;
    }

    @OneToMany(targetEntity = Transaction.class, fetch = FetchType.LAZY, mappedBy = "member", orphanRemoval = true,
    cascade = CascadeType.PERSIST)
    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CURRENCY_ID")
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
                email.equals(member.email) && contactNr.equals(member.contactNr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId, firstName, lastName, dob, email, contactNr);
    }
}
