package com.launchdatesandshop.service;

import com.launchdatesandshop.entities.Product;
import com.launchdatesandshop.exception.ResourceNotFoundException;
import com.launchdatesandshop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
//You can skip Transactional as it is handled internally
public class ProductServiceImpl implements ProductService {

    //Injecting Product Repository should change it to constructor injection later

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {

        return productRepository.save(product);

    }

    @Override
    public Product updateProduct(Product product) {
        //fetching record from DB
        Optional<Product> productDb = this.productRepository.findById(product.getIdProduct());

        if (productDb.isPresent()) {
            // getting product obj from Optional
            Product productUpdate = productDb.get();
            productUpdate.setIdProduct(product.getIdProduct());
            productUpdate.setProductName(product.getProductName());
            productUpdate.setProductDescription(product.getProductDescription());
            productUpdate.setProductPrice(product.getProductPrice());
            //saving the entity
            productRepository.save(productUpdate);
            return productUpdate;
        } else {
            throw new ResourceNotFoundException("Record/product not found with id: " + product.getIdProduct());
        }
    }

    @Override
    public List<Product> getAllProducts() {
//        return this.productRepository.findAll();
        return productRepository.findAll();

    }

    @Override
    public Product getProductById(long idProduct) {
        Optional<Product> productDb = this.productRepository.findById(idProduct);

        if (productDb.isPresent()) {
            return productDb.get();
        } else {
            throw new ResourceNotFoundException("Record/product not found with id: " + idProduct);
        }

    }

    @Override
    public void deleteProduct(long idProduct) {
        Optional<Product> productDb = this.productRepository.findById(idProduct);

        if (productDb.isPresent()) {
            this.productRepository.delete(productDb.get());
        } else {
            throw new ResourceNotFoundException("Product not found with id: " + idProduct);
        }
    }
}
