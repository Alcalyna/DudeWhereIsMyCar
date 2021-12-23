package com.parkshark.dudewheremycar.service.members;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.parkshark.dudewheremycar.domain.information.EmailAddress;
import com.parkshark.dudewheremycar.domain.information.LicensePlate;

import java.time.LocalDate;
import java.util.UUID;

@JsonDeserialize(builder = MemberSummaryDto.MemberSummaryDtoBuilder.class)
public class MemberSummaryDto {

    private UUID memberId;
    private String firstName;
    private String lastName;
    private LocalDate registrationDate;
    private String licensePlateNumber;
    private String phoneNumber;
    private EmailAddress emailAddress;


    public MemberSummaryDto(MemberSummaryDto.MemberSummaryDtoBuilder memberSummaryDtoBuilder) {
        this.memberId = memberSummaryDtoBuilder.memberId;
        this.firstName = memberSummaryDtoBuilder.firstName;
        this.lastName = memberSummaryDtoBuilder.lastName;
        this.registrationDate = memberSummaryDtoBuilder.registrationDate;
        this.phoneNumber = memberSummaryDtoBuilder.phoneNumber;
        this.licensePlateNumber = memberSummaryDtoBuilder.licensePlateNumber;
        this.emailAddress = memberSummaryDtoBuilder.emailAddress;
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

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public EmailAddress getEmailAddress() {
        return emailAddress;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    @JsonPOJOBuilder(withPrefix = "with")
    public static final class MemberSummaryDtoBuilder {
        private UUID memberId;
        private String firstName;
        private String lastName;
        private LocalDate registrationDate;
        private String licensePlateNumber;
        private String phoneNumber;
        private EmailAddress emailAddress;


        private MemberSummaryDtoBuilder() {
        }

        public static MemberSummaryDto.MemberSummaryDtoBuilder aMemberSummaryDtoBuilder() {
            return new MemberSummaryDto.MemberSummaryDtoBuilder();
        }

        public MemberSummaryDto.MemberSummaryDtoBuilder withMemberId(UUID memberId) {
            this.memberId = memberId;
            return this;
        }

        public MemberSummaryDto.MemberSummaryDtoBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public MemberSummaryDto.MemberSummaryDtoBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }


        public MemberSummaryDto.MemberSummaryDtoBuilder withRegistrationDate(LocalDate date) {
            this.registrationDate = date;
            return this;
        }

        public MemberSummaryDto.MemberSummaryDtoBuilder withLicensePlateNumber(String licensePlateNumber) {
            this.licensePlateNumber = licensePlateNumber;
            return this;
        }

        public MemberSummaryDto.MemberSummaryDtoBuilder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public MemberSummaryDto.MemberSummaryDtoBuilder withEmailAddress(EmailAddress emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public MemberSummaryDto build() {
            return new MemberSummaryDto(this);
        }
    }
}
