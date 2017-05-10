package com.example.service;

import com.example.dao.NutritionDao;
import com.example.domain.Nutrition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NutritionSerivceImpl implements  NutritionService{


    @Autowired
    NutritionDao nutritionDao;

    @Override
    @Transactional
    public void add(Nutrition nutrition) {
        nutritionDao.add(nutrition);

    }

    @Override
    public List<Nutrition> findAll() {
        return nutritionDao.findAll();
    }

    @Override
    public Nutrition find(long id) {
        return nutritionDao.find(id);
    }

    @Override
    @Transactional
    public void update(Nutrition nutrition) {
        nutritionDao.update(nutrition);

    }

    @Override
    @Transactional
    public void delete(long id) {
        nutritionDao.delete(id);

    }

    @Override
    @Transactional
    public void add(List<Nutrition> nutritions) {
        nutritionDao.add(nutritions);

    }
}
