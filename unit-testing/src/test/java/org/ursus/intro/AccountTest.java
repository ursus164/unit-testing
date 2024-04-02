package org.ursus.intro;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;


public class AccountTest {
    @Test
    public void testAccountActive() {
        // given
        Account account = new Account();

        // then
        assertFalse(account.isActive());

//        Inna składnia pisania asercji
        assertThat(account.isActive(), equalTo(false));
        assertThat(account.isActive(), is(false));

    }

    @Test
    public void testAccountInactive() {
        // given
        Account account = new Account();

        // when
        account.activate();

        // then
        assertTrue(account.isActive());

        assertThat(account.isActive(), equalTo(true));
    }

    @Test
    public void newAccountShouldNotHaveDefaultDeliveryAddressSet() {
        // given
        Account account = new Account();

        // when
        Address address = account.getDefaultDeliveryAddress();

        // then
        assertNull(address);
        assertThat(address, equalTo(null));
    }

    @Test
    public void defaultDeliveryAddressShouldNotBeNullAfterBeingSet() {
        // given
        Account account = new Account();
        Address address = new Address("Krakowska", "Kraków", "30-303", "Poland");
        account.setDefaultDeliveryAddress(address);

        // when
        Address defaultAddress = account.getDefaultDeliveryAddress();

        // then
        assertNotNull(defaultAddress);
        assertThat(defaultAddress, is(notNullValue()));
    }


}
