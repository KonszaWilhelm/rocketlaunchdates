package com.launchdatesandshop.service;

import com.launchdatesandshop.entities.CartItem;
import com.launchdatesandshop.entities.Launch;
import com.launchdatesandshop.entities.Order;
import com.launchdatesandshop.entities.ShoppingCart;
import com.launchdatesandshop.exception.ResourceNotFoundException;
import com.launchdatesandshop.repositories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    CartItemService cartItemService;

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Override
    public ShoppingCart updateShoppingCart(ShoppingCart cart) {
        Long cartId = cart.getId();
//        double grandTotal = orderService.getCustomerOrderGrandTotal(cartId);
//        cart.setGrandTotal(grandTotal);

        return shoppingCartRepository.save(cart);
    }

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
    public ShoppingCart update(ShoppingCart shoppingCart) {
        return null;
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
