package com.launchdatesandshop.service;

import com.launchdatesandshop.entities.*;
import com.launchdatesandshop.exception.ResourceNotFoundException;
import com.launchdatesandshop.repositories.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    CartItemRepository cartItemRepository;

    public void addCartItem(CartItem cartItem){
        cartItemRepository.save(cartItem);
    }

    @Override
    public void deleteCartItem(long id) {
        Optional<CartItem> cartItemDb = cartItemRepository.findById(id);
        if (cartItemDb.isPresent()) {
            this.cartItemRepository.delete(cartItemDb.get());
        } else {
            throw new ResourceNotFoundException("CartItem not found with id: " + id);
        }

    }

    public void removeAllCartItems(){
        cartItemRepository.deleteAll();
    }

    public CartItem getCartItemById(long id){
        Optional<CartItem> cartItemDb = cartItemRepository.findById(id);
        if (cartItemDb.isPresent()) {
            return cartItemDb.get();
        } else {
            throw new RuntimeException("Cart Item id not found: " + id);
        }
    }



}

