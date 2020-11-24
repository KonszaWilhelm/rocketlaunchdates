package com.launchdatesandshop.service;

import com.launchdatesandshop.entities.ShoppingCart;
import org.springframework.stereotype.Service;


public interface ShoppingCartService  {
    ShoppingCart updateShoppingCart(ShoppingCart shoppingCart);

    public ShoppingCart getShoppingCartById(Long id);

    public ShoppingCart save(ShoppingCart shoppingCart);

    public ShoppingCart update(ShoppingCart shoppingCart);


    public void delete(Long id);
}
