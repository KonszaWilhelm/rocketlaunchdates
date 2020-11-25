package com.launchdatesandshop.service;


import com.launchdatesandshop.entities.CartItem;
import com.launchdatesandshop.entities.Product;

public interface CartItemService {
    void addCartItem(CartItem cartItem);

    void deleteCartItem(long id);

    void removeAllCartItems();

    CartItem getCartItemById(long id);

    CartItem addProductToCartItem(Product product, int quantity);

    CartItem updateCartItem (CartItem cartItem);

}
