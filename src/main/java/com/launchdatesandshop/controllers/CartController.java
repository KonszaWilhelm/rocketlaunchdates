package com.launchdatesandshop.controllers;

import com.launchdatesandshop.entities.CartItem;
import com.launchdatesandshop.entities.ShoppingCart;
import com.launchdatesandshop.entities.User;
import com.launchdatesandshop.repositories.UserRepository;
import com.launchdatesandshop.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/shoppingCart")
public class CartController {

    @Autowired
    UserRepository userRepository;

//    @Autowired
//    CartItemService cartItemService;
//
//    @RequestMapping("/cart")
//    public String shoppingCart(Model model, Principal principal) {
//        User user = userRepository.findByEmail(principal.getName());
//        ShoppingCart shoppingCart = user.getShoppingCart();
//
//        List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
//    }
}
