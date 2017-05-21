package com.example.service;

import com.example.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

    Random random = new Random();

    @Autowired
    ProductService productService;

    @Autowired
    NutritionService nutritionService;

    @Test
    public void testAddNutritionToProduct() {
        Product product = new Product();
        product.setName(Integer.toString(random.nextInt()));
        product.setCompany(Integer.toString(random.nextInt()));
//        productService.add(product);

//        Nutrition nutrition = NutritionDaoTest.createRandomNut();
//        nutrition.setProductId(product.getId());
//
//        nutritionService.add(nutrition);
//
//        product = productService.find(product.getId());
//
//        boolean nutFound = false;
//        for (Nutrition nut : product.getNutritions()) {
//            if (nut.equals(nutrition)) {
//                nutFound = true;
//                break;
//            }
//        }
//        Assert.assertTrue("We should have found it", nutFound);

    }


}
