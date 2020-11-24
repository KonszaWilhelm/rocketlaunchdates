package com.launchdatesandshop.controllers;

import com.launchdatesandshop.entities.*;
import com.launchdatesandshop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    CartItemService cartItemService;



    @GetMapping("/")
    public String viewHomePage(Model model, Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCartById(user.getShoppingCart().getId());
        model.addAttribute("shoppingCart", shoppingCart);
        model.addAttribute("cartItemList", shoppingCart.getCartItemList());

        return "shopping_cart";
    }


    @GetMapping("/addToCart/{id}")
    public String addToShoppingCart(@PathVariable(value = "id") long id, Principal principal) {
        Product product = productService.getProductById(id);
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(1);
        cartItemService.addCartItem(cartItem);

        User user = userService.getUserByEmail(principal.getName());

        ShoppingCart shoppingCart = shoppingCartService.getShoppingCartById(user.getShoppingCart().getId());
        List<CartItem> cartItemList = shoppingCart.getCartItemList();
        cartItemList.add(cartItem);
        return "redirect:/shoppingCart/";
    }

}



