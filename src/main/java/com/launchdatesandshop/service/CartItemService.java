package com.launchdatesandshop.service;


import com.launchdatesandshop.entities.CartItem;
import com.launchdatesandshop.entities.ShoppingCart;

public interface CartItemService {
    void addCartItem(CartItem cartItem);

    void deleteCartItem(long id);

    void removeAllCartItems();

    CartItem getCartItemById(long id);

}
