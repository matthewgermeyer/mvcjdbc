package com.example.dao;

import com.example.domain.Nutrition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    //Methods
    @Override
    @Transactional
    public void add(Nutrition nutrition) {
        log.info("adding " + nutrition);
        jdbcTemplate.update("INSERT INTO nutrition(product, calories, carbs) VALUES (?,?,?)",
                nutrition.getProduct(),
                nutrition.getCalories(),
                nutrition.getCarbs());
    }

    //Find Nut by id
    @Override
    public Nutrition find(long id) {
        log.info("finding Nutrition object with id -->" + id);
        String sql = "select * from nutrition where id= ?;";
        try {
            Nutrition nut = jdbcTemplate.queryForObject(
                    sql, new NutritionMapper(), new Long(id));
            return nut;

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    //Select aka findAll
    public List<Nutrition> findAll() {
        List<Nutrition> nutritions = this.jdbcTemplate.query(
                "select * from nutrition",
                new NutritionMapper());
        log.info("Found " + nutritions.size() + " nutritions");
        return nutritions;
    }

    @Override
    @Transactional
    public void update(Nutrition nutrition) {
        log.info("UPDATING Nut called --> " + nutrition);
        String sql = "update nutrition set product = ?, calories = ?, carbs = ? WHERE id = ?;";

        int numUpdated = jdbcTemplate.update(sql, nutrition.getProduct(), nutrition.getCalories(), nutrition.getCarbs(), nutrition.getId());

        System.out.println("\n\n\n num updated ---> "+ numUpdated);
    }

    //delete a Nut
    @Override
    @Transactional
    public void delete(long id) {
        log.info("finding/deleting Nutrition object with id -->" + id);
        String sql = "delete from nutrition where id = ?";
        jdbcTemplate.update(sql, new Long(id));

    }

    //add Many
    @Override
    @Transactional
    public void add(List<Nutrition> nutritions) {
        for (Nutrition nutrition : nutritions) {
            this.add(nutrition);
        }
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
