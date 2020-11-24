package com.launchdatesandshop.controllers;

import com.launchdatesandshop.entities.Product;
import com.launchdatesandshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    // display list of prods
    @GetMapping("/products")
    public String viewHomePage(Model model) {
        model.addAttribute("listProducts", productService.getAllProducts());
        return "product";
    }

    @GetMapping("/showNewProductForm")
    public String showNewProductForm(Model model) {
        // create model attribute to bind form data
        Product product = new Product();
        model.addAttribute("product", product);
        return "new_product";
    }

    @PostMapping("**/createProduct")
    public String createProduct(@ModelAttribute("product") Product product) {
        // save product to database
        productService.createProduct(product);
        return "redirect:/products";
    }

    //try mv instead of model
    @GetMapping("/showFormForUpdate/{id}")
    public ModelAndView showFormForUpdate(@PathVariable(value = "id") long id, ModelAndView modelAndView) {
        // get product from the service
        Product product = productService.getProductById(id);

        // set product as a model / mv attribute to pre-populate the form
        modelAndView.addObject("product", product);
        //model.addAttribute("product", product);

        // set view which we return
        modelAndView.setViewName("update_product");

        return modelAndView;
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable(value = "id") long id) {

        // call delete product method
        this.productService.deleteProduct(id);
        return "redirect:/products";
    }

}
