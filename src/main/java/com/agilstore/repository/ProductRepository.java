package com.agilstore.repository;

import com.agilstore.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> listProducts() {
        return products;
    }

    public Product getProductById(String id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    public boolean updateProduct(String id, Product updatedProduct) {
        Product product = getProductById(id);
        if (product != null) {
            product.setName(updatedProduct.getName());
            product.setCategory(updatedProduct.getCategory());
            product.setQuantity(updatedProduct.getQuantity());
            product.setPrice(updatedProduct.getPrice());
            return true;
        }
        return false;
    }

    public boolean deleteProduct(String id) {
        Product product = getProductById(id);
        if (product != null) {
            products.remove(product);
            return true;
        }
        return false;
    }
}