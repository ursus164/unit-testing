package org.ursus.account;

public class Account {
    private boolean active;
    private Address defaultDeliveryAddress;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Invalid email address format");
        }
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    public boolean isActive() {
        return active;
    }

    public Account() {
        active = false;
    }

    public Account(Address defaultDeliveryAddress) {
        this.defaultDeliveryAddress = defaultDeliveryAddress;

        if(defaultDeliveryAddress != null) {
            activate();
        } else {
            active = false;
        }
    }

    public void activate() {
        active = true;
    }

    public Address getDefaultDeliveryAddress() {
        return defaultDeliveryAddress;
    }

    public void setDefaultDeliveryAddress(Address defaultDeliveryAddress) {
        this.defaultDeliveryAddress = defaultDeliveryAddress;
    }

}
