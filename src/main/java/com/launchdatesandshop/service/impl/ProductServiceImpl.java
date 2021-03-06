package com.launchdatesandshop.service.impl;

import com.launchdatesandshop.entities.Product;
import com.launchdatesandshop.exception.ResourceNotFoundException;
import com.launchdatesandshop.repositories.ProductRepository;
import com.launchdatesandshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {



    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {

        return productRepository.save(product);

    }

    @Override
    public Product updateProduct(Product product) {

        Optional<Product> productDb = this.productRepository.findById(product.getIdProduct());

        if (productDb.isPresent()) {

            Product productUpdate = productDb.get();
            productUpdate.setIdProduct(product.getIdProduct());
            productUpdate.setProductName(product.getProductName());
            productUpdate.setProductDescription(product.getProductDescription());
            productUpdate.setProductPrice(product.getProductPrice());

            productRepository.save(productUpdate);
            return productUpdate;
        } else {
            throw new ResourceNotFoundException("Record/product not found with id: " + product.getIdProduct());
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();

    }

    @Override
    public Product getProductById(long idProduct) {
        Optional<Product> productDb = productRepository.findById(idProduct);

        if (productDb.isPresent()) {
            return productDb.get();
        } else {
            throw new ResourceNotFoundException("Record/product not found with id: " + idProduct);
        }

    }

    @Override
    public void deleteProduct(long idProduct) {
        Optional<Product> productDb = productRepository.findById(idProduct);

        if (productDb.isPresent()) {
            productRepository.delete(productDb.get());
        } else {
            throw new ResourceNotFoundException("Product not found with id: " + idProduct);
        }
    }
}
