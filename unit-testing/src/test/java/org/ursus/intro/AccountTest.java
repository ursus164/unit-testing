package org.ursus.intro;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;


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

//    Repairing in Jacoco - possible task for presentation
    @Test
    public void accountShouldNotBeActiveWhenCreatedWithNullAddress() {
        // given
        Address address = null;

        // when
        Account account = new Account(address);

        // then
        assertThat(account.isActive(), is(false));
    }

    @Test
    void invalidEmailShouldThrowException() {
        // given
        Account account = new Account();

        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> account.setEmail("invalidEmail"));
    }

//    Second code coverage repair - possible task
    @Test
    void validEmailShouldBeSet() {
        // given
        Account account = new Account();

        // when
        account.setEmail("kontakt@example.com");
        assertThat(account.getEmail(), is("kontakt@example.com"));
    }

    @Test
    void newAccountWithNotNullAddressShouldBeActive() {
        // given
        Address address = new Address("Krakowska", "Kraków", "30-303", "Poland");

        // when
        Account account = new Account(address);

        // then
        assumingThat(address != null, () -> {
            assertTrue(account.isActive());
            assertThat(account.isActive(), is(true));
        });
    }

}
