package com.parkshark.dudewheremycar.domain.members;

import com.parkshark.dudewheremycar.domain.exceptions.InvalidMemberInformationException;
import com.parkshark.dudewheremycar.domain.information.Address;
import com.parkshark.dudewheremycar.domain.information.City;
import com.parkshark.dudewheremycar.domain.information.EmailAddress;
import com.parkshark.dudewheremycar.domain.information.LicensePlate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MemberTest {


    @Test
    void whenCreatingAMemberViaBuilder_then_IdAndDateAreAdded_AllFieldsComplete() {

        Member member = Member.MemberBuilder.aMember()
                .withFirstName("Jan")
                .withLastName("TestMan")
                .withAddress(new Address("testStreet", "8", new City("9000", "Gent")))
                .withPhoneNumber("092530082")
                .withMobileNumber("0476162018")
                .withLicensePlate(new LicensePlate("FGD564", "Norway"))
                .withEmailAddress(new EmailAddress("jan", "test"))
                .build();

        Assertions.assertEquals(member.getFirstName(), "Jan");
        Assertions.assertEquals(member.getLastName(), "TestMan");
        Assertions.assertEquals(member.getAddress().getStreetNumber(), "8");
        Assertions.assertNotNull(member.getMemberId());
        Assertions.assertEquals(member.getRegistrationDate(), LocalDate.now());
    }

    @Test
    void whenCreatingAMemberViaBuilder_withMissingParam_then_ThrowsInvalidMemberException() {
        assertThatThrownBy(() -> Member.MemberBuilder.aMember()
                .withFirstName("Jan")
                .withAddress(new Address("testStreet", "8", new City("9000", "Gent")))
                .withPhoneNumber("092530082")
                .withMobileNumber("0476162018")
                .withLicensePlate(new LicensePlate("FGD564", "Norway"))
                .withEmailAddress(new EmailAddress("jan", "test"))
                .build())
                .isInstanceOf(InvalidMemberInformationException.class)
                .hasMessage("A member requires a last name");
    }


}