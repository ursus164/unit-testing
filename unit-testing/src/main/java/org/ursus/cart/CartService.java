package org.ursus.cart;

import org.ursus.order.OrderStatus;

public class CartService {
    private CartHandler cartHandler;    // klasa mająca powiązanie / dependencje z CartHandler

    public CartService(CartHandler cartHandler) {
        this.cartHandler = cartHandler;
    }

    Cart processCart(Cart cart) {
        if(cartHandler.canHandleCart(cart)) {
            cartHandler.sendToPrepare(cart);
            cart.getOrders().forEach(order -> order.changeOrderStatus(OrderStatus.PREPARING));
            return cart;
        } else {
            cart.getOrders().forEach(order -> order.changeOrderStatus(OrderStatus.REJECTED));
            return  cart;
        }
    }
}
