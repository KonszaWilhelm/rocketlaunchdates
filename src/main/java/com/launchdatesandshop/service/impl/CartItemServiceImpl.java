package com.launchdatesandshop.service.impl;

import com.launchdatesandshop.entities.*;
import com.launchdatesandshop.exception.ResourceNotFoundException;
import com.launchdatesandshop.repositories.CartItemRepository;
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
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    UserRepository userRepository;

    public void addCartItem(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }

    @Override
    public void deleteCartItem(long id) {
        Optional<CartItem> cartItemDb = cartItemRepository.findById(id);

        if (cartItemDb.isPresent()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentPrincipalName = authentication.getName();
            User user = userRepository.findByEmail(currentPrincipalName);
            ShoppingCart shoppingCart = user.getShoppingCart();
            List<CartItem> cartItemList = shoppingCart.getCartItemList();
            cartItemList.remove(cartItemDb.get());
            shoppingCart.setCartItemList(cartItemList);
            this.cartItemRepository.delete(cartItemDb.get());

        } else {
            throw new ResourceNotFoundException("CartItem not found with id: " + id);
        }

    }

    public void removeAllCartItems() {
        cartItemRepository.deleteAll();
    }

    public CartItem getCartItemById(long id) {
        Optional<CartItem> cartItemDb = cartItemRepository.findById(id);
        if (cartItemDb.isPresent()) {
            return cartItemDb.get();
        } else {
            throw new RuntimeException("Cart Item id not found: " + id);
        }
    }

    @Override
    public CartItem addProductToCartItem(Product product, int quantity) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userRepository.findByEmail(currentPrincipalName);
        ShoppingCart shoppingCart = user.getShoppingCart();

        //check if prod already there
        List<CartItem> cartItemList = shoppingCart.getCartItemList();
        for (CartItem cartItem : cartItemList) {
            if (product.getIdProduct() == cartItem.getProduct().getIdProduct()) {
                cartItem.setQuantity(quantity + 1);
                cartItemRepository.delete(cartItem);
                return cartItemRepository.save(cartItem);
            }
        }


        CartItem cartItem = new CartItem();
        cartItem.setShoppingCart(user.getShoppingCart());
        cartItem.setProduct(product);

        cartItem.setQuantity(quantity);
        return cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem updateCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }
}

