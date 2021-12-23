package com.parkshark.dudewheremycar.service.members;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.parkshark.dudewheremycar.domain.information.Address;
import com.parkshark.dudewheremycar.domain.information.EmailAddress;
import com.parkshark.dudewheremycar.domain.information.LicensePlate;

import java.time.LocalDate;
import java.util.UUID;

@JsonDeserialize(builder = MemberDto.MemberDtoBuilder.class)
public class MemberDto {

    private UUID memberId;
    private String firstName;
    private String lastName;
    private Address address;
    private LocalDate registrationDate;
    private String phoneNumber;
    private LicensePlate licensePlate;
    private String mobileNumber;
    private EmailAddress emailAddress;


    public MemberDto(MemberDtoBuilder memberDtoBuilder) {
        this.memberId = memberDtoBuilder.memberId;
        this.firstName = memberDtoBuilder.firstName;
        this.lastName = memberDtoBuilder.lastName;
        this.address = memberDtoBuilder.address;
        this.registrationDate = memberDtoBuilder.registrationDate;
        this.phoneNumber = memberDtoBuilder.phoneNumber;
        this.licensePlate = memberDtoBuilder.licensePlate;
        this.mobileNumber = memberDtoBuilder.mobileNumber;
        this.emailAddress = memberDtoBuilder.emailAddress;
    }

    public UUID getMemberId() {
        return memberId;
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

    @JsonPOJOBuilder(withPrefix = "with")
    public static final class MemberDtoBuilder {
        private UUID memberId;
        private String firstName;
        private String lastName;
        private Address address;
        private LocalDate registrationDate;
        private String phoneNumber;
        private String mobileNumber;
        private LicensePlate licensePlate;
        private EmailAddress emailAddress;



        private MemberDtoBuilder() {
        }

        public static MemberDtoBuilder aMemberDtoBuilder() {
            return new MemberDtoBuilder();
        }

        public MemberDtoBuilder withMemberId(UUID memberId) {
            this.memberId = memberId;
            return this;
        }

        public MemberDtoBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public MemberDtoBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public MemberDtoBuilder withAddress(Address address) {
            this.address = address;
            return this;
        }

        public MemberDtoBuilder withRegistrationDate(LocalDate date) {
            this.registrationDate = date;
            return this;
        }

        public MemberDtoBuilder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public MemberDtoBuilder withMobileNumber(String mobileNumber) {
            this.mobileNumber = mobileNumber;
            return this;
        }

        public MemberDtoBuilder withEmailAddress(EmailAddress emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public MemberDtoBuilder withLicensePlate(LicensePlate licensePlate) {
            this.licensePlate = licensePlate;
            return this;
        }

        public MemberDto build() {
            return new MemberDto(this);
        }

    }
}
