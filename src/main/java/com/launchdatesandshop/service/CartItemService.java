package com.launchdatesandshop.service;

import com.launchdatesandshop.entities.CartItem;
import com.launchdatesandshop.entities.ShoppingCart;

import java.util.List;

public interface CartItemService {
    public List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);
}
