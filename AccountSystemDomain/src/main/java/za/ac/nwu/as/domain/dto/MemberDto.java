package za.ac.nwu.as.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import za.ac.nwu.as.domain.persistence.Member;
import za.ac.nwu.as.domain.persistence.Currency;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class MemberDto implements Serializable {

    private static final long serialVersionUID = 1195600164163241730L;

    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String email;
    private String contactNr;

    public MemberDto() {
    }

    public MemberDto(String firstName, String lastName, LocalDate dob, String email, String contactNr) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.email = email;
        this.contactNr = contactNr;
    }

    public MemberDto(Member member) {
        this.firstName = member.getFirstName();
        this.lastName = member.getLastName();
        this.dob = member.getDob();
        this.email = member.getEmail();
        this.contactNr = member.getContactNr();
    }

    @JsonIgnore
    public Member buildMember(Currency currency) {
        return new Member(this.firstName, this.lastName, this.dob, this.email, this.contactNr, currency);
    }

    @JsonIgnore
    public Member buildMember() {
        return new Member(this.firstName, this.lastName, this.dob, this.email, this.contactNr);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberDto memberDto = (MemberDto) o;
        return firstName.equals(memberDto.firstName) && lastName.equals(memberDto.lastName) &&
                dob.equals(memberDto.dob) && email.equals(memberDto.email) &&
                Objects.equals(contactNr, memberDto.contactNr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, dob, email, contactNr);
    }
}
