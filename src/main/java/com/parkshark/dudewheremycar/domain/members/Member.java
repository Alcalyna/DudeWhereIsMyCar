
package com.parkshark.dudewheremycar.domain.members;

import com.parkshark.dudewheremycar.domain.exceptions.information.InvalidMemberInformationException;
import com.parkshark.dudewheremycar.domain.information.Address;
import com.parkshark.dudewheremycar.domain.information.EmailAddress;
import com.parkshark.dudewheremycar.domain.information.LicensePlate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "members")
public class Member {

    public Member() {
    }

    @Id
    @Column(name = "id")
    private UUID memberId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address")
    private Address address;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "membership_level")
    @Enumerated(EnumType.STRING)
    private MembershipLevel membershipLevel;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "email_address")
    private EmailAddress emailAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_license_plate")
    private LicensePlate licensePlate;

    public Member(MemberBuilder memberBuilder) {
        validateMemberInformation(memberBuilder);
        this.memberId = UUID.randomUUID();
        this.registrationDate = LocalDate.now();
        this.firstName = memberBuilder.firstName;
        this.lastName = memberBuilder.lastName;
        this.address = memberBuilder.address;
        this.phoneNumber = memberBuilder.phoneNumber;
        this.mobileNumber = memberBuilder.mobileNumber;
        this.licensePlate = memberBuilder.licensePlate;
        this.emailAddress = memberBuilder.emailAddress;
        this.membershipLevel = memberBuilder.membershipLevel;
        if (membershipLevel == null) {
            membershipLevel = MembershipLevel.BRONZE;
        }
    }

    public UUID getMemberId() {
        return memberId;
    }

    public void setMemberId(UUID memberId) {
        this.memberId = memberId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Address getAddress() {
        return address;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public EmailAddress getEmailAddress() {
        return emailAddress;
    }

    public LicensePlate getLicensePlate() {
        return licensePlate;
    }

    public MembershipLevel getMembershipLevel() {
        return membershipLevel;
    }

    private void validateMemberInformation(MemberBuilder memberBuilder) {
        if (memberBuilder.firstName == null) {
            throw new InvalidMemberInformationException("A member requires a first name");
        }
        if (memberBuilder.lastName == null) {
            throw new InvalidMemberInformationException("A member requires a last name");
        }
        if (memberBuilder.address == null) {
            throw new InvalidMemberInformationException("A member requires an address");
        }
        if (memberBuilder.phoneNumber == null) {
            throw new InvalidMemberInformationException("A member requires a phone number");
        }
        if (memberBuilder.mobileNumber == null) {
            throw new InvalidMemberInformationException("A member requires a mobile number");
        }
        if (memberBuilder.emailAddress == null) {
            throw new InvalidMemberInformationException("A member requires an email address");
        }
        if (memberBuilder.licensePlate == null) {
            throw new InvalidMemberInformationException("A member requires an license plate");
        }

    }

    public static final class MemberBuilder {
        private String firstName;
        private String lastName;
        private Address address;
        private String phoneNumber;
        private String mobileNumber;
        private LicensePlate licensePlate;
        private EmailAddress emailAddress;
        private MembershipLevel membershipLevel;

        MemberBuilder() {
        }

        public static MemberBuilder aMember() {
            return new MemberBuilder();
        }

        public MemberBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public MemberBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public MemberBuilder withAddress(Address address) {
            this.address = address;
            return this;
        }

        public MemberBuilder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public MemberBuilder withMobileNumber(String mobileNumber) {
            this.mobileNumber = mobileNumber;
            return this;
        }

        public MemberBuilder withEmailAddress(EmailAddress emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public MemberBuilder withLicensePlate(LicensePlate licensePlate) {
            this.licensePlate = licensePlate;
            return this;
        }

        public MemberBuilder withMembershipLevel(MembershipLevel membershipLevel) {
            this.membershipLevel = membershipLevel;
            return this;
        }

        public Member build() {
            return new Member(this);
        }
    }

}
