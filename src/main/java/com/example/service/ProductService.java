package com.example.service;

import com.example.domain.Product;

import java.util.List;

public interface ProductService {
    void add(Product product);
    List<Product> findAll();
    Product find(int id);
    void update(Product product);
    void delete(int id);
    void add(List<Product> products);

}
