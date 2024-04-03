package org.ursus.intro;

public class Account {
    private boolean active;
    private Address defaultDeliveryAddress;
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
