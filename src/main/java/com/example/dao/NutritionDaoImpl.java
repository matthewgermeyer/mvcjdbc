package com.example.dao;

import com.example.domain.Nutrition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by MattyG on 5/8/17.
 */
@Repository
public class NutritionDaoImpl implements NutritionDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void add(Nutrition nutrition) {
        String sql = "INSERT INTO nutrition (product, calories, carbs) VALUES ('";
        sql = sql + nutrition.getProduct();
        sql = sql + "' ,";
        sql = sql + nutrition.getCalories();
        sql = sql + ", ";
        sql = sql + nutrition.getCarbs();
        sql = sql + ")";

        System.out.println("\n!!!\n the insert sql is: " + sql);
        jdbcTemplate.update(sql);

    }

    @Override
    public List findAll() {

        List<Map<String,Object>> results = jdbcTemplate.queryForList("select * from nutrition");
        List<Nutrition> nutritions = new ArrayList<>();
        for(Map<String, Object> map : results){
            Nutrition nut = new Nutrition();
            nut.setId((int)map.get("id"));
            nut.setCalories((int)map.get("calories"));
            nut.setCarbs((int)map.get("carbs"));
            nut.setProduct((String)map.get("product"));
            nutritions.add(nut);
        }
        System.out.println(results);
        return nutritions;
//        String sqlQuery = "select * from nutrition";
//        List<Map<String,Object>> listOfMaps = jdbcTemplate.queryForList(sqlQuery);
//        List<Nutrition> nuts = new Nutrition();
//        for (Map<String, Object> map : listOfMaps){
//            Nutrition nut = new Nutrition();
//            nut.setId(map.get("id"));
//        }
//
//        List<Map<String,Object>> listOfKeys = new ArrayList<>();
//        Map<String,Object> tableRow = new HashMap();
//        return selectAllFromNutrition;

    }
}
