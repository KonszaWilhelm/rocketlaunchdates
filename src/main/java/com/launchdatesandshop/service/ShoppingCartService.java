package com.launchdatesandshop.service;

import com.launchdatesandshop.entities.CartItem;
import com.launchdatesandshop.entities.ShoppingCart;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ShoppingCartService {


    public ShoppingCart getShoppingCartById(Long id);

    public ShoppingCart save(ShoppingCart shoppingCart);

    public ShoppingCart update(List<CartItem> cartItemList);


    public void delete(Long id);
}
