package com.example.dao;

import com.example.domain.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductDaoTest {
    Random random = new Random();

    @Autowired
    ProductDao productDao;
    @Autowired
    NutritionDao nutritionDao;


    //Ready to test --
    @Test
    public void testCreate() {
        Product product = createProduct();

        List<Product> products = productDao.findAll();
        Assert.assertNotNull(products);
        Assert.assertTrue(products.size() > 0);

        Product foundProduct = null;
        foundProduct = productDao.find(product.getId());

        System.out.println("\n\n\n" + product);
        System.out.println("\n\n\n" + foundProduct);
        Assert.assertTrue("Mismatch in list!! " + product, product.equals(foundProduct));
    }

    @Test
    public void testFind() {
        Product product = createProduct();

        Product foundProd = null;
        foundProd = productDao.find(product.getId());

        Product comparisonProd = productDao.find(foundProd.getId());
        Assert.assertTrue("Errr, they should be the same", foundProd.equals(comparisonProd));
    }

    //Ready to Test --
    @Test
    public void testDelete() {
        Product product = createProduct();
        Product foundProd = null;
        foundProd = productDao.find(product.getId());

        Assert.assertNotNull(foundProd);
        productDao.delete(foundProd.getId());
        Assert.assertNull("Should not find anything.. ", productDao.find(foundProd.getId()));
    }

//    //Leave for later
//    @Test
//    public void testUpdate() {
//        Nutrition nutrition = createNut();
//        Nutrition foundNut = findNutritionInList(nutrition);
//        Assert.assertNotNull(foundNut);
//
//        //Scramble some fields.
//        foundNut.setCarbs(random.nextInt());
//        foundNut.setCalories(random.nextInt());
//
//        nutritionDao.update(foundNut);
//
//        Nutrition foundNut2 = findNutritionInList(foundNut);
//        Assert.assertEquals(foundNut, foundNut2);
//    }


    //Leave for later
//    @Test
//    public void multiNewTest() {
//        Nutrition nutrition = createNut();
//        Nutrition nutrition2 = createNut();
//        Assert.assertFalse("Shoulda have been different...", nutrition.equals(nutrition2));
//    }


//    //DRY Methods
//    private Nutrition findNutritionInList(Nutrition nutrition) {
//        List<Nutrition> nutritions = nutritionDao.findAll();
//        for (Nutrition nut : nutritions) {
//            if (nut.equals(nutrition)) {
//                return nut;
//            }
//        }
//        return null;
//    }

    //Create product  -- Ready
    private Product createProduct() {
        Product product = new Product();

        String name = Integer.toString(random.nextInt());
        String company = Integer.toString(random.nextInt());

        productDao.add(product);
        return product;
    }


}
