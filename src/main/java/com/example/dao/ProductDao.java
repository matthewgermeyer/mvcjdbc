package com.example.dao;

import com.example.domain.Product;

import java.util.List;

public interface ProductDao {

    void add(Product product);
    void delete(int id);
    List<Product> findAll();
    Product find(int id);

}
