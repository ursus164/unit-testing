package org.ursus.cart;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.ursus.order.Order;
import org.ursus.order.OrderStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CartServiceTest {

    @Test
    void acceptedCartShouldBeSentToPrepare() {
        // checking if the cart with orders, that can be prepared by restaurant, is actually sent to realisation

        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        given(cartHandler.canHandleCart(cart)).willReturn(true);

        //when
        Cart resultCart = cartService.processCart(cart);

        // then
        // checking if the sendToPrepare() method was called after accepting the order by restaurant

        then(cartHandler).should().sendToPrepare(cart);

        InOrder inOrder = Mockito.inOrder(cartHandler);
        inOrder.verify(cartHandler).canHandleCart(cart);
        inOrder.verify(cartHandler).sendToPrepare(cart);

        assertThat(resultCart.getOrders(), hasSize(1));
        assertThat(resultCart.getOrders().get(0).getOrderStatus(), is(OrderStatus.PREPARING));
    }

    @Test
    void rejectedCartShouldNotBeSentToPrepare() {
        // checking if the cart with orders, that can be prepared by restaurant, is actually sent to realisation

        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        given(cartHandler.canHandleCart(any(Cart.class))).willReturn(false);

        //when
        Cart resultCart = cartService.processCart(cart);

        // then
        // checking if the sendToPrepare() method was called after accepting the order by restaurant
        verify(cartHandler, Mockito.never()).sendToPrepare(cart);

        assertThat(resultCart.getOrders(), hasSize(1));
        assertThat(resultCart.getOrders().get(0).getOrderStatus(), is(OrderStatus.REJECTED));
    }

    @Test
    void rejectedCartShouldNotBeSentToPrepareWithArgumentMatchers() {
        // checking if the cart with orders, that can be prepared by restaurant, is actually sent to realisation

        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        given(cartHandler.canHandleCart(cart)).willReturn(false);

        //when
        Cart resultCart = cartService.processCart(cart);

        // then
        // checking if the sendToPrepare() method was called after accepting the order by restaurant
        verify(cartHandler, Mockito.never()).sendToPrepare(cart);

        assertThat(resultCart.getOrders(), hasSize(1));
        assertThat(resultCart.getOrders().get(0).getOrderStatus(), is(OrderStatus.REJECTED));
    }

    @Test
    void acceptedCartShouldBeSentToPrepareWithArgumentCaptor() {
        // checking if the cart with orders, that can be prepared by restaurant, is actually sent to realisation

        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        ArgumentCaptor <Cart> argumentCaptor = ArgumentCaptor.forClass(Cart.class); // for capturing the argument

        given(cartHandler.canHandleCart(cart)).willReturn(true);

        //when
        Cart resultCart = cartService.processCart(cart);

        // then
        // checking if the sendToPrepare() method was called after accepting the order by restaurant

//        then(cartHandler).should().sendToPrepare(cart);
        then(cartHandler).should().sendToPrepare(argumentCaptor.capture());

        InOrder inOrder = Mockito.inOrder(cartHandler);
        inOrder.verify(cartHandler).canHandleCart(cart);
        inOrder.verify(cartHandler).sendToPrepare(cart);

        assertThat(argumentCaptor.getValue().getOrders().size(), equalTo(1));

        assertThat(resultCart.getOrders(), hasSize(1));
        assertThat(resultCart.getOrders().get(0).getOrderStatus(), is(OrderStatus.PREPARING));
    }
}
