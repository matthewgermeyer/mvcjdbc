package com.example.dao;

import com.example.common.FoodType;
import com.example.domain.Nutrition;
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
public class NutritionDaoTest {
    Random random = new Random();

    @Autowired
    NutritionDao nutritionDao;

    @Test
    public void testCreate() {
        Nutrition nutrition = createNut();

        List<Nutrition> nutritions = nutritionDao.findAll();
        Assert.assertNotNull(nutritions);
        Assert.assertTrue(nutritions.size() > 0);

        Nutrition foundNut = null;
        foundNut = findNutritionInList(nutrition);

        System.out.println("\n\n\n" + nutrition);
        System.out.println("\n\n\n" + foundNut);
        Assert.assertTrue("Mismatch in list!! " + nutrition, nutrition.equals(foundNut));
    }

    @Test
    public void testFind() {
        Nutrition nutrition = createNut();

        Nutrition foundNut = null;
        foundNut = findNutritionInList(nutrition);

        Nutrition comparisonNut = nutritionDao.find(foundNut.getId());
        Assert.assertTrue("Errr, they should be the same", foundNut.equals(comparisonNut));
    }

    @Test
    public void testDelete() {
        Nutrition nutrition = createNut();
        Nutrition foundNut = null;
        foundNut = findNutritionInList(nutrition);

        Assert.assertNotNull(foundNut);
        nutritionDao.delete(foundNut.getId());
        Assert.assertNull("Should not find anything.. ", nutritionDao.find(foundNut.getId()));
    }

    @Test
    public void testUpdate() {
        Nutrition nutrition = createNut();
        Nutrition foundNut = findNutritionInList(nutrition);
        Assert.assertNotNull(foundNut);

        //Scramble some fields.
        foundNut.setCarbs(random.nextInt());
        foundNut.setCalories(random.nextInt());

        nutritionDao.update(foundNut);

        Nutrition foundNut2 = findNutritionInList(foundNut);

        Assert.assertEquals(foundNut, foundNut2);

    }

    @Test
    public void multiNewTest() {
        Nutrition nutrition = createNut();
        Nutrition nutrition2 = createNut();
        Assert.assertFalse("Shoulda have been different...", nutrition.equals(nutrition2));
    }


    //DRY Methods

    //Look in 1) Nutritions List for 2) a given nutrition -> if it's in there, return it.
    private Nutrition findNutritionInList(Nutrition nutrition) {
        List<Nutrition> nutritions = nutritionDao.findAll();
        for (Nutrition nut : nutritions) {
            if (nut.equals(nutrition)) {
                return nut;
            }
        }
        return null;
    }

    //Create a random nut & put it in the list -> nutritions
    private Nutrition createNut() {
        Nutrition nutrition = new Nutrition();

        String product = Integer.toString(random.nextInt());
        int calories = random.nextInt();
        int carbs = random.nextInt();
        Boolean isClean = random.nextBoolean();
        FoodType foodTypes[] = FoodType.values();

        nutrition.setProduct(product);
        nutrition.setCalories(calories);
        nutrition.setCarbs(carbs);
        nutrition.setClean(isClean);
        nutrition.setFoodType(foodTypes[random.nextInt(foodTypes.length)]);

        nutritionDao.add(nutrition);
        return nutrition;
    }


}
