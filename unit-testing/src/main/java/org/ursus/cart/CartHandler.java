package org.ursus.cart;

public interface CartHandler {
    boolean canHandleCart(Cart cart);
    // sending cart to reastaurant, then it will be checked if it is possible to prepare (true / false)
    void sendToPrepare(Cart cart);
}
