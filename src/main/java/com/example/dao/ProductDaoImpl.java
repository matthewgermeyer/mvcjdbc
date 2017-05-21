package com.example.dao;

import com.example.domain.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {
    private static final Logger log = LoggerFactory.getLogger(ProductDaoImpl.class);

    @Autowired
    NutritionDao nutritionDao;
    @Autowired
    JdbcTemplate jdbcTemplate;

    //Methods
    @Override
    @Transactional
    public void add(Product product){
        log.info("adding " + product);

        KeyHolder holder = new GeneratedKeyHolder();

        String sql = "INSERT INTO product(name, company) VALUES (?,?)";
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, product.getName());
                ps.setString(2, product.getCompany());
                return ps;
            }
        }, holder);

        product.setId(holder.getKey().intValue());

    }

    @Override
    public Product find(int id) {
        log.info("finding Product object with id -->" + id);
        String sql = "select * from product where id= ?;";
        try {
            Product prod = jdbcTemplate.queryForObject(
                    sql, new ProductDaoImpl.ProductMapper(), id);
            return prod;

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = this.jdbcTemplate.query(
                "select * from product",
                new ProductDaoImpl.ProductMapper());
        log.info("Found " + products.size() + " products");
        return products;
    }


    @Override
    @Transactional
    public void delete(int id) {
        log.info("finding/deleting Product object with id -->" + id);
        String sql = "delete from product where id = ?";
        jdbcTemplate.update(sql, id);

    }

//        @Override
//        public void addNutrtionToProduct(Nutrition nutrition) {
//            jdbcTemplate.update("INSERT INTO nutrition (product, calories, carbs, clean, foodType, product_id) VALUES (?,?,?, ?,?,?)",
//                    nutrition.getProduct(),
//                    nutrition.getCalories(),
//                    nutrition.getCarbs(),
//                    nutrition.isClean(),
//                    nutrition.getFoodType().name(),
//                    nutrition.getProductId());
//        }




    private static class ProductMapper implements RowMapper<Product> {
        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setCompany(rs.getString("company"));

            return product;
        }
    }

}
