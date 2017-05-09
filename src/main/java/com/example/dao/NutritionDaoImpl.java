package com.example.dao;

import com.example.domain.Nutrition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
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
    public List<Nutrition> findAll() {

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
    }

    /*

    public class Nutrition {
    private long id;
    private String product;
    private int calories;
    private int carbs;
     */
    private static class PersonMapper implements RowMapper<Nutrition> {
        @Override
        public Nutrition mapRow(ResultSet rs, int rowNum) throws SQLException {
            Nutrition nutrition = new Nutrition();
            nutrition.setId(rs.getLong("id"));
            nutrition.setProduct(rs.getString("product"));
            nutrition.setCalories(rs.getInt("calories"));
            nutrition.setCarbs(rs.getInt("carbs"));
            return nutrition;
        }
    }


    /*
    @Override
    public void add(Person person) {
        log.info("adding " + person);
        jdbcTemplate.update("INSERT INTO person(name, dob, gender) VALUES (?,?,?)",
                person.getName(),
                person.getDob(),
                person.getGender());
    }
    @Override
    public List<Person> find() {
        List<Person> persons = this.jdbcTemplate.query(
                "select * from person",
                new PersonMapper());
        log.info("Found " + persons.size() + " persons");
        return persons;
    }

    @Override
    public void add(Nutrition person) {
        log.info("adding " + person);
        jdbcTemplate.update("INSERT INTO person(name, dob, gender) VALUES (?,?,?)",
                person.getName(),
                person.getDob(),
                person.getGender());
    }
    @Override
    public List<Nutrition> find() {
        List<Nutrition> persons = this.jdbcTemplate.query(
                "select * from person",
                new PersonMapper());
        log.info("Found " + persons.size() + " persons");
        return persons;
    }

     */
}
