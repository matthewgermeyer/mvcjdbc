package com.example.service;

import com.example.common.FoodType;
import com.example.domain.Nutrition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NutritionServiceTest2 {

    Random random = new Random();

//    @MockBean
//    NutritionDao nutritionDao;

    @Autowired
    NutritionService nutritionService;

    @Test
    public void testCreate() {
        Nutrition nutrition = new Nutrition();
        String product = Integer.toString(random.nextInt());
        int calories = random.nextInt();
        int carbs = random.nextInt();

        nutrition.setProduct(product);
        nutrition.setCarbs(carbs);
        nutrition.setCalories(calories);
        nutrition.setClean(true);
        nutrition.setFoodType(FoodType.FAT);

        nutritionService.add(nutrition);
//        org.mockito.BDDMockito.given(nutrition.find(1)).willReturn(nutrition);




//        List<Nutrition> nutritions = nutritionService.findAll();
//
//        Assert.assertNotNull(nutritions);
//        Assert.assertTrue(nutritions.size() > 0);
//        boolean found = false;
//        for (Nutrition nut : nutritions) {
//            if (nut.equals(nutrition)) {
//                found = true;
//                break;
//            }
//        }
//
//        Assert.assertTrue("Could not find " + nutrition, found);
    }

}
