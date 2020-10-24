package com.launchdatesandshop.controllers;

import com.launchdatesandshop.entities.Product;
import com.launchdatesandshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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
        // save employee to database
        productService.createProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {

        // get products from the service
        Product product = productService.getProductById(id);

        // set product as a model attribute to pre-populate the form
        model.addAttribute("product", product);
        return "update_product";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable (value = "id") long id) {

        // call delete product method
        this.productService.deleteProduct(id);
        return "redirect:/products";
    }

}
