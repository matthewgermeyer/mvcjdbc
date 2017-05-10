package com.example.service;

import com.example.domain.Nutrition;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NutritionServiceTest {

    Random random = new Random();

    @Autowired
    NutritionService nutritionService;

    @Test
    public void testCreate() {
        Nutrition nutrition = new Nutrition();

        String product = Integer.toString(random.nextInt());
        nutrition.setProduct(product);

        int calories = random.nextInt();
        nutrition.setCalories(calories);

        int carbs = random.nextInt();
        nutrition.setCarbs(carbs);

        nutritionService.add(nutrition);

        List<Nutrition> nutritions = nutritionService.findAll();
        Assert.assertNotNull(nutritions);
        Assert.assertTrue(nutritions.size() > 0);
        boolean found = false;
        for (Nutrition nut : nutritions) {
            if (nut.equals(nutrition)) {
                found = true;
                break;
            }
        }

        Assert.assertTrue("Could not find " + nutrition, found);
    }

    @Test
    public void testFind() {
        Nutrition nutrition = new Nutrition();

        String product = Integer.toString(random.nextInt());
        nutrition.setProduct(product);

        int calories = random.nextInt();
        nutrition.setCalories(calories);

        int carbs = random.nextInt();
        nutrition.setCarbs(carbs);

        //creates a row in the db
        nutritionService.add(nutrition);

        //return back a List of the db entries...
        List<Nutrition> nutritions = nutritionService.findAll();
        Assert.assertNotNull(nutritions);
        Assert.assertTrue(nutritions.size() > 0);
        Nutrition foundNutrition = null;
        for (Nutrition nut : nutritions) {
            if (nut.equals(nutrition)) {
                foundNutrition = nut;
                break;
            }
        }

        Nutrition comparisonNut = nutritionService.find(foundNutrition.getId());
        Assert.assertTrue("Errr, they should be the same", foundNutrition.equals(comparisonNut) );
    }

    @Test
    public void testDelete(){
        Nutrition nutrition = new Nutrition();

        String product = Integer.toString(random.nextInt());
        nutrition.setProduct(product);

        int calories = random.nextInt();
        nutrition.setCalories(calories);

        int carbs = random.nextInt();
        nutrition.setCarbs(carbs);

        nutritionService.add(nutrition);

        List<Nutrition> nutritions = nutritionService.findAll();
        Assert.assertNotNull(nutritions);
        Assert.assertTrue(nutritions.size() > 0);
        Nutrition foundNut = null;
        for (Nutrition nut : nutritions) {
            if (nut.equals(nutrition)) {
                foundNut = nut;
                break;
            }
        }

        Assert.assertNotNull(foundNut);
        nutritionService.delete(foundNut.getId());
        Assert.assertNull("Should not find anything.. ", nutritionService.find(foundNut.getId()));
    }

    @Test
    public void testUpdate(){
        //make a new one.
        Nutrition nut = new Nutrition();
        nut.setCalories(random.nextInt());
        nut.setCarbs(random.nextInt());
        nut.setProduct("Orange");
        //put it in the list
        nutritionService.add(nut);

        //find it in the list, (like in delete)
        List<Nutrition> nutritions = nutritionService.findAll();
        Assert.assertNotNull(nutritions);
        Assert.assertTrue(nutritions.size() > 0);
        Nutrition foundNut = null;
        for (Nutrition n : nutritions) {
            if (n.equals(nut)) {
                foundNut = n;
                break;
            }
        }
        Assert.assertNotNull(foundNut);
        //change some of its fields...
        foundNut.setCarbs(random.nextInt());
        foundNut.setCalories(random.nextInt());
        //call update.
        nutritionService.update(foundNut);

        //call find again and compare
        Nutrition foundNut2 = null;
        nutritions = nutritionService.findAll();
        Assert.assertNotNull(nutritions);
        Assert.assertTrue(nutritions.size() > 0);
        for (Nutrition n : nutritions) {
            if (n.equals(foundNut)) {
                foundNut2 = n;
                break;
            }
        }
        Assert.assertEquals(foundNut,foundNut2);

    }
    @Test
    public void multiNewTest(){
        Nutrition nutrition = new Nutrition();

        String product = Integer.toString(random.nextInt());
        nutrition.setProduct(product);

        int calories = random.nextInt();
        nutrition.setCalories(calories);

        int carbs = random.nextInt();
        nutrition.setCarbs(carbs);

        nutritionService.add(nutrition);

        Nutrition nutrition2 = new Nutrition();

         product = Integer.toString(random.nextInt());
        nutrition2.setProduct(product);

         calories = random.nextInt();
        nutrition2.setCalories(calories);

         carbs = random.nextInt();
        nutrition2.setCarbs(carbs);

        nutritionService.add(nutrition2);

        Assert.assertFalse("Shoulda have been different...", nutrition.equals(nutrition2));
    }
    @Test
    public void badUpdateTest(){
        //make a new Nutrition -> nut
        Nutrition nut = new Nutrition();
        nut.setCalories(random.nextInt());
        nut.setCarbs(random.nextInt());
        nut.setProduct("Kebab");

        //put it in the list
        nutritionService.add(nut);

        //find it in the list
        //call findAll() -> set that to a list, nutritions
        //Assert nutritions is >0 in size, not null.
        List<Nutrition> nutritions = nutritionService.findAll();
        Assert.assertNotNull(nutritions);
        Assert.assertTrue(nutritions.size() > 0);

        //Make a Nutrition, foundNut = null;
        //Look through the list, nutritions, for Nutrition (n)
        //where n.equals(nut) i.e. the one we just made.
        //set that nut, n (which.equals nut) to foundNut.
        //the nut we created should be identical to foundNut.
        Nutrition foundNut = null;
        for (Nutrition n : nutritions) {
            if (n.equals(nut)) {
                foundNut = n;
                break;
            }
        }

        Assert.assertNotNull(foundNut);

        //change some of its fields...
        foundNut.setCarbs(random.nextInt());
        foundNut.setCalories(random.nextInt());
        foundNut.setProduct("j;lkj;alkj;lkj;lkj;lkajsdfwieropiurwowier");
        //call update.
        try {
            nutritionService.update(foundNut);
            Assert.fail("product was longer than it should have been..");
        } catch (DataAccessException e) {
            Assert.assertTrue(true);
        }

    }
    @Test
    public void badMultiNewTest(){
        Nutrition nutrition = new Nutrition();

        String product = Integer.toString(random.nextInt());
        int calories = random.nextInt();
        int carbs = random.nextInt();

        nutrition.setProduct(product);
        nutrition.setCalories(calories);
        nutrition.setCarbs(carbs);

        Nutrition nutrition2 = new Nutrition();

        nutrition2.setCalories(random.nextInt());
        nutrition2.setCarbs(random.nextInt());
        //re-Set product to something that it won't like.
        nutrition2.setProduct("SLKDJF:LSKDasdfsdfsJF:LSKDJFLKSJDFLKJWEORIUWERLKJWERLKJ");

        System.out.println(nutrition);
        System.out.println(nutrition2);

        List<Nutrition> nutritions = new ArrayList<>();

        try {
            nutritions.add(nutrition);
            nutritions.add(nutrition2);
            nutritionService.add(nutritions);
            Assert.fail("Product is too long");
        } catch (DataAccessException e) {
            Assert.assertTrue(true);
        }
    }


}
