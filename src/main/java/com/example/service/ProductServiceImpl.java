package com.example.service;

import com.example.dao.NutritionDao;
import com.example.dao.ProductDao;
import com.example.domain.Nutrition;
import com.example.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;

    @Autowired
    NutritionDao nutritionDao;

    @Override
    public void add(Product product) {
        productDao.add(product);

    }

    @Override
    public List<Product> findAll()
    {
        return productDao.findAll();
    }

    @Override
    public Product find(int id) {
        Product product = productDao.find(id);
        List<Nutrition> nutritions = nutritionDao.findByProductId(id);
        product.setNutritions(nutritions);

        return product;
    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void add(List<Product> products) {

    }
}
