package com.launchdatesandshop.service.impl;

import com.launchdatesandshop.entities.*;
import com.launchdatesandshop.exception.ResourceNotFoundException;
import com.launchdatesandshop.repositories.ShoppingCartRepository;
import com.launchdatesandshop.repositories.UserRepository;
import com.launchdatesandshop.service.CartItemService;
import com.launchdatesandshop.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    CartItemService cartItemService;

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    UserRepository userRepository;


    @Override
    public ShoppingCart getShoppingCartById(Long id) {
        Optional<ShoppingCart> cartDb = this.shoppingCartRepository.findById(id);
        if (cartDb.isPresent()) {
            return cartDb.get();
        } else {
            throw new RuntimeException("Cart not found: " + id);
        }
    }

    @Override
    public ShoppingCart save(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart update(List<CartItem> cartItemList) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userRepository.findByEmail(currentPrincipalName);
        ShoppingCart shoppingCart = user.getShoppingCart();
        shoppingCart.setCartItemList(cartItemList);
        shoppingCartRepository.save(shoppingCart);
        return shoppingCart;
    }

    @Override
    public void delete(Long id) {
        Optional<ShoppingCart> cartDb = this.shoppingCartRepository.findById(id);
        if (cartDb.isPresent()) {
            this.shoppingCartRepository.delete(cartDb.get());
        } else {
            throw new ResourceNotFoundException("Cart not found with id: " + id);
        }
    }
}
