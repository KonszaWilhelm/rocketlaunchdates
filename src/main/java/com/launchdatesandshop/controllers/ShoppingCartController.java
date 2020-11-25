package com.launchdatesandshop.controllers;

import com.launchdatesandshop.entities.*;
import com.launchdatesandshop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;


@Controller

public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    CartItemService cartItemService;


    @GetMapping("/shoppingCart")
    public String viewHomePage(Model model, Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        ShoppingCart shoppingCart = user.getShoppingCart();
        model.addAttribute("shoppingCart", shoppingCart);
        model.addAttribute("cartItemList", shoppingCart.getCartItemList());

        return "shopping_cart";
    }


    @RequestMapping("/addToCart/{id}")
    public ModelAndView addToShoppingCart(@PathVariable(value = "id") long id, Principal principal, ModelAndView modelAndView) {
        Product product = productService.getProductById(id);
        CartItem cartItem = cartItemService.addProductToCartItem(product, 1);

        User user = userService.getUserByEmail(principal.getName());
        ShoppingCart shoppingCart = user.getShoppingCart();
        List<CartItem> cartItemList = shoppingCart.getCartItemList();

        if (product.getProductCount() > 0) {
            cartItemList.add(cartItem);
            product.setProductCount(product.getProductCount() - 1);
        } else
            modelAndView.addObject("notEnoughStock", true);

        shoppingCartService.update(cartItemList);
        modelAndView.setViewName("redirect:/shoppingCart");
        return modelAndView;
    }

    @GetMapping("/deleteCartItem/{id}")
    public String deleteCartItem(@PathVariable(value = "id") Long id) {
        cartItemService.deleteCartItem(id);
        return "redirect:/shoppingCart";
    }

    @PostMapping("/updateQuantity/{id}")
    public String updateCartItemQuantity(@PathVariable(value = "id") long id, @RequestParam("quantity") int quantity) {
        //@ModelAttribute("id") Long cartItemId,
        CartItem cartItem = cartItemService.getCartItemById(id);
        cartItem.setQuantity(quantity);
        cartItemService.updateCartItem(cartItem);
        return "redirect:/shoppingCart";
    }


}



