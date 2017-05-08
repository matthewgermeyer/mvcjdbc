package com.example.domain;

/**
 * long id
 String product
 int caloires
 int carbs
 */
public class Nutrition {
    private long id;
    private String product;
    private int calories;
    private int carbs;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getCarbs() {
        return carbs;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Nutrition nutrition = (Nutrition) o;

        if (calories != nutrition.calories) return false;
        if (carbs != nutrition.carbs) return false;
        return product.equals(nutrition.product);
    }

    @Override
    public int hashCode() {
        int result = product.hashCode();
        result = 31 * result + calories;
        result = 31 * result + carbs;
        return result;
    }

    @Override
    public String toString() {
        return "Nutrition{" +
                "id=" + id +
                ", product='" + product + '\'' +
                ", calories=" + calories +
                ", carbs=" + carbs +
                '}';
    }
}
