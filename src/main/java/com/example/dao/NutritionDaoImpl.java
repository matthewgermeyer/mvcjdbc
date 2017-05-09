package com.example.dao;

import com.example.domain.Nutrition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by MattyG on 5/8/17.
 */
@Repository
public class NutritionDaoImpl implements NutritionDao {
    private static final Logger log = LoggerFactory.getLogger(NutritionDaoImpl.class);

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public void add(Nutrition nutrition) {
        log.info("adding " + nutrition);
        jdbcTemplate.update("INSERT INTO nutrition(product, calories, carbs) VALUES (?,?,?)",
                nutrition.getProduct(),
                nutrition.getCalories(),
                nutrition.getCarbs());
    }

    @Override
    public List<Nutrition> findAll() {
        List<Nutrition> nutritions = this.jdbcTemplate.query(
                "select * from nutrition",
                new NutritionMapper());
        log.info("Found " + nutritions.size() + " nutritions");
        return nutritions;
    }

    private static class NutritionMapper implements RowMapper<Nutrition> {
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

}
